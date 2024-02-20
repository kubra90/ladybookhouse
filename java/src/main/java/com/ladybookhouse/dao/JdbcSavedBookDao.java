package com.ladybookhouse.dao;


import com.ladybookhouse.model.savedBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSavedBookDao implements savedBookDao{


    private final JdbcTemplate jdbcTemplate;

    public JdbcSavedBookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }

    @Override
    public boolean create(String sku, String email) {

        String bookSql = "insert into bookshelf (sku, email) values (?, ?)";

        return jdbcTemplate.update(bookSql, sku, email) == 1;
    }

    @Override
    public List<savedBook> findAllSavedBooks() {
        List<savedBook> savedBooks = new ArrayList<>();
        String sql = "select * from bookshelf";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            savedBook book = mapRowToSavedBook(results);
            savedBooks.add(book);
        }

        return savedBooks;


    }

    @Override
    public List<savedBook> getSavedBooksByEmail(String email) {
       if(email == null) throw new IllegalArgumentException("Email cannot be null!");
       List<savedBook> books= new ArrayList<>();
       for(savedBook book:this.findAllSavedBooks()){
           if(book.getEmail().equalsIgnoreCase(email)){
               books.add(book);
           }
       }

       if(books.isEmpty()){
//           create new customized exception
           throw new UsernameNotFoundException("bookshelf with this email cannot be found!");
       }
       return books;
    }

    private savedBook mapRowToSavedBook(SqlRowSet rs){
        savedBook book = new savedBook();
        book.setSku(rs.getString("sku"));
        book.setEmail(rs.getString("email"));
        return book;
    }
}
