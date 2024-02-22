package com.ladybookhouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface BooksService {
    boolean fetchAndStoreBookDetailsIfNotExists(String sku) throws JsonProcessingException;
}
