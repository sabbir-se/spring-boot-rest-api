package com.ikubinfo.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by sabbir on 9/28/21.
 */

@Entity(name = "user_login")
@ApiModel(description = "All details about the User Login.")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated user login ID")
    private Long id;

    @Column(name = "user_name", length = 50)
    @ApiModelProperty(notes = "Login username")
    private String username;

    @Column(name = "password")
    @ApiModelProperty(notes = "Login password")
    private String password;

    @Column(length = 50)
    @ApiModelProperty(notes = "Salt for password generated")
    private String salt;

    @Column(name = "access_token", columnDefinition = "TEXT")
    @ApiModelProperty(notes = "JWT access token generated")
    private String accessToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
