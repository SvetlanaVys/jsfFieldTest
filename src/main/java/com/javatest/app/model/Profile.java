package com.javatest.app.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Class for saving user`s profile
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@Entity
@Table(name = "profile")
public class Profile {

    /** The id is a unique object identifier */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    /** The email is a unique property */
    @Column(name = "email")
    private String email;

    /** Password with email used for user authenticate */
    @Column(name = "password")
    private String password;

    /** This is the user`s First Name */
    @Column(name = "first_name")
    private String firstName;

    /** This is the user`s Last Name */
    @Column(name = "last_name")
    private String lastName;

    /** This is the user`s Phone number */
    @Column(name = "phone")
    private String phone;

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

    public String getEmail() { return this.email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return this.password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() { return this.phone; }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return String for current profile object
     */
    public String toString()
    {
        return  "Profile: " +
                "id: " + this.id + " " +
                "email: " + this.email + " " +
                "password: " + this.password + " " +
                "firstName: " + this.firstName + " " +
                "lastName: " + this.lastName + " " +
                "phone: " + this.phone;
    }
}
