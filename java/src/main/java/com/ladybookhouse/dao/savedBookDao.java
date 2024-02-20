package com.ladybookhouse.dao;

import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.savedBook;

import java.util.List;

public interface savedBookDao {

    boolean create(String bookNo, String email);

    List<savedBook> findAllSavedBooks();

    List<savedBook> getSavedBooksByEmail(String email);

}