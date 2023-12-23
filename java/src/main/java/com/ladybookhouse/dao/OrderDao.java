package com.ladybookhouse.dao;

public interface OrderDao {

    boolean create(String firstname, String lastName, String country, String
                   zipcode, String city, String state, String addressLine,
                   String email, Long phoneNumber, String sku);

}
