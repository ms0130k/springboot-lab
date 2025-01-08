package io.shock.bootlab.controller;

import io.shock.bootlab.dto.Car;
import io.shock.bootlab.service.CarService;
import io.shock.bootlab.service.excel.ExcelFile;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ExcelController {
    public static final MediaType EXCEL_XLSX = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    private final CarService carService;

    @GetMapping("/json")
    @ResponseBody
    public List<Car> getJson() {
        return carService.getCars();
    }

    @GetMapping("/excel2")
    public void downloadExcel2(HttpServletResponse response) throws IOException {
        response.setContentType(EXCEL_XLSX.toString());
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename("cars.xlsx").build();
        response.setHeader("Content-Disposition", contentDisposition.toString());

        List<Car> cars = carService.getCars();

        ExcelFile<Car> excelFile = ExcelFile.create(cars, Car.class);
        excelFile.write(response.getOutputStream());
    }

    @GetMapping("/excel1")
    public void downloadExcel1(HttpServletResponse response) {
        response.setContentType(EXCEL_XLSX.toString());
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename("cars.xlsx").build();
        response.setHeader("Content-Disposition", contentDisposition.toString());

        try (Workbook workbook = new SXSSFWorkbook()) {
            List<Car> cars = carService.getCars();

            Sheet sheet = workbook.createSheet("Cars");
            CellStyle headerStyle = workbook.createCellStyle();
            applyCellStyle(headerStyle, Color.CYAN);
            CellStyle bodyStyle = workbook.createCellStyle();
            applyCellStyle(bodyStyle, Color.GREEN);


            int rowIndex = 0;
            Row headerRow = sheet.createRow(rowIndex++);
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("Name");
            headerCell.setCellStyle(headerStyle);
            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("Year");
            headerCell.setCellStyle(headerStyle);

            for (Car car : cars) {
                Row row = sheet.createRow(rowIndex++);
                Cell cell = row.createCell(0);
                cell.setCellValue(car.getCarName());
                cell.setCellStyle(bodyStyle);
                cell = row.createCell(1);
                cell.setCellValue(car.getYear());
                cell.setCellStyle(bodyStyle);
            }
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void applyCellStyle(CellStyle cellStyle, Color color) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
        xssfCellStyle.setFillForegroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
    }
}
