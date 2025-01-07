package io.shock.bootlab.service.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public abstract class CustomExcelCellStyle implements ExcelCellStyle {
    private ExcelCellStyleConfigurer configurer = new ExcelCellStyleConfigurer();

    public CustomExcelCellStyle() {
        configurer(configurer);
    }

    public abstract void configurer(ExcelCellStyleConfigurer configurer);

    @Override
    public void apply(CellStyle cellStyle) {
        configurer.configure(cellStyle);
    }

}
