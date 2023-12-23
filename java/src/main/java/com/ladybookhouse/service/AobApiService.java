package com.ladybookhouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.model.Book;

import java.util.List;

public interface AobApiService {

//    List<Book> getInventoryList() throws JsonProcessingException;
    Book getBookInfo(String sku) throws NullPointerException, JsonProcessingException;

    List<Book> getInventoryList() throws NullPointerException, JsonProcessingException;

    List<Book> getNewArrivals() throws NullPointerException, JsonProcessingException;

    List<Book> getFeaturedItems() throws NullPointerException, JsonProcessingException;
}
