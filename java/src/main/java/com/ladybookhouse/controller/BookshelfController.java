package com.ladybookhouse.controller;


import com.ladybookhouse.dao.SavedBookDao;
import com.ladybookhouse.model.savedBook;
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

    public BookshelfController(SavedBookDao bookDao){
        this.bookDao = bookDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/bookshelf", method= RequestMethod.POST)
    public void createBookshelf(@Valid @RequestBody savedBook book){
        bookDao.create(book.getSku(), book.getEmail());
    }

    @RequestMapping(path= "/your-books", method=RequestMethod.GET)
    public List<savedBook> getBookshelf(Principal principal){
        String email = principal.getName();
        return bookDao.getSavedBooksByEmail(email);
    }

}
