package io.shock.bootlab.service.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelCellStyleConfigurer {
    private ExcelBackgroundColor backgroundColor = new NoExcelBackgroundColor();

    public ExcelCellStyleConfigurer backgroundColor(int red, int blue, int green) {
        return null;
    }

    public void configure(CellStyle cellStyle) {

    }
}
