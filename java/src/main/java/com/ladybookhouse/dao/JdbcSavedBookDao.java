package com.ladybookhouse.dao;


import com.ladybookhouse.model.savedBook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSavedBookDao implements SavedBookDao {


    private final JdbcTemplate jdbcTemplate;

    public JdbcSavedBookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }

    @Override
    public boolean create(String sku, String email) {

        List<savedBook> books = this.getSavedBooksByEmail(email);
            boolean bookExistsInBookshelf = books.stream().anyMatch(book -> book.getSku().equalsIgnoreCase(sku));

            // If the book doesn't exist, proceed to insert
            if (!bookExistsInBookshelf) {
                String bookSql = "insert into bookshelf (sku, email) values (?, ?)";
                // If the update is successful, return true
                return jdbcTemplate.update(bookSql, sku, email) == 1;
            }
        throw new UsernameNotFoundException("this book added");

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
//
//       if(books.isEmpty()){
////           create new customized exception
//           throw new UsernameNotFoundException("bookshelf with this email cannot be found!");
//       }
       return books;
    }

    @Override
    public boolean BookExistsBySku(String sku) {
        String sql = "SELECT COUNT(*) from bookshelf WHERE sku= ?";

        Integer count =jdbcTemplate.queryForObject(sql, Integer.class, sku);
        return count != null && count> 0;

    }

    @Override
    public boolean deleteBook(String sku, String email) {
        String sql = "DELETE from bookshelf where email = ? and sku =?";
        int rowsEffected = jdbcTemplate.update(sql, email, sku);
        System.out.println("rowseffected: " + rowsEffected);
        return rowsEffected > 0;
    }

    private savedBook mapRowToSavedBook(SqlRowSet rs){
        savedBook book = new savedBook();
        book.setSku(rs.getString("sku"));
        book.setEmail(rs.getString("email"));
        return book;
    }
}
