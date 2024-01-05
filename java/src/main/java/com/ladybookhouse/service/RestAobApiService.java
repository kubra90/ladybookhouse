package com.ladybookhouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.Category;
import com.sun.jdi.IntegerValue;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Component
public class RestAobApiService implements AobApiService{

    Category type = new Category();

    RestTemplate restTemplate = new RestTemplate();

    private final String API_URL = "https://www.theartofbooks.com/api/2.0/item/";
    @Autowired
    private Environment environment;

    public RestAobApiService() {
    }

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
//            double price = root.path(0).path("price").asDouble(0.00);
        String priceStr = root.path(0).path("price").asText("0.00");
            String media = root.path(0).path("media").asText("");
            String image = root.path(0).path("image").asText("");
            String notes = root.path(0).path("notes").asText("");
            String isbn =root.path(0).path("isbn").asText("");
            int quantity = root.path(0).path("qty").asInt(0);
            String skuNumber =root.path(0).path("sku").asText("");
            String listedDateString = root.path(0).path("listed_date").asText("");
            LocalDate listedDate = listedDateString.isEmpty() ? null : LocalDate.parse(listedDateString);



        String publisher =root.path(0).path("publisher").asText("");
            int condition =root.path(0).path("condition").asInt(0);


            Book temp = new Book();
            temp.setTitle(title); temp.setAuthor(author); temp.setImage(image);
            temp.setIsbn(isbn);
        BigDecimal currentPrice = new BigDecimal(priceStr).setScale(2, BigDecimal.ROUND_HALF_UP);
        temp.setPrice(currentPrice);
            temp.setMedia(media);
            temp.setNotes(notes);
            temp.setQuantity(quantity);
            temp.setInventoryCode(skuNumber);
            String typeBook =type.getBookTypes(temp.getInventoryCode());
            temp.setCategory(typeBook);
            temp.setPublisher(publisher);
            temp.setListedDate(listedDate);
       temp.setCondition(condition);
        String conditionText = temp.getConditionAsText(); // Get the textual description

            String conditionUsed = type.getCondition(temp);
            temp.setUsedBook(conditionUsed);
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
//                System.out.println(title);
                String author = root.path(i).path("author").asText("");
                String priceStr = root.path(i).path("price").asText("0.00");
                String media = root.path(i).path("media").asText("");
                String image = root.path(i).path("image").asText("");
                String notes = root.path(i).path("notes").asText("");
                String isbn = root.path(i).path("isbn").asText("");

                String skuNumber = root.path(i).path("sku").asText("");
                String publisher = root.path(i).path("publisher").asText("");
                int condition = root.path(i).path("condition").asInt(0);
                String listedDateString = root.path(i).path("listed_date").asText("");
                LocalDate listedDate = listedDateString.isEmpty() ? null : LocalDate.parse(listedDateString);

                Book temp = new Book();
                temp.setTitle(title); temp.setAuthor(author); temp.setImage(image);
                temp.setIsbn(isbn);
                BigDecimal currentPrice = new BigDecimal(priceStr).setScale(2, BigDecimal.ROUND_HALF_UP);
                temp.setPrice(currentPrice);
                System.out.println("Price String: " + priceStr); // Debugging line
                System.out.println("Current Price: " + currentPrice); // Debugging line
                temp.setMedia(media);
                temp.setNotes(notes);
                temp.setQuantity(quantity);
                temp.setInventoryCode(skuNumber);
                String typeBook =type.getBookTypes(temp.getInventoryCode());
                temp.setCategory(typeBook);
                temp.setPublisher(publisher);
                temp.setCondition(condition);
                temp.setListedDate(listedDate);
                String conditionText = temp.getConditionAsText(); // Get the textual description

                String conditionUsed = type.getCondition(temp);
                temp.setUsedBook(conditionUsed);
               bookList.add(temp);

            }

        }
        System.out.println(bookList.size());
        return bookList;

    }

    @Override
    public List<Book> getNewArrivals() throws NullPointerException, JsonProcessingException {
        List<Book> books = getInventoryList();

        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        List<Book> newArrival = new ArrayList<>();

        for (Book book : books) {
            LocalDate listedDate = book.getListedDate();
            if (listedDate != null && listedDate.isAfter(threeMonthsAgo)) {
                newArrival.add(book);
            }
        }


        // Sort the newArrival list by listedDate in descending order
        newArrival.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b2.getListedDate().compareTo(b1.getListedDate());
            }
        });
        System.out.println(newArrival.size());
        return newArrival;

    }

    @Override
    public List<Book> getFeaturedItems() throws NullPointerException, JsonProcessingException {
       List<Book> books =getInventoryList();

       List<Book> featuredBooks = new ArrayList<>();

       for(Book book: books){
           String code = book.getInventoryCode();
           if(code.contains("SSL") || code.contains("SS") || code.contains("NS") ||
                   code.contains("AM") || code.contains("D")){
               featuredBooks.add(book);
           }
       }
        System.out.println(featuredBooks.size());
       return featuredBooks;
    }
}




