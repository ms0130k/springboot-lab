package io.shock.bootlab.service.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public interface ExcelBackgroundColor {
    void apply(CellStyle cellStyle);
}
