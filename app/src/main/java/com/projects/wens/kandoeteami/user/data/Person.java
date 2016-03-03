package com.projects.wens.kandoeteami.user.data;



/**
 * Created by michaelkees on 03/03/16.
 */
public class Person {
    private String firstname;
    private String lastname;
    private Address address;

    public Person() {
    }

    public Person(String firstname, String lastname, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
