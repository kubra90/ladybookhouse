package com.ladybookhouse.service;

public interface BooksService {
    boolean fetchAndStoreBookDetailsIfNotExists(String sku);
}
