package com.ladybookhouse.model;

public class Address {
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private String state;
    private String zipcode;
    private String addressLine;
    private String phoneNumber;
    private String email;

    public Address(){

    }

    public Address(String firstname, String lastname, String country, String city, String state, String zipcode, String addressLine, String phoneNumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.addressLine = addressLine;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
