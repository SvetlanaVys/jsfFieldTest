package com.javatest.app;

import com.javatest.app.dao.FieldDAO;
import com.javatest.app.model.Field;
import com.javatest.app.model.Option;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FieldDAOTest {

    private FieldDAO fieldDAO = new FieldDAO();
    private Field field = com.javatest.app.Field.createField("testLabel999", "Single line text", false, true, 0, new ArrayList<Option>());
    List<Option> options = new ArrayList<Option>();

    private Option createOption(Long id, String name, Field field) {
        Option option = new Option();
        option.setId(id);
        option.setName(name);
        option.setField(field);
        return option;
    }

    @Test
    public void testSaveField() {
        this.fieldDAO.saveFieldDetails(this.field);
    }

    @Test(dependsOnMethods={"testSaveField"})
    public void testUpdateField()  {
        this.field.setType("Radio button");
        this.field.setIsActive(false);
        this.fieldDAO.updateFieldDetails(this.field);
    }

    @Test(dependsOnMethods={"testSaveField"})
    public void testSaveOption()  {
        this.options.add(createOption(null,"newOption1", this.field));
        this.options.add(createOption(null,"newOption2", this.field));
        this.field.setOptions(this.options);
        this.fieldDAO.updateFieldDetails(this.field);
    }

    @Test(dependsOnMethods={"testSaveOption"})
    public void testUpdateOption()  {
        this.options = new ArrayList<Option>();
        this.options.add(createOption(this.field.getOptions().get(0).getId(),"newOption3", this.field));
        this.options.add(createOption(this.field.getOptions().get(1).getId(),"newOption4", this.field));
        this.field.setOptions(this.options);
        this.fieldDAO.updateFieldDetails(this.field);
    }

    @Test(dependsOnMethods={"testUpdateOption"})
    public void testDeleteField()  {
        this.fieldDAO.deleteFieldRecord(this.field);
    }
}
