package com.ladybookhouse.dao;

import com.ladybookhouse.model.Book;

import java.util.List;

public interface BookDao {

    boolean save(Book book);

    boolean existsBySku(String sku);

    List<Book> findSavedBooksDetailByEMail(String email);



}
