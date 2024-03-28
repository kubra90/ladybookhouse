package com.ladybookhouse.dao;

import com.ladybookhouse.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;


@Component
public class JdbcOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;



    public JdbcOrderDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}



    @Override
    public boolean create(String firstname, String lastName, String country,
                          String zipcode, String city, String state,
                          String addressLine, String email, String phoneNumber,
                          List<String> inventoryCode, boolean saveAddress, boolean infoMail, String message) {
        try {
            // Insert the order into 'orders' table
            String orderSql = "INSERT INTO orders (firstname, lastname, country, city, " +
                    "state, zipcode, address, email, phoneNumber, saveAddress, infoMail, message) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING order_id";

            Integer orderId = jdbcTemplate.queryForObject(orderSql, new Object[]{firstname, lastName, country, city, state, zipcode, addressLine, email, phoneNumber, saveAddress, infoMail, message}, Integer.class);

            if (orderId == null) {
                return false;
            }

            // Inserting books for the order
            String bookSql = "INSERT INTO order_books (order_id, bookNo) VALUES (?, ?)";
            for (String sku : inventoryCode) {
                jdbcTemplate.update(bookSql, orderId, sku);
            }
            System.out.println("Email used for query: " + email);

//            check orderId in books_order table than return true
//            String checkSql = "SELECT order_books_id from order_books where order_id = ?";
//            Integer orderBookId = jdbcTemplate.queryForObject(checkSql, new Object[]{orderId}, Integer.class);
////
//           if (orderBookId != null)
                if (saveAddress) {
                    // Attempt to find user ID by email
                    String findUserIdBySql = "SELECT user_id FROM users WHERE email=?";
                    Integer userId = jdbcTemplate.queryForObject(findUserIdBySql, new Object[]{email}, Integer.class);
                    System.out.println(userId);
                    if (userId != null) {
                        // Insert new address and link it to the user
                        String addressSql = "INSERT INTO address (email, firstname, lastname, country, city, state, zipcode, address, phoneNumber) "
                                + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?) RETURNING address_id";
                        Integer addressId = jdbcTemplate.queryForObject(addressSql, new Object[]{email, firstname, lastName, country, city, state, zipcode, addressLine, phoneNumber}, Integer.class);
                        System.out.println(addressId);
                        if (addressId != null) {
                            String linkAddressToUserSql = "INSERT INTO user_addresses(user_id, address_id) VALUES (?, ?)";
                            jdbcTemplate.update(linkAddressToUserSql, userId, addressId);
                            return true;
                        }
                    }

            }

                return true; // Order and book links successfully created

            } catch(EmptyResultDataAccessException e){
                // Handle case where no rows are returned for a query expecting a single row
                System.out.println("No results found where one was expected: " + e.getMessage());
                return false;
            } catch(DataAccessException e){
                // General handler for other data access exceptions
                System.out.println("Data access error: " + e.getMessage());
                return false;
            }
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
        order.setPhoneNumber(rs.getString("phoneNumber"));
        order.setEmail(rs.getString("email"));
        order.setSaveAddress(rs.getBoolean("saveAddress"));
        order.setInfoMail(rs.getBoolean("infoMail"));
        order.setMessage(rs.getString("message"));
        order.setOrderDateTime(Objects.requireNonNull(rs.getTimestamp("created_at")).toLocalDateTime());
        return order;
    }
}
