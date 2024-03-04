package com.ladybookhouse.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.BookDao;
import com.ladybookhouse.dao.SavedBookDao;
import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.savedBook;
import com.ladybookhouse.service.SavedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class BookshelfController {



    private SavedBookDao savedBookDao;


    private BookDao bookDao;

    @Autowired
    private SavedBooksService savedBooksService;



@Autowired
    public BookshelfController(SavedBookDao savedBookDao, BookDao bookDao){
        this.savedBookDao = savedBookDao;
        this.bookDao =bookDao;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value="/bookshelf", method= RequestMethod.POST)
//    public void createBookshelf(@Valid @RequestBody savedBook book){
//        bookDao.create(book.getSku(), book.getEmail());
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/bookshelf", method= RequestMethod.POST)
    public void addBookToShelf(@RequestParam String sku, Principal principal) throws JsonProcessingException {
        String email = principal.getName();
        boolean isBookFetchedAndSaved = savedBooksService.fetchAndStoreBookDetailsIfNotExists(sku);
        if (isBookFetchedAndSaved) {
            savedBookDao.create(sku, email);
        }else{
            savedBookDao.create(sku, email);
        }
    }

//    @RequestMapping(path= "/your-books", method=RequestMethod.GET)
//    public List<savedBook> getBookshelf(Principal principal){
//        String email = principal.getName();
//        return bookDao.getSavedBooksByEmail(email);
//    }

    @RequestMapping(path= "/your-books", method= RequestMethod.GET)
    public List<Book> getBookshelf(Principal principal){
        String email = principal.getName();
        return bookDao.findSavedBooksDetailByEMail(email);

    }

    @DeleteMapping(path= "/removeBook")
    public boolean deleteBook(Principal principal, @RequestParam String sku){
    String email = principal.getName();
        System.out.println("sku: " + sku + " email: " + email);
    return savedBookDao.deleteBook(sku, email);
    }
}
