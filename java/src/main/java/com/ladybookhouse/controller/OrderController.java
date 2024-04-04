package com.ladybookhouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.AddressDao;
import com.ladybookhouse.dao.JdbcOrderDao;
import com.ladybookhouse.dao.OrderDao;
import com.ladybookhouse.model.*;
import com.ladybookhouse.security.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

private AddressDao addressDao;


private MyUserDetail userDetail;

public OrderController(OrderDao orderDao, AddressDao addressDao){
    this.orderDao = orderDao;
    this.addressDao =addressDao;
}
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
//    public void placeOrder(@Valid @RequestBody Order newOrder) {
//         orderDao.create(newOrder.getEmail(),newOrder.getInventoryCode(),
//    newOrder.isSaveAddress(), newOrder.isInfoMail(),  newOrder.getMessage(),
//                 newOrder.getBilling);
//
//
//    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/checkout", method= RequestMethod.POST)
    public void placeOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        // get the billing and shipping addresses from the methods in orderRequest
      AddressDTO billingAddressDTO = orderRequest.getBillingAddress();
      AddressDTO shippingAddressDTO = orderRequest.getShippingAddress();
        System.out.println(shippingAddressDTO.getCountry());
        // Convert AddressDTOs to your domain model Address objects if necessary
        Address billingAddress = convertToAddress(billingAddressDTO);
        Address shippingAddress = convertToAddress(shippingAddressDTO);
        orderDao.create(
                orderRequest.getEmail(),
                orderRequest.getInventoryCode(),
                orderRequest.isSaveAddress(),
                orderRequest.isInfoMail(),
                orderRequest.getMessage(),
                billingAddress,
                shippingAddress

        );


    }

    @RequestMapping(path="/orders", method= RequestMethod.GET)
    public List<Order> getOrders(Principal principal) {
    String email= principal.getName();
        System.out.println(principal);
        System.out.println(email);
    return orderDao.getOrderByEmail(email);
    }

    private Address convertToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setFirstname(addressDTO.getFirstname());
        address.setLastname(addressDTO.getLastname());
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipcode(addressDTO.getZipcode());
        address.setAddressLine(addressDTO.getAddressLine());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setAddressType(addressDTO.getAddressType());

        return address;
    }








}
