package com.ladybookhouse.model;

public class Order {
    private int orderId;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String zipCode;
    private String country;
    private String state;
    private String city;
    private String email;
    private String phoneNumber;
    private String inventoryCode;

    private String message;

    public Order() {
    }


    public Order(int orderId, String firstName, String lastName, String addressLine,
                 String zipCode, String country, String state, String city, String
                         email, String phoneNumber, String inventoryCode, String message) {
        this.orderId = orderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine = addressLine;
        this.zipCode = zipCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.inventoryCode = inventoryCode;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }
}
