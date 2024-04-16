package com.ladybookhouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.AddressDao;
import com.ladybookhouse.dao.JdbcOrderDao;
import com.ladybookhouse.dao.OrderDao;
import com.ladybookhouse.model.*;
import com.ladybookhouse.security.MyUserDetail;
import com.ladybookhouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
public class OrderController {

private OrderDao orderDao;

private AddressDao addressDao;


private MyUserDetail userDetail;

private OrderService orderService;

@Autowired
public OrderController(OrderDao orderDao, AddressDao addressDao, OrderService orderService){
    this.orderDao = orderDao;
    this.addressDao =addressDao;
    this.orderService = orderService;
}


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/checkout", method= RequestMethod.POST)
    public void placeOrder(@Valid @RequestBody OrderRequestDTO orderRequest) throws JsonProcessingException {
        // get the billing and shipping addresses from the methods in orderRequest
      AddressDTO billingAddressDTO = orderRequest.getBillingAddress();
      AddressDTO shippingAddressDTO = orderRequest.getShippingAddress();

        // Convert AddressDTOs to model Address
        Address billingAddress = convertToAddress(billingAddressDTO);
        Address shippingAddress = convertToAddress(shippingAddressDTO);

        // calculate the totalPrice here
        orderDao.create(
                orderRequest.getEmail(),
                orderRequest.getInventoryCode(),
                orderRequest.isSaveAddress(),
                orderRequest.isInfoMail(),
                orderRequest.getMessage(),
                billingAddress,
                shippingAddress,
                orderRequest.getDeliveryOption()

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
        address.setState(addressDTO.getStateInfo());
        address.setZipcode(addressDTO.getZipcode());
        address.setAddressLine(addressDTO.getAddressLine());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setAddressType(addressDTO.getAddressType());

        return address;
    }








}
