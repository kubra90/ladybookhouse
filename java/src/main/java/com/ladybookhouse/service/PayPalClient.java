package com.ladybookhouse.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class PayPalClient {



    @Autowired
    private Environment environment;

    private String clientId;
    private String clientSecret;
    private String mode;

    private Map<String, String> configMap = new HashMap<>();

    public PayPalClient() {
    }

    @PostConstruct
    private void init() {
        this.clientId = environment.getProperty("paypal.client.id");
        this.clientSecret = environment.getProperty("paypal.client.secret");
        this.mode = environment.getProperty("mode");

        // Create and configure the map with API and OAuth endpoints
        configMap.put("mode", mode);

        // Set service and OAuth endpoints based on the current mode
        String endpointPrefix = mode.equals("sandbox") ? "https://api-m.sandbox.paypal.com" : "https://api-m.paypal.com";
        configMap.put("service.EndPoint", endpointPrefix);
        configMap.put("oauth.EndPoint", endpointPrefix + "/v1/oauth2/token");

        // Optionally, print out configuration to verify correct settings (you can remove this later)

    }

    public APIContext getAPIContext() throws PayPalRESTException {


        System.out.println("Configured service endpoint: " + configMap.get("service.EndPoint"));
        System.out.println("Configured OAuth endpoint: " + configMap.get("oauth.EndPoint"));

        System.out.println(clientId);
        System.out.println(clientSecret);
        //            String accessToken = fetchAccessTokenManually();
//            System.out.println("Access token is: " + accessToken);

        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        String accessToken= apiContext.fetchAccessToken();
        System.out.println("new access "+ accessToken);
        apiContext.setMode(mode);
        apiContext.setConfigurationMap(configMap);
        System.out.println("apiContext is "+ apiContext);
        return apiContext;


    }

//    private String fetchAccessTokenManually() throws IOException, MalformedURLException {
//        String endpoint = configMap.get("oauth.EndPoint");
//        URL url = new URL(endpoint);
//        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//        httpConn.setRequestMethod("POST");
//
//        String credentials = clientId + ":" + clientSecret;
//        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
//
//        httpConn.setRequestProperty("Authorization", "Basic " + encodedCredentials);
//        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        httpConn.setRequestProperty("Accept", "application/json");
//
//        httpConn.setDoOutput(true);
//        try (OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream())) {
//            writer.write("grant_type=client_credentials");
//            writer.flush();
//        }
//
//        int responseCode = httpConn.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
//            Scanner scanner = new Scanner(httpConn.getInputStream()).useDelimiter("\\A");
//            String response = scanner.hasNext() ? scanner.next() : "";
//            scanner.close();
//
//           //  Extract the token from the response
//            return extractAccessTokenFromResponse(response);
//        } else {
//            Scanner scanner = new Scanner(httpConn.getErrorStream()).useDelimiter("\\A");
//            String errorResponse = scanner.hasNext() ? scanner.next() : "";
//            scanner.close();
//            throw new IOException("Failed to retrieve access token: " + errorResponse);
//        }
//    }

//    private String extractAccessTokenFromResponse(String jsonResponse) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode rootNode = mapper.readTree(jsonResponse);
//        return rootNode.get("access_token").asText();
//    }



}
