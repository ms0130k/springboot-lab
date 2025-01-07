package io.shock.bootlab.service.excel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelFile<T> {
    private static final SpreadsheetVersion SUPPLY_VERSION = SpreadsheetVersion.EXCEL2007;
    private static final int ROW_START_INDEX = 0;
    private static final int COLUMN_START_INDEX = 0;

    private SXSSFWorkbook workbook;
    private Sheet sheet;
    private ExcelMetaData excelMetaData;

    public static <T> ExcelFile create(List<T> data, Class<T> type) {
        return new ExcelFile(data, type);
    }

    public void write(OutputStream os) {
        try {
            workbook.write(os);
            workbook.close();
            workbook.dispose();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ExcelFile(List<T> data, Class<T> type) {
        workbook = new SXSSFWorkbook();
        sheet = workbook.createSheet();
        excelMetaData = new ExcelMetaData(type);
        renderExcel(data);
    }

    private void renderExcel(List<T> data) {
        assert data != null;
        sheet = workbook.createSheet();
        renderHeaders(sheet, ROW_START_INDEX, COLUMN_START_INDEX);
        if (data.isEmpty()) {
            return;
        }

        int rowIndex = ROW_START_INDEX + 1;
        for (T item : data) {
            renderBody(item, rowIndex++, COLUMN_START_INDEX);
        }
    }

    private void renderHeaders(Sheet sheet, int rowStartIndex, int columnStartIndex) {
        Row row = sheet.createRow(rowStartIndex);
        List<String> fieldNames = excelMetaData.getFieldNames();
        fieldNames.stream().forEach(name -> {
            int columnIndex = fieldNames.indexOf(name) + columnStartIndex;
            String header = excelMetaData.getHeader(name);
            row.createCell(columnIndex).setCellValue(header);
        });
    }

    private void renderBody(T item, int i, int columnStartIndex) {
        Row row = sheet.createRow(i);
        List<String> fieldNames = excelMetaData.getFieldNames();
        fieldNames.stream().forEach(name -> {
            int columnIndex = fieldNames.indexOf(name) + columnStartIndex;
            try {
                Field field = item.getClass().getDeclaredField(name);
                field.setAccessible(true);
                row.createCell(columnIndex).setCellValue(String.valueOf(field.get(item)));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
