package com.ladybookhouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.JdbcOrderDao;
import com.ladybookhouse.dao.OrderDao;
import com.ladybookhouse.model.Order;
import com.ladybookhouse.model.RegisterUserDto;
import com.ladybookhouse.model.User;
import com.ladybookhouse.security.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
public class OrderController {

private OrderDao orderDao;


private MyUserDetail userDetail;

public OrderController(OrderDao orderDao){
    this.orderDao = orderDao;
}
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public void placeOrder(@Valid @RequestBody Order newOrder) {
             orderDao.create(newOrder.getFirstName(),newOrder.getLastName(),
                     newOrder.getCountry(), newOrder.getZipCode(), newOrder.getCity(),
                     newOrder.getState(), newOrder.getAddressLine(), newOrder.getPhoneNumber(),
                     newOrder.getEmail(), newOrder.getInventoryCode(), newOrder.getMessage());
          }
    @RequestMapping(path="/orders", method= RequestMethod.GET)
    public List<Order> getOrders(Principal principal) {
    String email= principal.getName();
        System.out.println(principal);
        System.out.println(email);
    return orderDao.getOrderByEmail(email);
    }








}
