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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Angelina
 */
public class App {
    
    //private String name; // это имя останется, переменная класса
    
    public void run(){
        //System.out.println("Это метод run() класса App");
        //Scanner scanner = new Scanner(System.in);
        //String name = "Angelina"; //локальная переменная
        //this.name = scanner.nextLine(); //поле класса
        //System.out.println(this.toString());
        //System.out.println(name);
        
        Book book1 = new Book();
        book1.setBookName("Voina i mir");
        
        Author author1 = new Author();
        author1.setFirstName("Lev");
        author1.setLastName("Tolstoy");
        
        Author[] authors = new Author[1];
        authors[0]=author1;
        
        book1.setAuthors(authors);
        book1.setPublishedYear(2021);
        
        Reader reader1 = new Reader();
        reader1.setFirstName("Angelina");
        reader1.setLastName("Prokofjeva");
        reader1.setTelephone("55661148");
        
        History history1 = new History();
        history1.setBook(book1);
        history1.setReader(reader1);
        Calendar c = new GregorianCalendar();
        history1.setGivenDate(c.getTime());

        
        System.out.println(history1.toString());
        
        history1.setReturnedDate(c.getTime());
        
        System.out.println(history1.toString());

        
        //clean build
        
        
        
        
    }
    
    //@Override //перекрыл доступ к родительскому toString
    //public String toString() {
        //return "App { name = "+this.name + " }";
    //}
}
