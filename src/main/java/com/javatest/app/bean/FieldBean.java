package com.javatest.app.bean;

import com.javatest.app.dao.FieldDAO;
import com.javatest.app.model.Field;
import com.javatest.app.model.Option;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Class for interplay with 'field' pages
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class FieldBean {
    public List<Field> fieldsList;
    private Date date;
    private Field field;
    private String optString;

    private String[] types = {"Single line text", "Multiline text", "Radio button", "Checkbox",  "Combobox", "Date"};

    public Field getField() { return this.field; }
    public void setField(Field field) {
        this.field = field;
    }

    public String[] getTypes() {
        return this.types;
    }
    public void setTypes(String[] types) {
        this.types = types;
    }


    public String getOptString() {
        return this.optString;
    }
    public void setOptString(String optString) {
        this.optString = optString;
    }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public List fieldsList() {
        return fieldsList;
    }

    /**
     * Initialize method
     */
    @PostConstruct
    public void init() {
        fieldsList = FieldDAO.getFieldsList();
        field = new Field();
    }


    /**
     * Method for saving a field
     * @param newField is a 'field' need to save
     * @return page with 'fields'
     */
    public String saveFieldDetails(Field newField) {
        newField.setOptions(new ArrayList<Option>());
        newField.setRowNumber(this.fieldsList.get(0).getRowNumber());
        return FieldDAO.saveFieldDetails(saveOptions(newField, optString));
    }
    public String editFieldRecord(Long fieldId) {
        return FieldDAO.editFieldRecord(fieldId);
    }
    public String updateFieldDetails(Field updateField, String stringOptions) {
        return FieldDAO.updateFieldDetails(saveOptions(updateField, stringOptions));
    }

    public String deleteFieldRecord(Field field) {
        return FieldDAO.deleteFieldRecord(field);
    }


    /**
     * Parse optionString and add 'options' property to the 'field'
     * @param field current 'field' object
     * @param optionsString string with this field`s options
     * @return current 'field' with options from @param optionsString in property field.options
     */
    public Field saveOptions(Field field, String optionsString) {
        String[] optionArr = optionsString.split("\r\n");
        Option option;
        if(field.getType().equals("Radio button") || field.getType().equals("Checkbox") || field.getType().equals("Combobox")) {
            for (int i = 0; i < field.getOptions().size(); i++) {
                if (optionArr.length > i) {
                    field.getOptions().get(i).setName(optionArr[i]);
                } else {
                    field.getOptions().get(i).setName("");
                }
            }
            for (int i = field.getOptions().size(); i < optionArr.length; i++) {
                if (optionArr[i] != "") {
                    option = new Option();
                    option.setName(optionArr[i]);
                    option.setField(field);
                    field.getOptions().add(option);
                }
            }
        }
        return field;
    }

}
