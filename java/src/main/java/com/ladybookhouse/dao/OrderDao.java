package com.ladybookhouse.dao;

import com.ladybookhouse.model.Order;

import java.util.List;

public interface OrderDao {

    boolean create(String firstname, String lastName, String country, String
                   zipcode, String city, String state, String addressLine,
                   String email, String phoneNumber, List<String> skus, String message);

    List<Order> findAllOrders();

    Order getOrderByOrderId(int orderId);

    List<Order> getOrderByEmail(String email);


}
