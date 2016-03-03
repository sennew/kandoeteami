package com.projects.wens.kandoeteami.user.data;

/**
 * Created by michaelkees on 03/03/16.
 */
public class Address {
    private String street;
    private String number;
    private String zip;
    private String city;

    public Address() {
    }

    public Address(String street, String number, String zip, String city) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
