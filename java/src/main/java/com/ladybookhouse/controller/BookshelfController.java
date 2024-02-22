package com.ladybookhouse.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.SavedBookDao;
import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.savedBook;
import com.ladybookhouse.service.SavedBooksService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class BookshelfController {

    private SavedBookDao bookDao;

    private SavedBooksService savedBooksService;

    public BookshelfController(SavedBookDao bookDao, SavedBooksService savedBooksService){
        this.bookDao = bookDao;
        this.savedBooksService = savedBooksService;
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
            bookDao.create(sku, email);
        }else{
            bookDao.create(sku, email);
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

    }
}
