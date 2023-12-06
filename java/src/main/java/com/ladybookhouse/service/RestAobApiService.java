package com.ladybookhouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class RestAobApiService implements AobApiService{

    Category type = new Category();

    RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://www.theartofbooks.com/api/2.0/item/";
    @Autowired
    private Environment environment;

    //create headers method
    private HttpHeaders createHeaders() {
        String username = environment.getProperty("username");
        String password = environment.getProperty("password");
        HttpHeaders headers= new HttpHeaders();
        headers.add("AOB-AUTH-USER", username);
        headers.add("AOB-AUTH-PW", password);
        return headers;
    }

    @Override
    public Book getBookInfo(String sku) throws NullPointerException, JsonProcessingException {

        HttpHeaders headers= createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL + sku, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.getBody()); //turns the string response into a tree with nodes for us to grab.
        JsonNode root = jsonNode.path("results");

            String title = root.path(0).path("title").asText("");
            String author = root.path(0).path("author").asText("");
            double price = root.path(0).path("price").asDouble(0);
            String media = root.path(0).path("media").asText("");
            String image = root.path(0).path("image").asText("");
            String notes = root.path(0).path("notes").asText("");
            String isbn =root.path(0).path("isbn").asText("");
            int quantity = root.path(0).path("qty").asInt(0);
            String skuNumber =root.path(0).path("sku").asText("");

            String publisher =root.path(0).path("publisher").asText("");
            int condition =root.path(0).path("condition").asInt(0);


            Book temp = new Book();
            temp.setTitle(title); temp.setAuthor(author); temp.setImage(image);
            temp.setIsbn(isbn);
            temp.setPrice(price);
            temp.setMedia(media);
            temp.setNotes(notes);
            temp.setQuantity(quantity);
            temp.setInventoryCode(skuNumber);
            String typeBook =type.getBookTypes(temp.getInventoryCode());
            temp.setBookCategory(typeBook);
            temp.setPublisher(publisher);
            temp.setCondition(condition);
            String conditionUsed = type.getCondition(temp);
            temp.setConditionBook(conditionUsed);
         return temp;
        }

    @Override
    public List<Book> getInventoryList() throws NullPointerException, JsonProcessingException {
        HttpHeaders headers= createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        //to retrieve the response content in the expected format. getBody() important
        JsonNode jsonNode = mapper.readTree(response.getBody()); //turns the string response into a tree with nodes for us to grab.
        JsonNode root = jsonNode.path("results");
        //create new array List to save Book objects
        List<Book> bookList = new ArrayList<>();
        for(int i=0;i< root.size(); i++) {
            int quantity = root.path(i).path("qty").asInt(0);

            if(quantity> 0) {
                String title = root.path(i).path("title").asText("");
                System.out.println(title);
                String author = root.path(i).path("author").asText("");
                double price = root.path(i).path("price").asDouble(0);
                String media = root.path(i).path("media").asText("");
                String image = root.path(i).path("image").asText("");
                String notes = root.path(i).path("notes").asText("");
                String isbn = root.path(i).path("isbn").asText("");

                String skuNumber = root.path(i).path("sku").asText("");
                String publisher = root.path(i).path("publisher").asText("");
                int condition = root.path(i).path("condition").asInt(0);

                Book temp = new Book();
                temp.setTitle(title);
                temp.setAuthor(author);
                temp.setImage(image);
                temp.setIsbn(isbn);
                temp.setPrice(price);
                temp.setMedia(media);
                temp.setNotes(notes);
                temp.setQuantity(quantity);
                temp.setInventoryCode(skuNumber);
                temp.setPublisher(publisher);
                temp.setCondition(condition);
                bookList.add(temp);

            }

        }
        System.out.println(bookList.size());
        return bookList;

    }

    }




