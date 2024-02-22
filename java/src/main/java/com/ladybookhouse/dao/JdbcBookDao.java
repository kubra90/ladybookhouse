package com.ladybookhouse.dao;

import com.ladybookhouse.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
        String sql = "SELECT COUNT(*) from books where sku= ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, sku);
        return count != null && count>0;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }
}
