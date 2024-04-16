package com.ladybookhouse.dao;

import com.ladybookhouse.model.Address;
import com.ladybookhouse.model.AddressDTO;
import com.ladybookhouse.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {

    boolean create(String email, List<String> skus, boolean saveAddress, boolean infoMail, String message,
                   Address billingAddress, Address shippingAddress, String deliveryOption);

    List<Order> findAllOrders();

    Order getOrderByOrderId(int orderId);

    List<Order> getOrderByEmail(String email);



}
