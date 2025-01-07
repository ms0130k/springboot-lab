package io.shock.bootlab.service.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class RgbExcelBackgroundColor implements ExcelBackgroundColor {
    private static final int MIN_RGB = 0;
    private static final int MAX_RGB = 255;

    private final byte red;
    private final byte green;
    private final byte blue;

    public RgbExcelBackgroundColor(int red, int green, int blue) {
        this.red = validateAndConvertToByte("Red", red);
        this.green = validateAndConvertToByte("Green", green);
        this.blue = validateAndConvertToByte("Blue", blue);
    }

    private byte validateAndConvertToByte(String colorName, int value) {
        if (value < MIN_RGB || value > MAX_RGB) {
            throw new IllegalArgumentException(
                    String.format("%s value must be between %d and %d: %d", colorName, MIN_RGB, MAX_RGB, value)
            );
        }
        return (byte) value;
    }

    @Override
    public void apply(CellStyle cellStyle) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
        xssfCellStyle.setFillForegroundColor(new XSSFColor(new byte[]{red, green, blue}, new DefaultIndexedColorMap()));
        xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
