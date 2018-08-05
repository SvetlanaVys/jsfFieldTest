package com.javatest.app.model;

import javax.persistence.*;

/**
 * Class for saving user`s responses
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@Entity
@Table(name = "response")
public class Response {

    /** The id is a unique object identifier */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /** User`s answer*/
    @Column(name = "content")
    private String content;

    /** row number*/
    @Column(name = "row")
    private Integer row;

    /** The field for which was answered*/
    @ManyToOne
    @JoinColumn(name = "field")
    private Field field;

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

    public String getContent() { return this.content; }
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRow() { return this.row; }
    public void setRow(Integer row) {
        this.row = row;
    }

    public Field getField() { return this.field; }
    public void setField(Field field) {
        this.field = field;
    }

    /**
     *
     * @return String for current response object
     */
    public String toString()
    {
        return  "Response: " +
                "id: " + this.id + " " +
                "content: " + this.content + " " +
                "row: " + this.row + " " +
                "field: " + this.field + " ";
    }
}
