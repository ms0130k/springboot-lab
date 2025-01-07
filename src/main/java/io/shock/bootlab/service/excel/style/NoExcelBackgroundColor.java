package io.shock.bootlab.service.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public class NoExcelBackgroundColor implements ExcelBackgroundColor {
    @Override
    public void apply(CellStyle cellStyle) {
        // do nothing
    }
}
