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

    private String conditionAsText;

    private String conditionBook;

    public String getConditionBook() {
        return conditionBook;
    }

    public void setConditionBook(String conditionBook) {
        this.conditionBook = conditionBook;
    }

    public String getConditionAsText() {
        conditionAsText = "";
        if(getCondition() == 1){
            conditionAsText+= "Like New";
        }else if(getCondition() == 2){
            conditionAsText += "Very Good";
        }else if(getCondition() == 3){
            conditionAsText += "Good";
        }else if(getCondition() == 4){
            conditionAsText += "Acceptable";
        }else if(getCondition() == 11){
            conditionAsText += "New";
        }
        return conditionAsText;
    }

    public void setConditionAsText(String conditionAsText) {
        this.conditionAsText = conditionAsText;
    }

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
        }else if(getCondition() == 4){
            notes+= "This book is in acceptable condition, showing significant signs of " +
                    "wear and use. The pages may contain extensive notes, highlighting, or " +
                    "underlining, and the text may be difficult to read in some places. The " +
                    "cover and spine may have considerable wear, including creases, scuff marks, " +
                    "or larger tears. This book may also have a former owner's name or other " +
                    "markings on the inside covers or endpapers. Although the book shows its age " +
                    "and heavy use, it is still intact and suitable for reading or reference purposes.";
        }else if(getCondition() == 11){
            notes += "Brand New";
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
