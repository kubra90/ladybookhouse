package com.ladybookhouse.service;


import com.ladybookhouse.dao.SavedBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavedBooksService implements BooksService{


    private final SavedBookDao savedBookDao;
    private final BooksService booksService;



    @Autowired
    public SavedBooksService(SavedBookDao savedBookDao, BooksService booksService){
        this.booksService = booksService;
        this.savedBookDao = savedBookDao;
    }
//    @Override
//    public boolean addBookToUserShelf(String sku, String email) {
//
//
//
//        return savedBookDao.create(sku, email);
//    }

//    @Override
//    public void fetchAndStoreBookDetailsIfNotExists(String sku, String email) {
//        booksService.fetchAndStoreBookDetailsIfNotExists(sku);
//
//        return savedBookDao.create(sku, email);
//    }

    @Override
    public boolean fetchAndStoreBookDetailsIfNotExists(String sku) {

    }
}
