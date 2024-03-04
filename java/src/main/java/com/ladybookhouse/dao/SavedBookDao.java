package com.ladybookhouse.dao;

import com.ladybookhouse.model.savedBook;

import java.util.List;

public interface SavedBookDao {

    boolean create(String bookNo, String email);

    List<savedBook> findAllSavedBooks();

    List<savedBook> getSavedBooksByEmail(String email);

    boolean BookExistsBySku(String sku);

    boolean deleteBook(String sku, String email);

}
