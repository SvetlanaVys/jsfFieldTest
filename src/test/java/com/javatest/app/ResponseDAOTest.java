package com.javatest.app;

import com.javatest.app.dao.FieldDAO;
import com.javatest.app.dao.ResponseDAO;
import com.javatest.app.model.Field;
import com.javatest.app.model.Option;
import com.javatest.app.model.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ResponseDAOTest {

    private ResponseDAO responseDAO = new ResponseDAO();
    private FieldDAO fieldDAO = new FieldDAO();
    private Response response;
    List<Response> responseList = new ArrayList<Response>();
    private Field field = com.javatest.app.Field.createField("testLabel999", "Single line text", false, true, 0, new ArrayList<Option>());

    private Response createResponse(String content, Integer row, Field field) {
        Response response = new Response();
        response.setContent(content);
        response.setRow(row);
        response.setField(field);
        return response;
    }

    @Test
    public void testSaveField() {
        this.fieldDAO.saveFieldDetails(this.field);
    }

    @Test(dependsOnMethods={"testSaveField"})
    public void testSaveResponse() {
        this.response = this.createResponse("content", this.field.getRowNumber(), this.field);
        this.responseList.add(response);
        this.response = this.createResponse("content2", this.field.getRowNumber(), this.field);
        this.responseList.add(response);
        this.responseDAO.saveResponseDetails(responseList);
    }

    @Test(dependsOnMethods={"testSaveResponse"})
    public void testDeleteResponse()  {
        this.responseDAO.deleteResponseRecord(this.responseList);
    }

    @Test(dependsOnMethods={"testDeleteResponse"})
    public void testDeleteField()  {
        this.fieldDAO.deleteFieldRecord(this.field);
    }
}
