/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author William Sanchez
 */
public class Book implements Serializable {

    private String title;
    private String author;
    private String category;
    private String publisher;
    private String description;

    public Book() {
        this.title = "";
        this.author = "";
        this.category = "";
        this.publisher = "";
        this.description = "";
    }

    public Book(String title, String author, String category, String publisher, String description) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}