package com.projects.wens.kandoeteami.organisation.data;

/**
 * Created by senne on 08/03/2016.
 */
public class OrganisationList {
    private int organisationId;
    private String organisationName;
    private String address;
    private String logoURL;
    private int aantalUsers;

    public OrganisationList() {
        //empty
    }

    //Constructor voor TESTING
    public OrganisationList(String organisationName, String address){
        this.organisationName = organisationName;
        this.address = address;
        this.aantalUsers = 0;
    }

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organistionId) {
        this.organisationId = organistionId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public int getAantalUsers() {
        return aantalUsers;
    }

    public void setAantalUsers(int aantalUsers) {
        this.aantalUsers = aantalUsers;
    }
}
