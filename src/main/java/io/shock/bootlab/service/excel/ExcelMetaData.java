package io.shock.bootlab.service.excel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelMetaData<T> {
    private List<String> fieldNames;
    private Map<String, Map<String, Object>> fieldAttributes = new HashMap<>();

    public ExcelMetaData(Class<T> type) {
        this.fieldNames = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ExcelColumn.class))
                .map(Field::getName)
                .toList();
        this.fieldNames.stream()
                .forEach(name -> {
                    try {
                        String header = type.getDeclaredField(name).getAnnotation(ExcelColumn.class).header();
                        header = header.isEmpty() ? toSnakeCase(name) : header;
                        fieldAttributes.put(name, Map.of(
                                "header", header
                        ));
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public String getHeader(String fieldName) {
        return (String) fieldAttributes.get(fieldName).get("header");
    }

    private String toSnakeCase(String fieldName) {
        StringBuilder result = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append("_");
            }
            result.append(Character.toUpperCase(c));
        }
        return result.toString();
    }
}
