package com.ladybookhouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.AddressDao;
import com.ladybookhouse.dao.OrderDao;
import com.ladybookhouse.model.*;
import com.ladybookhouse.model.Address;
import com.ladybookhouse.model.Order;
import com.ladybookhouse.security.MyUserDetail;
import com.ladybookhouse.service.EmailServiceMessage;
import com.ladybookhouse.service.OrderService;
import com.ladybookhouse.service.PayPalClient;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class OrderController {

    private final PayPalClient payPalClient;

    private OrderDao orderDao;

    private AddressDao addressDao;


    private MyUserDetail userDetail;

    private OrderService orderService;

    @Autowired
    private EmailServiceMessage emailService;

    @Autowired
    public OrderController(OrderDao orderDao, AddressDao addressDao, OrderService orderService, PayPalClient payPalClient) {
        this.orderDao = orderDao;
        this.addressDao = addressDao;
        this.orderService = orderService;
        this.payPalClient =payPalClient;

    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequestDTO orderRequest){
        // get the billing and shipping addresses from the methods in orderRequest
        try{
        AddressDTO billingAddressDTO = orderRequest.getBillingAddress();
        AddressDTO shippingAddressDTO = orderRequest.getShippingAddress();

        // Convert AddressDTOs to model Address
        Address billingAddress = convertToAddress(billingAddressDTO);
        Address shippingAddress = convertToAddress(shippingAddressDTO);

            // Create and process payment before creating the order
            Payment payment =createPayment(orderRequest);
            if(!"approved".equals(payment.getState())){
                return ResponseEntity.badRequest().body("Payment was not successful.");
            }

        // calculate the totalPrice here
        Order order =orderDao.create(
                orderRequest.getEmail(),
                orderRequest.getInventoryCode(),
                orderRequest.isSaveAddress(),
                orderRequest.isInfoMail(),
                orderRequest.getMessage(),
                billingAddress,
                shippingAddress,
                orderRequest.getDeliveryOption()

        );
        if(order !=  null) {
            // Sending confirmation email
            System.out.println(order);
            System.out.println(order.getOrderId());
            String emailProvider = determineProvider(orderRequest.getEmail());
            String subject = "Order Confirmation";
            String body = "Thank you for your order! We are processing it and will keep you updated.";
            emailService.sendEmail(emailProvider, orderRequest.getEmail(), subject, body);

            return ResponseEntity.ok(order);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order.");
        }
    } catch (MessagingException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
    } catch(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred processing your order.");

    }

}

private Payment createPayment(OrderRequestDTO orderRequest) throws PayPalRESTException, JsonProcessingException {
    BigDecimal totalPrice=orderService.calculateTotalPrice(orderRequest);
    Amount amount = new Amount();
    amount.setCurrency("USD");
    amount.setTotal(String.format("%.2f", totalPrice));

    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transaction.setDescription("Your purchase description");

    List<Transaction> transactions = new ArrayList<>();
    transactions.add(transaction);

    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl("http://localhost:8080/cancel");
    redirectUrls.setReturnUrl("http://localhost:8080/process");

    Payment payment = new Payment();
    payment.setIntent("sale");
    payment.setPayer(new Payer());
    payment.setTransactions(transactions);
    payment.setRedirectUrls(redirectUrls);

    APIContext apiContext =payPalClient.getAPIContext();
    return payment.create(apiContext);
}

    private String determineProvider(String email) {
        if (email.toLowerCase().contains("@gmail.com")) {
            return "gmail";
        } else if (email.toLowerCase().contains("@yahoo.com")) {
            return "yahoo";
        } else if (email.toLowerCase().contains("@outlook.com")) {
            return "outlook";
        } else {
            return "other";
        }
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
