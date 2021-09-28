package com.ikubinfo.assignment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by sabbir on 9/28/21.
 */

@Entity(name = "role")
@ApiModel(description = "All details about the user roles.")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated role ID")
    private Long id;

    @ApiModelProperty(notes = "The role name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @ApiModelProperty(notes = "The role associated with user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
