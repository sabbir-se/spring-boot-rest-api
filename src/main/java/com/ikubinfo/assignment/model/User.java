package com.ikubinfo.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sabbir on 9/28/21.
 */

@Entity(name = "user")
@ApiModel(description = "All details about the User.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated user ID")
    private Long id;

    @Column(name = "first_name", length = 128)
    @ApiModelProperty(notes = "The user first name")
    private String firstName;

    @Column(name = "last_name", length = 128)
    @ApiModelProperty(notes = "The user last name")
    private String lastName;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty(notes = "The user address")
    private String address;

    @Column(name = "email_address")
    @ApiModelProperty(notes = "The user email address")
    private String emailAddress;

    @Column(unique = true, length = 20)
    @ApiModelProperty(notes = "The user phone number")
    private String phone;

    @Transient
    @ApiModelProperty(notes = "The user roles")
    private List<String> role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
