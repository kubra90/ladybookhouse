package com.ladybookhouse.service;


import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PayPalClient {



    @Autowired
    private Environment environment;

    String clientId = environment.getProperty("paypal.client.id");
    String clientSecret = environment.getProperty("paypal.client.secret");

    @Value("${paypal.mode}")
    private String mode;

    public APIContext getAPIContext() {
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        return apiContext;
    }

}
