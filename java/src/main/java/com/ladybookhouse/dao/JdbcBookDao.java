package com.ladybookhouse.dao;

import com.ladybookhouse.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcBookDao implements  BookDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }
    @Override
    public boolean save(Book book) {
        String sql = "INSERT INTO books (isbn, title, image, price, media, publisher, " +
        "author, weight, notes, quantity, inventory_code, condition, category, listed_date, " +
        "condition_as_text, used_book) VALUES (?,?,?,?,?,?, ?,?,?,?,?,?,? ,?, ?,?) ";

        return jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getImage(),
                book.getPrice(), book.getMedia(), book.getPublisher(), book.getAuthor(),
                book.getWeight(), book.getNotes(), book.getQuantity(), book.getInventoryCode(),
                book.getCondition(), book.getCategory(), book.getListedDate(), book.getConditionAsText(),
                book.getUsedBook()
                ) == 1;
    }

    @Override
    public boolean existsBySku(String sku) {
        String sql = "SELECT COUNT(*) from books where inventory_code= ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, sku);
        return count != null && count>0;
    }

    @Override
    public List<Book> findSavedBooksDetailByEMail(String email) {
        List<Book> books = new ArrayList<>();
        String sql= "SELECT b.* from books b " +
                "INNER JOIN bookshelf bs ON b.inventory_code = bs.sku "+
                "WHERE bs.email =? ";
       SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, email);
       while(rowSet.next()) {
           books.add(mapRowToSaveBook(rowSet));
       }
       return books;
    }


    private Book mapRowToSaveBook(SqlRowSet rs){
        Book book = new Book();
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setImage(rs.getString("image"));
        book.setPrice(rs.getBigDecimal("price"));
        book.setMedia(rs.getString("media"));
        book.setPublisher(rs.getString("publisher"));
        book.setAuthor(rs.getString("author"));
        book.setWeight(rs.getInt("weight"));
        book.setNotes(rs.getString("notes"));
        book.setQuantity(rs.getInt("quantity"));
        book.setInventoryCode(rs.getString("inventory_code"));
        book.setCondition(rs.getInt("condition"));
        book.setCategory(rs.getString("category"));
        book.setListedDate(rs.getDate("listed_date").toLocalDate());
        book.setConditionAsText(rs.getString("condition_as_text"));
        book.setUsedBook(rs.getString("used_book"));
        return book;

    }
}
