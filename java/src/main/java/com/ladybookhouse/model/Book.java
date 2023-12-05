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
    private long isbn;

    //notes about the condition. Some item includes "" only without note.
    private String notes;

    @JsonProperty("qty")
    private Integer quantity;

    @JsonProperty("sku")
    private String inventoryCode;

    //numeric value for the condition
    private Integer condition;

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


    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getNotes() {
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
