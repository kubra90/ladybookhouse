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
import java.util.Map;


@RestController
@CrossOrigin
public class OrderController {

    private OrderDao orderDao;

    private AddressDao addressDao;


    private MyUserDetail userDetail;

    private OrderService orderService;

    @Autowired
    private PayPalClient payPalClient;

    @Autowired
    private EmailServiceMessage emailService;

    @Autowired
    public OrderController(OrderDao orderDao, AddressDao addressDao, OrderService orderService) {
        this.orderDao = orderDao;
        this.addressDao = addressDao;
        this.orderService = orderService;


    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequestDTO orderRequest){
        // get the billing and shipping addresses from the methods in orderRequest
        try{
//        AddressDTO billingAddressDTO = orderRequest.getBillingAddress();
//        AddressDTO shippingAddressDTO = orderRequest.getShippingAddress();
//
//        // Convert AddressDTOs to model Address
//        Address billingAddress = convertToAddress(billingAddressDTO);
//        Address shippingAddress = convertToAddress(shippingAddressDTO);

            // Create and process payment before creating the order
            Payment payment =createPayment(orderRequest);
            String approvalLink = payment.getLinks().stream()
                    .filter(link -> "approval_url".equals(link.getRel()))
                    .findFirst()
                    .map(link -> link.getHref())
                    .orElseThrow(() -> new IllegalStateException("Approval URL not found in payment"));
            return ResponseEntity.ok(Map.of("approvalUrl", approvalLink, "paymentId", payment.getId()));
        } catch (PayPalRESTException | JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment: " + e.getMessage());
        }


            // calculate the totalPrice here
//        Order order =orderDao.create(
//                orderRequest.getEmail(),
//                orderRequest.getInventoryCode(),
//                orderRequest.isSaveAddress(),
//                orderRequest.isInfoMail(),
//                orderRequest.getMessage(),
//                billingAddress,
//                shippingAddress,
//                orderRequest.getDeliveryOption()
//
//        );
//        if(order !=  null) {
//            // Sending confirmation email
//            System.out.println(order);
//            System.out.println(order.getOrderId());
//            String emailProvider = determineProvider(orderRequest.getEmail());
//            String subject = "Order Confirmation";
//            String body = "Thank you for your order! We are processing it and will keep you updated.";
//            emailService.sendEmail(emailProvider, orderRequest.getEmail(), subject, body);
//
//            return ResponseEntity.ok(order);
//        }else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order.");
//        }
//    } catch (MessagingException e){
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
//    } catch(Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred processing your order.");
//
//    }

}

@PostMapping("/execute-payment")
public ResponseEntity<?> executePayment(@RequestBody PaymentExecutionRequest request){
        try {

//            execute the Paypal payment

            Payment executedPayment = executePaymentLogic(request.getPaymentId(), request.getPayerId());

            if ("approved".equals(executedPayment.getState())) {
                OrderRequestDTO orderDTO = request.getRequestDTO();
                Order order = createOrder(executedPayment, orderDTO);
                return ResponseEntity.ok(order);

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment not approved");
            }
        }catch(PayPalRESTException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to execute payment" + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an error occurred: " + e.getMessage());
        }
}

    private Order createOrder(Payment executedPayment, OrderRequestDTO orderRequest) {
        try {
            // Assume you can extract a transaction ID or similar from the executed payment if needed
            String paymentConfirmationNumber = executedPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId();

            AddressDTO billingAddressDTO = orderRequest.getBillingAddress();
            AddressDTO shippingAddressDTO = orderRequest.getShippingAddress();

            // Convert AddressDTOs to model Address
            Address billingAddress = convertToAddress(billingAddressDTO);
            Address shippingAddress = convertToAddress(shippingAddressDTO);
            // Create the order in the database
            Order order = orderDao.create(
                    orderRequest.getEmail(),
                    orderRequest.getInventoryCode(),
                    orderRequest.isSaveAddress(),
                    orderRequest.isInfoMail(),
                    orderRequest.getMessage(),
                    billingAddress,
                    shippingAddress,
                    orderRequest.getDeliveryOption(),
                    orderRequest.getPaypalNum()
            );

            // Check if the order was successfully created
            if (order != null) {
                order.setPaymentConfirmationNumber(paymentConfirmationNumber); // Set the payment confirmation number from PayPal
                 // Assuming your DAO has a save method to update the order

                // Send a confirmation email
                String emailProvider = determineProvider(orderRequest.getEmail());
                String subject = "Order Confirmation";
                String body = "Thank you for your order! We are processing it and will keep you updated.";
                emailService.sendEmail(emailProvider, orderRequest.getEmail(), subject, body);

                return order;
            } else {
                throw new IllegalStateException("Failed to create order.");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("An error occurred processing your order: " + e.getMessage());
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
    for(Transaction a: transactions){
        System.out.println(a.getAmount());
        System.out.println(a);
    }

    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl("http://localhost:9000/cancel");
    redirectUrls.setReturnUrl("http://localhost:9000/payment-success"); //url after concluding the payment in Paypal


    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");

    Payment payment = new Payment();
    payment.setIntent("sale");
    payment.setPayer(payer);
    payment.setTransactions(transactions);
    payment.setRedirectUrls(redirectUrls);

    APIContext apiContext =payPalClient.getAPIContext();
    System.out.println("Using client ID: " + apiContext.getClientID());
    System.out.println("Using Auth Token: " + apiContext.getClientSecret());
    try {
        Payment createdPayment = payment.create(apiContext);
        System.out.println("Created Payment: " + createdPayment.toJSON()); // Log created payment object
        return createdPayment;
    } catch (PayPalRESTException e) {
        System.err.println("PayPal API call failed with details: " + e.getDetails());
        if (e.getDetails() != null) {
            System.err.println("Error details: " + e.getDetails().getMessage());
        }
        throw e;
    }
}

    private Payment executePaymentLogic(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = payPalClient.getAPIContext();
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        // Execute the payment
        Payment executedPayment = payment.execute(apiContext, paymentExecution);

        // Optionally log the payment for debugging and auditing purposes
        System.out.println("Executed Payment: " + executedPayment.toJSON());

        return executedPayment;
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
