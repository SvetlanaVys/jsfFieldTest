package com.javatest.app.bean;

import com.javatest.app.dao.FieldDAO;
import com.javatest.app.dao.ResponseDAO;
import com.javatest.app.model.Field;
import com.javatest.app.model.Response;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;

/**
 * Class for interplay with 'response' pages
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class ResponseBean {
    private List<Response> responsesList;
    private List<Response> responses = new ArrayList<Response>();
    private List<Field> activeFieldList;
    private List<Field> allFieldList;
    private Response response;
    private Map responsesMap;

    public Response getResponse() { return this.response; }
    public void setResponse(Response response) {
        this.response = response;
    }

    public List responsesList() {
        return responsesList;
    }
    public List activeFieldList( ) {
        return activeFieldList;
    }
    public List allFieldList( ) {
        return allFieldList;
    }
    public Map responsesMap() {
        return responsesMap;
    }
    /**
     * Initialize method
     */
    @PostConstruct
    public void init() {
        responsesList = ResponseDAO.getResponsesList();
        activeFieldList = FieldDAO.getActiveFieldsList();
        allFieldList = FieldDAO.getFieldsList();
        responsesMap = makeResponses();
    }

    public String saveResponses() {
        System.out.println("START: " + activeFieldList.get(0).getLabel() );
        for(Field field: activeFieldList) {
            if(field.getType().equals("Checkbox")) {
                field.setResponseBuf(Arrays.toString(field.getResponseBufArr()));
            }
            if(field.getResponseBuf() != ""){
                response = new Response();
                response.setContent(field.getResponseBuf());
                response.setRow(field.getRowNumber());
                response.setField(field);
                responses.add(response);
            }
        }
        System.out.println("RESPONSES: " + responses);
        return ResponseDAO.saveResponseDetails(responses);
    }

    private Map makeResponses() {
        Map responses = new HashMap<Integer, Map<Integer, String>>();
        List<String> content;
        Map<Long, String> field = new HashMap();
        int i = 0;
        for(Response response: responsesList) {
            if(i != response.getRow()) {
                i = response.getRow();
                field = new HashMap();
            }
            if(response.getField().getType().equals("Date")) {
                response.setContent(formatDate(response.getContent()));
            }
            field.put(response.getField().getId(), response.getContent());
            responses.put(response.getRow(), field);
        }

        return responses;
    }

    /**
     * Method to format date like 'dd Month YYYY'
     * @param date
     * @return
     */
    private String formatDate(String date) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String resultDate = "";
        date += ".";
        String[] bufDate = date.split("\\.");
        resultDate = bufDate[0] + " " + months[Integer.parseInt(bufDate[1]) - 1] + " " + bufDate[2];
        return resultDate;
    }

}
