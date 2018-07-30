package com.javatest.app.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "field")
    public Field field;

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
    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }

    public Field getField() { return this.field; }
    public void setField(Field field) {
        this.field = field;
    }
    /**
     *
     * @return String for current option object
     */
    public String toString()
    {
        return "Option: " +
                "id: " + this.id + " " +
                "name: " + this.name + " ";
    }
}