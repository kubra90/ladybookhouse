package com.ladybookhouse.service;

public interface BooksService {
    void fetchAndStoreBookDetailsIfNotExists(String sku);
}
