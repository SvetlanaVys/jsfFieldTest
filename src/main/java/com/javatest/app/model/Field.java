package com.javatest.app.model;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Class for saving field-object
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@Entity
@Table(name = "field")
public class Field {

    /** The id is a unique object identifier */
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    /** This property is a name of field */
    @Column(name = "label")
    private String label;

    /** This property is a one of the five possible field types */
    @Column(name = "type")
    private String type;

    /** The property controls the visibility of the field */
    @Column(name = "isactive")
    private Boolean isActive;

    /** If this property is true this field must be completed  */
    @Column(name = "required")
    private Boolean required;

    /** This property shows how many response rows contain a field */
    @Column(name = "rownumber")
    private Integer rowNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field", cascade = CascadeType.ALL)
    private List<Option> options;

    @Transient
    private String responseBuf;

    @Transient
    private String[] responseBufArr;


    /**
     * Gets the value of the id property, which can be specified using the method {@link #setId(Long)}
     * @return property value id
     */
    public Long getId() { return this.id; }

    /**
     * Sets the property value id, which can be obtained using the method {@link #getId()}
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Geter`s and Seter`s for other properties
     *
     */

    public String  getLabel() {
        return this.label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String  getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getRequired() {
        return this.required;
    }
    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getRowNumber() {
        return this.rowNumber;
    }
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<Option> getOptions() {
        return this.options;
    }
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getResponseBuf() { return this.responseBuf; }
    public void setResponseBuf(String responseBuf) {
        this.responseBuf = responseBuf;
    }

    public String[] getResponseBufArr() { return this.responseBufArr; }
    public void setResponseBufArr(String[] responseBufArr) {
        this.responseBufArr = responseBufArr;
    }

    /**
     *
     * @return String for current field object
     */
    public String toString()
    {
        return "Field: " +
                "id: " + this.id + " " +
                "label: " + this.label + " " +
                "type: " + this.type + " " +
                "isActive: " + this.isActive + " " +
                "required: " + this.required + " " +
                "rowNumber: " + this.rowNumber + " " +
                "options: " + this.options;
    }
}
