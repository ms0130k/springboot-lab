package io.shock.bootlab.service.excel;

import io.shock.bootlab.dto.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ExcelMetaDataTest {
    @Test
    void getFieldNames() {
        // given
        ExcelMetaData<Car> excelMetaData = new ExcelMetaData<>(Car.class);
        // when
        var fieldNames = excelMetaData.getFieldNames();
        // then
        assertEquals(3, fieldNames.size());
        assertTrue(fieldNames.contains("carName"));
        assertTrue(fieldNames.contains("year"));
        assertTrue(fieldNames.contains("rating"));
    }

    @Test
    void getHeader() {
        // given
        ExcelMetaData<Car> excelMetaData = new ExcelMetaData<>(Car.class);
        // when
        var carNameHeader = excelMetaData.getHeader("carName");
        var yearHeader = excelMetaData.getHeader("year");
        var ratingHeader = excelMetaData.getHeader("rating");
        // then
        assertEquals("CAR_NAME", carNameHeader);
        assertEquals("YEAR", yearHeader);
        assertEquals("RATING", ratingHeader);
    }
}