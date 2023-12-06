package com.ladybookhouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    private String title;

    private String image;

    private Double price;

    //paperback or hardcover
    private String media;

    private String publisher;

    private String author;
    private String isbn;

    //notes about the condition. Some item includes "" only without note.
    private String notes;

    @JsonProperty("qty")
    private Integer quantity;

    @JsonProperty("sku")
    private String inventoryCode;

    //numeric value for the condition
    private Integer condition;

    private String bookCategory;

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public String getMedia() {
        return media;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNotes(){
        notes = "";
        if(getCondition() == 1){
            notes+= "This book is in near-perfect condition, showing minimal signs " +
                    "of use. It has clean, crisp pages with no markings or highlighting, " +
                    "and the spine and cover are intact without any creases or wear. This " +
                    "book appears as if it has been barely touched and is virtually " +
                    "indistinguishable from a brand new book.";
        }else if(getCondition() == 2){
            notes+= "This book is in very good condition, with only minor signs " +
                    "of wear and use. The pages may contain limited notes or " +
                    "highlighting, but overall, the text remains clean and legible. " +
                    "The cover and spine are also in great shape, with only minimal " +
                    "shelf wear or creasing. This book has been well-maintained and " +
                    "still presents a clean and attractive appearance.";
        }else if(getCondition() == 3){
            notes+= "This book is in good condition, showing moderate signs of wear " +
                    "and use. The pages may have noticeable notes, highlighting, or " +
                    "underlining, but the text remains clearly readable. The cover and " +
                    "spine may show signs of shelf wear, such as creases, scuff marks, " +
                    "or small tears. Despite these cosmetic flaws, the book remains " +
                    "structurally sound and fully functional.";
        }
        return notes;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", media='" + media + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", notes='" + notes + '\'' +
                ", quantity=" + quantity +
                ", inventoryCode='" + inventoryCode + '\'' +
                ", condition=" + condition +
                '}';
    }
}
