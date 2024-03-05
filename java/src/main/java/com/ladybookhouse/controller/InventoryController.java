package com.ladybookhouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.BookListDTO;
import com.ladybookhouse.service.AobApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class InventoryController {

    @Autowired
    AobApiService service;

    //localhost:9000/ladybookhouse
    @RequestMapping(path = "/books/{sku}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("sku") String sku) throws JsonProcessingException {
        // Use 'sku' in your service method to fetch specific book information
        return service.getBookInfo(sku); // Assuming your service method can handle 'sku'
    }

    //get all inventory List
    @RequestMapping(path= "/books", method= RequestMethod.GET)
    public List<Book> getAllBooks() throws JsonProcessingException{
        return service.getInventoryList();
    }

//    @RequestMapping(path= "/new-arrivals", method=RequestMethod.GET)
//    public List<Book> getNewArrivals() throws JsonProcessingException{
//        return service.getNewArrivals();
//    }

    @RequestMapping(path= "/featured-items", method=RequestMethod.GET)
    public List<Book> getFetauredList() throws JsonProcessingException{
        return service.getFeaturedItems();
    }
}
