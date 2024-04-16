package com.ladybookhouse.dao;

import com.ladybookhouse.model.Address;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Component
public class JdbcOrderDao implements OrderDao {

    private final JdbcTemplate jdbcTemplate;



    public JdbcOrderDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

   @Override
   public boolean create(String email, List<String> inventoryCode, boolean saveAddress,
                         boolean infoMail, String message, Address billingAddress, Address shippingAddress, String deliveryOption,
                         BigDecimal totalPrice)
   {

        if(!checkInventoryCodesExistsInOrders(inventoryCode)) {
            Integer shippingAddressId;
            Integer billingAddressId;
            try {

                shippingAddressId = insertAddress(shippingAddress);
                System.out.println(shippingAddressId);
                System.out.println(shippingAddress);
                if (!shippingAddress.equals(billingAddress)) {
                    billingAddressId = insertAddress(billingAddress);
                } else {
                    billingAddressId = shippingAddressId;
                }

//           }

                System.out.println("Billing Address: " + billingAddress);
                System.out.println("Shipping Address: " + shippingAddress);


                // Now insert the order linking it to the address IDs (if addresses were saved)
                String orderSql = "INSERT INTO orders (email, billing_address_id, shipping_address_id, total_price, delivery_option,  saveAddress, infoMail, message, created_at) " +
                        "VALUES (?, ?, ?, ?,?,?, ?, ?, ?) RETURNING order_id";

                Integer orderId = jdbcTemplate.queryForObject(orderSql, Integer.class, email, billingAddressId, shippingAddressId, totalPrice, deliveryOption, saveAddress, infoMail, message, LocalDateTime.now());

                if (orderId != null)
                    // If the order was successfully created, insert related books
                    insertOrderBooks(inventoryCode, orderId);

                return true;

            } catch (EmptyResultDataAccessException e) {
                return false;
                // Log exception
            } catch (DataAccessException e) {
                // General handler for other data access exceptions
                System.out.println("Data access error: " + e.getMessage());
                return false;
            }
        }
        throw new UsernameNotFoundException("sku number used in previous orders");
   }

//    @Override
//    public boolean create(String firstname, String lastName, String country,
//                          String zipcode, String city, String state,
//                          String addressLine, String email, String phoneNumber,
//                          List<String> inventoryCode, boolean saveAddress, boolean infoMail, String message) {
//        try {
//            // Insert the order into 'orders' table
//            String orderSql = "INSERT INTO orders (firstname, lastname, country, city, " +
//                    "state, zipcode, address, email, phoneNumber, saveAddress, infoMail, message) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING order_id";
//
//            Integer orderId = jdbcTemplate.queryForObject(orderSql, new Object[]{firstname, lastName, country, city, state, zipcode, addressLine, email, phoneNumber, saveAddress, infoMail, message}, Integer.class);
//
//            if (orderId == null) {
//                return false;
//            }
//
//            // Inserting books for the order
//            String bookSql = "INSERT INTO order_books (order_id, bookNo) VALUES (?, ?)";
//            for (String sku : inventoryCode) {
//                jdbcTemplate.update(bookSql, orderId, sku);
//            }
//            System.out.println("Email used for query: " + email);
//
////            check orderId in books_order table than return true
////            String checkSql = "SELECT order_books_id from order_books where order_id = ?";
////            Integer orderBookId = jdbcTemplate.queryForObject(checkSql, new Object[]{orderId}, Integer.class);
//////
////           if (orderBookId != null)
//                if (saveAddress) {
//                    // Attempt to find user ID by email
//                    String findUserIdBySql = "SELECT user_id FROM users WHERE email=?";
//                    Integer userId = jdbcTemplate.queryForObject(findUserIdBySql, new Object[]{email}, Integer.class);
//                    System.out.println(userId);
//                    if (userId != null) {
//                        // Insert new address and link it to the user
//                        String addressSql = "INSERT INTO address (email, firstname, lastname, country, city, state, zipcode, address, phoneNumber) "
//                                + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?) RETURNING address_id";
//                        Integer addressId = jdbcTemplate.queryForObject(addressSql, new Object[]{email, firstname, lastName, country, city, state, zipcode, addressLine, phoneNumber}, Integer.class);
//                        System.out.println(addressId);
//                        if (addressId != null) {
//                            String linkAddressToUserSql = "INSERT INTO user_addresses(user_id, address_id) VALUES (?, ?)";
//                            jdbcTemplate.update(linkAddressToUserSql, userId, addressId);
//                            return true;
//                        }
//                    }
//
//            }
//
//                return true; // Order and book links successfully created
//
//            } catch(EmptyResultDataAccessException e){
//                // Handle case where no rows are returned for a query expecting a single row
//                System.out.println("No results found where one was expected: " + e.getMessage());
//                return false;
//            } catch(DataAccessException e){
//                // General handler for other data access exceptions
//                System.out.println("Data access error: " + e.getMessage());
//                return false;
//            }
//        }


    private boolean checkUserRegistration(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }

    private void insertOrderBooks(List<String> inventoryCode, Integer orderId){
        String orderSql = "INSERT INTO order_books (order_id, bookNo) VALUES (?, ?)";
            for (String sku : inventoryCode) {
                jdbcTemplate.update(orderSql, orderId, sku);
        }
    }

    private boolean checkInventoryCodesExistsInOrders(List<String> inventoryCode) {
        Integer count;
        for (String sku : inventoryCode) {
            String orderSql = "SELECT COUNT(*) from order_books where bookNo=?";
            count = jdbcTemplate.queryForObject(orderSql, new Object[]{sku}, Integer.class);
            if(count > 0){
                return true;
            }
        }
        return false;
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


    private Integer insertAddress(Address address) {

               if(address != null){
                   String addressSql = "INSERT INTO address (email, firstname, lastname, country, city, state, zipcode, addressLine, phoneNumber, addressType) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING address_id";
                  Integer addressId = jdbcTemplate.queryForObject(addressSql, new Object[]{ address.getEmail(), address.getFirstname(), address.getLastname(),
                           address.getCountry(), address.getCity(), address.getState(), address.getZipcode(), address.getAddressLine(),
                           address.getPhoneNumber(), address.getAddressType()}, Integer.class);

                  return addressId;
               }else{
                   throw new UsernameNotFoundException("address is null" + address + " not found");
               }



    }


    private Order mapRowToOrder(SqlRowSet rs){
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setEmail(rs.getString("email"));
        order.setBillingId(rs.getInt("billing_address_id"));
        order.setShippingId(rs.getInt("shipping_address_id"));
        order.setDeliveryOption(rs.getString("delivery_option"));
        order.setTotalPrice(rs.getBigDecimal("total_price"));
        order.setSaveAddress(rs.getBoolean("saveAddress"));
        order.setInfoMail(rs.getBoolean("infoMail"));
        order.setMessage(rs.getString("message"));
        order.setOrderDateTime(Objects.requireNonNull(rs.getTimestamp("created_at")).toLocalDateTime());
        return order;
    }
}
