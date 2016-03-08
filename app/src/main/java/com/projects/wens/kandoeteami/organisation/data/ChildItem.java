package com.projects.wens.kandoeteami.organisation.data;

/**
 * Created by senne on 04/03/2016.
 */
public class ChildItem {
    public String firstName;
    public String lastName;
    public String role;
    public String profilePictureUrl;

    public ChildItem(String firstName, String lastName, String role, String profilePictureUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

}
