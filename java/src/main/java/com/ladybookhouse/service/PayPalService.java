package com.ladybookhouse.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import io.jsonwebtoken.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayPalService {
 @Autowired
 private PayPalClient payPalClient;

}
