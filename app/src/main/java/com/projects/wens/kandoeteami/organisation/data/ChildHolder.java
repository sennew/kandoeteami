package com.projects.wens.kandoeteami.organisation.data;

import android.widget.TextView;

/**
 * Created by senne on 05/03/2016.
 */
public class ChildHolder {
    public TextView firstName;
    public TextView lastName;
    public TextView role;


    public ChildHolder() {
    }

    public void setFirstName(TextView firstName) {
        this.firstName = firstName;
    }

    public void setLastName(TextView lastName) {
        this.lastName = lastName;
    }

    public void setRole(TextView role) {
        this.role = role;
    }

}
