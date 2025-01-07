package io.shock.bootlab.service.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RgbExcelBackgroundColorTest {

    @Test
    void testValidRgbValues() {
        RgbExcelBackgroundColor backgroundColor = new RgbExcelBackgroundColor(255, 128, 64);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        assertDoesNotThrow(() -> backgroundColor.apply(cellStyle));
    }

    @Test
    void testInvalidRgbValues() {
        assertThrows(IllegalArgumentException.class, () -> new RgbExcelBackgroundColor(-1, 128, 64));
        assertThrows(IllegalArgumentException.class, () -> new RgbExcelBackgroundColor(256, 128, 64));
        assertThrows(IllegalArgumentException.class, () -> new RgbExcelBackgroundColor(128, -10, 64));
    }

    @Test
    void testApplyBackgroundColor() {
        RgbExcelBackgroundColor backgroundColor = new RgbExcelBackgroundColor(100, 150, 200);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        backgroundColor.apply(cellStyle);

        assertNotNull(cellStyle.getFillForegroundColorColor());
        assertEquals(
                new XSSFColor(new byte[]{100, (byte) 150, (byte) 200}, new DefaultIndexedColorMap()),
                cellStyle.getFillForegroundColorColor()
        );
    }

}