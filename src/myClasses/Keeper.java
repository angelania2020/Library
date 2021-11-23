/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelina
 */
public class Keeper implements Keeping{
    
    @Override
    public void saveBooks(List<Book> books) {
        FileOutputStream fos; //идентификаторы только, объектов нет
        ObjectOutputStream oos;
        
        try {
            fos = new FileOutputStream("books");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }
    
    @Override
    public List<Book> loadBooks() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Book> books = new ArrayList<>();
        try {
            fis = new FileInputStream("books");
            ois = new ObjectInputStream(fis);
            books = (List<Book>) ois.readObject(); //явное приведение типа
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл books еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения books", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return books;
    }
    
    
    @Override
    public void saveAuthors(List<Author> authors) {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("authors");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(authors);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }
    @Override
    public List<Author> loadAuthors() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Author> authors = new ArrayList<>();
        try {
            fis = new FileInputStream("authors");
            ois = new ObjectInputStream(fis);
            authors = (List<Author>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл authors еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения authors", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return authors;
    }
    
    @Override
    public void saveReaders(List<Reader> readers) {
        FileOutputStream fos; //идентификаторы только, объектов нет
        ObjectOutputStream oos;
        
        try {
            fos = new FileOutputStream("readers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(readers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }
    
    @Override
    public List<Reader> loadReaders() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Reader> readers = new ArrayList<>();
        try {
            fis = new FileInputStream("readers");
            ois = new ObjectInputStream(fis);
            readers = (List<Reader>) ois.readObject(); //явное приведение типа
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл readers еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения readers", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return readers;
    }
    
    @Override
    public void saveHistories(List<History> histories){
        FileOutputStream fos; //идентификаторы только, объектов нет
        ObjectOutputStream oos;
        
        try {
            fos = new FileOutputStream("histories");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }
    
    @Override
    public List<History> loadHistories() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<History> histories = new ArrayList<>();
        try {
            fis = new FileInputStream("histories");
            ois = new ObjectInputStream(fis);
            histories = (List<History>) ois.readObject(); //явное приведение типа
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Файл histories еще не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Ошибка чтения histories", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Keeper.class.getName()).log(Level.SEVERE, "Нет класса", ex);
        }
        return histories;
    }
    
}
