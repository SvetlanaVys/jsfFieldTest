package com.javatest.app;

import com.javatest.app.model.Option;

import java.util.List;

public class Field {
    private static com.javatest.app.model.Field field;

    public static com.javatest.app.model.Field createField(String label, String type, Boolean isActive, Boolean required, Integer rowNumber, List<Option> option) {
        com.javatest.app.model.Field field = new com.javatest.app.model.Field();
        field.setLabel(label);
        field.setType(type);
        field.setIsActive(isActive);
        field.setRequired(required);
        field.setRowNumber(rowNumber);
        field.setOptions(option);
        return field;
    }
}
