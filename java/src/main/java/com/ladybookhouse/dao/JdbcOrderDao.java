package com.ladybookhouse.dao;

import com.ladybookhouse.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}


    @Override
    public boolean create(String firstname, String lastName, String country,
                          String zipcode, String city, String state,
                          String addressLine, String email, String phoneNumber,
                          String sku) {
        String orderSql = "insert into orders (firstname, lastname, country, city, " +
                "state, zipcode, address, phoneNumber, email, bookNo) " +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(orderSql, firstname, lastName, country,
                city, state, zipcode, addressLine, email, phoneNumber, sku) == 1;
    }

    private Order mapRowToOrder(SqlRowSet rs){
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setFirstName(rs.getString("firstname"));
        order.setLastName(rs.getString("lastname"));
        order.setCountry(rs.getString("country"));
        order.setState(rs.getString("state"));
        order.setCity(rs.getString("city"));
        order.setAddressLine(rs.getString("address"));
        order.setZipCode(rs.getString("zipcode"));
        order.setEmail(rs.getString("email"));
        order.setPhoneNumber(rs.getString("phoneNumber"));
        order.setInventoryCode(rs.getString("bookNo"));
        return order;
    }
}
