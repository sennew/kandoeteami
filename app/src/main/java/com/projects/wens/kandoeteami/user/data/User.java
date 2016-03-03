package com.projects.wens.kandoeteami.user.data;

import java.util.List;

/**
 * Created by michaelkees on 02/03/16.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String oldPassword;
    private String email;
    private List<Object> roleTypes;
    private Person person;

    public User() {
    }

    public User(int userId, String username, String password, String oldPassword, String email, List<Object> roleTypes, Person person) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.oldPassword = oldPassword;
        this.email = email;
        this.roleTypes = roleTypes;
        this.person = person;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleTypes(List<Object> roleTypes) {
        this.roleTypes = roleTypes;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public List<Object> getRoleTypes() {
        return roleTypes;
    }

    public Person getPerson() {
        return person;
    }
}
