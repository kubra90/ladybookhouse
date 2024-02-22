package com.ladybookhouse.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.dao.BookDao;
import com.ladybookhouse.dao.SavedBookDao;
import com.ladybookhouse.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SavedBooksService implements BooksService{


    private  BooksService booksService;


    @Autowired
    private BookDao bookDao;


    @Autowired
    private  RestAobApiService bookApiService;


  public SavedBooksService(){}

    @Override
    public boolean fetchAndStoreBookDetailsIfNotExists(String sku) throws JsonProcessingException {
       if(!bookDao.existsBySku(sku)){
           Book book = bookApiService.getBookInfo(sku);
           if(book != null){
               bookDao.save(book);
               return true;
           }

           return false;  //book details could not be fetched
       }
       return false; //Indicates that book already exists, no fetch needed.
    }
}
