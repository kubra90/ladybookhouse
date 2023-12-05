package com.ladybookhouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ladybookhouse.model.Book;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
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

    @Override
    public Book getInventoryList(String sku) throws NullPointerException, JsonProcessingException {

        HttpHeaders headers= createHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL + sku, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
//        return object booklist
//        List<Book> bookList = new ArrayList<>();

        JsonNode jsonNode = mapper.readTree(response.getBody()); //turns the string response into a tree with nodes for us to grab.
        JsonNode root = jsonNode.path("results");

//        for(int i=0; i <10; i++){
//
            String title = root.path(0).path("title").asText("");
        System.out.println(title);
            String author = root.path(0).path("author").asText("");
            double price = root.path(0).path("price").asDouble(0);
            String media = root.path(0).path("media").asText("");
            String image = root.path(0).path("image").asText("");
            String notes = root.path(0).path("notes").asText("");
            long isbn =root.path(0).path("isbn").asLong(0);
            int quantity = root.path(0).path("qty").asInt(0);
            String skuNumber =root.path(0).path("sku").asText("");
            System.out.println(skuNumber);
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
            temp.setPublisher(publisher);
            temp.setCondition(condition);
         return temp;
        }
//        System.out.println(bookList);

    }

    //call to print List of the books


