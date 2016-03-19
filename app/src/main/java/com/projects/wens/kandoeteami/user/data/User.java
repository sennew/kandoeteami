package com.projects.wens.kandoeteami.user.data;

import java.util.List;


public class User {
    private int userId;
    private String username;
    private String password;
    private String oldPassword;
    private boolean facebookAccount;
    private String email;
    private List<Object> roleTypes;
    private String profilePicture;
    private Person person;
    private int position;


    public User() {
    }


    public User(int userId, String username, String password, String oldPassword, String email, List<Object> roleTypes, Person person, String profilePicture) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.oldPassword = oldPassword;
        this.email = email;
        this.roleTypes = roleTypes;
        this.profilePicture = profilePicture;
        this.person = person;

    }

    public boolean isFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(boolean facebookAccount) {
        this.facebookAccount = facebookAccount;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
