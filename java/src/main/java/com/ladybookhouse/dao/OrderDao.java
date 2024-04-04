package com.ladybookhouse.dao;

import com.ladybookhouse.model.Address;
import com.ladybookhouse.model.Order;

import java.util.List;

public interface OrderDao {

    boolean create(String email, List<String> skus, boolean saveAddress, boolean infoMail, String message,
                   Address billingAddress, Address shippingAddress);

    List<Order> findAllOrders();

    Order getOrderByOrderId(int orderId);

    List<Order> getOrderByEmail(String email);



}
