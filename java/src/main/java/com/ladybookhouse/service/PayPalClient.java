package com.ladybookhouse.service;


import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PayPalClient {



    @Autowired
    private Environment environment;

    private String clientId;
    private String clientSecret;
    private String mode;

    public PayPalClient() {
    }

    @PostConstruct
    private void init() {
        this.clientId = environment.getProperty("paypal.client.id");
        this.clientSecret = environment.getProperty("paypal.client.secret");
        this.mode = environment.getProperty("mode");
    }

    public APIContext getAPIContext() {
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        return apiContext;
    }

}
