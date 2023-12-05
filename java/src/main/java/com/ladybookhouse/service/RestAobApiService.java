package com.ladybookhouse.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;


public class RestAobApiService implements AobApiService{

    RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://www.theartofbooks.com/api/2.0/item/";

    //create headers method

    private HttpHeaders createHeaders() {
        String username = System.getenv("AOB_USERNAME");
        String password = System.getenv("AOB_PASSWORD");

        HttpHeaders headers= new HttpHeaders();
        headers.add("AOB-AUTH-USER", username);
        headers.add("AOB-AUTH-PW", password);
        return headers;
    }

   //call to print List of the books


}
