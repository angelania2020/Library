/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Arrays;

/**
 *
 * @author Angelina
 */
public class Book {
    private String bookName;
    private Author[] authors;
    private int publishedYear;

    public Book() { //это конструктор, без void, создаем его, чтобы не потерять
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" + "bookName=" + bookName + ", authors=" + Arrays.toString(authors) + ", publishedYear=" + publishedYear + '}';
    }
    
    
    
    
    
    
}
