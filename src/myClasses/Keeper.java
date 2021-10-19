/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

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
        FileOutputStream fos = null; //идентификаторы только, объектов нет
        ObjectOutputStream oos = null;
        
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
        FileInputStream fis = null;
        ObjectInputStream ois = null;
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
    public void saveReaders(List<Reader> readers) {
        
    }
    
    @Override
    public List<Reader> loadReaders() {
        List<Reader> readers = new ArrayList<>();
        
        return readers;
    }
    
    @Override
    public void saveHistories(List<History> histories){
        
    }
    
    @Override
    public List<History> loadHistories() {
        List<History> histories = new ArrayList<>();
        
        return histories;
    }
    
}
