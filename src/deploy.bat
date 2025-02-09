@echo off

set YEAR=%date:~6,4%
set MONTH=%date:~0,2%
set DAY=%date:~3,2%
set HOUR=%time:~0,2%
IF "%HOUR:~0,1%" == " " SET HOUR=0%HOUR:~1,1%
set MINUTE=%time:~3,2%
set SECOND=%time:~6,2%

set JAR_NAME=springboot-lab.jar
set BACKUP_NAME=%JAR_NAME%_%YEAR%%MONTH%%DAY%-%HOUR%%MINUTE%%SECOND%.bak
set SERVICE_NAME=testProject
set DEPLOY_DIR=C:\Shock\deploy
set BACKUP_DIR=C:\Shock\deploy\backup\%YEAR%-%MONTH%\%DAY%
set BUILD_DIR=C:\ProgramData\Jenkins\.jenkins\workspace\deploy-test\build\libs

echo [INFO] Stopping existing service...
net stop %SERVICE_NAME%

echo [INFO] Ensuring backup directory exists...
if not exist "%BACKUP_DIR%" mkdir "%BACKUP_DIR%"

echo [INFO] Backing up existing JAR file...
if exist "%DEPLOY_DIR%\%JAR_NAME%" (
    copy "%DEPLOY_DIR%\%JAR_NAME%" "%BACKUP_DIR%\%BACKUP_NAME%"
)

echo [INFO] Deleting old JAR file...
del "%DEPLOY_DIR%\%JAR_NAME%" >nul 2>&1

echo [INFO] Copying new JAR file...
copy "%BUILD_DIR%\%JAR_NAME%" "%DEPLOY_DIR%\"

echo [INFO] Restarting service...
net start %SERVICE_NAME%
