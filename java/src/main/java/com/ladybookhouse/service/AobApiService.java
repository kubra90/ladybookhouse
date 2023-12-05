package com.ladybookhouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.model.Book;

public interface AobApiService {

//    List<Book> getInventoryList() throws JsonProcessingException;
    Book getInventoryList(String sku) throws NullPointerException, JsonProcessingException;
}
