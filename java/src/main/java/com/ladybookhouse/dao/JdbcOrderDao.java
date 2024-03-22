package com.ladybookhouse.dao;

import com.ladybookhouse.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class JdbcOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}


//    @Override
//    public boolean create(String firstname, String lastName, String country,
//                          String zipcode, String city, String state,
//                          String addressLine, String email, String phoneNumber,
//                          String sku, String message) {
//        String orderSql = "insert into orders (firstname, lastname, country, city, " +
//                "state, zipcode, address, phoneNumber, email, bookNo, message) " +
//                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(orderSql, firstname, lastName, country,
//                city, state, zipcode, addressLine, email, phoneNumber, sku, message) == 1;
//    }

    @Override
    public boolean create(String firstname, String lastName, String country,
                          String zipcode, String city, String state,
                          String addressLine, String email, String phoneNumber,
                          List<String> inventoryCode, String message) {
        // Insert the order into 'orders' table
        String orderSql = "INSERT INTO orders (firstname, lastname, country, city, " +
                "state, zipcode, address, phoneNumber, email, message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING order_id";

        Integer orderId = jdbcTemplate.queryForObject(orderSql, new Object[]{firstname, lastName, country, city, state, zipcode, addressLine, email, phoneNumber, message}, Integer.class);
        if(orderId == null){
            return false;
        }
        String bookSql = "INSERT INTO order_books (order_id, bookNo) VALUES (?, ?)";
        for (String sku : inventoryCode) {
            jdbcTemplate.update(bookSql, orderId, sku);
        }

        return true; // Order and book links successfully created
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql= "select * from orders";

        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Order order=mapRowToOrder(results);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        String sql= "SELECT * from orders WHERE order_id= ?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql, orderId);
        if(results.next()){
            return mapRowToOrder(results);
        }else {
            return null;
        }
    }

    @Override
    public List<Order> getOrderByEmail(String email) {
        if(email == null) throw new IllegalArgumentException("Email cannot be null");
        List<Order> orders= new ArrayList<>();
        for(Order order: this.findAllOrders()){
            if(order.getEmail().equalsIgnoreCase(email)){
                orders.add(order);
            }
        }
        if (orders.isEmpty()) {
            // create new customized exception here
            throw new UsernameNotFoundException("Order with this email " + email + " was not found!");
        }
        return orders;
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
        order.setMessage(rs.getString("message"));
        order.setOrderDateTime(Objects.requireNonNull(rs.getTimestamp("created_at")).toLocalDateTime());
        return order;
    }
}
