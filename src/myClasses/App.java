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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Angelina
 */
public class App {
    
    private Scanner scanner = new Scanner(System.in);
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    
    public void run(){
        String repeat = "r";
        do{
            System.out.println("Выберите номер задачи:");
            System.out.println("0: Закончить программу");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Показать список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Показать список читателей");
            System.out.println("5: Выдать книгу читателю");
            System.out.println("6: Вернуть книгу");
            
            int task = scanner.nextInt(); scanner.nextLine();
            
            switch (task) {
                case 0:
                    repeat = "q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    System.out.println("--- Добавление книги ---");
                    books.add(addBook());
                    break;
                case 2:
                    System.out.println("--- Список книг ---");
                    for (int i=0; i < books.size(); i++){
                        if(books.get(i) != null) {
                            System.out.println(books.get(i).toString());
                        }
                    }      
                    System.out.println("------------------");
                    break;
                case 3:
                    System.out.println("--- Добавление читателя ---");
                    readers.add(addReader());
                    break;
                case 4:
                    System.out.println("--- Список читателей ---");
                    for (int i=0; i < readers.size(); i++){
                        if(readers.get(i) != null) {
                            System.out.println(readers.get(i).toString());
                        }
                    }      
                    System.out.println("------------------");
                    break;
                case 5:
                    System.out.println("--- Выдача книги ---");
                    History history = addHistory();
                    histories.add(history);
                    System.out.println("Книга "+history.getBook().getBookName()
                                        +" выдана читателю "+history.getReader().getFirstName()
                                        +" "+history.getReader().getLastName()
                                        +" "+history.getGivenDate()
                    );    
                    System.out.println("------------------");
                    break;
                case 6:
                    System.out.println("--- Возврат книги ---");
                    System.out.println("Список читаемых книг:");
                    int n=0;
                    for (int i=0; i < histories.size(); i++){
                        if(histories.get(i).getReturnedDate() == null) { 
                            //&& histories.get(i) != null
                            System.out.println(i+1+". Книга "
                                    +histories.get(i).getBook().getBookName()
                                    +" выдана читателю "+histories.get(i).getReader().getFirstName()
                                    +" "+histories.get(i).getReader().getLastName()   
                            );
                            n++;
                        }
                    }
                    if(n<1){
                        System.out.println("Нет читаемых книг!");
                        System.out.println("------------------");
                        break;
                    }
                    System.out.print("Выберите номер возвращаемой книги: ");
                    int numberHistory = scanner.nextInt(); scanner.nextLine();
                    Calendar cal = new GregorianCalendar();
                    histories.get(numberHistory-1).setReturnedDate(cal.getTime());
                    System.out.println("Книга "+histories.get(numberHistory-1).getBook().getBookName()
                                                +" возвращена"
                                                +" "+histories.get(numberHistory-1).getReturnedDate()
                    );
                    System.out.println("------------------");
                    break;
                default:
                    System.out.println("Выберите цифру из списка!");
            }
        }while("r".equals(repeat));
        
        
    }
    
    private Book addBook(){
        Book book = new Book();
        
        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        
        System.out.print("Введите год издания книги: ");
        book.setPublishedYear(scanner.nextInt());scanner.nextLine();
        
        System.out.println("Введите автора книги");
        
        System.out.print("Сколько авторов у книги: ");
        int countAuthors = scanner.nextInt();scanner.nextLine();
        Author[] authors = new Author[countAuthors];
        for (int i=0; i < authors.length; i++) {
            Author author = new Author();
            System.out.println("Имя автора "+(i+1)+": ");
            author.setFirstName(scanner.nextLine());
            System.out.println("Фамилия автора: ");
            author.setLastName(scanner.nextLine());
            authors[i] = author;
        }
        book.setAuthors(authors);
        return book;
    }
    
    private Reader addReader(){
        Reader reader = new Reader();
        
        System.out.print("Введите имя читателя: ");
        reader.setFirstName(scanner.nextLine());
        
        System.out.print("Введите фамилию читателя: ");
        reader.setLastName(scanner.nextLine());
        
        System.out.print("Введите телефон читателя: ");
        reader.setTelephone(scanner.nextLine());
        
        return reader;
    }

    private History addHistory() {
        //throw new UnsupportedOperationException("Not supported yet."); //Заглушка To change body of generated methods, choose Tools | Templates.
        History history = new History();
        
        System.out.println("Список книг");
        for (int i=0; i < books.size(); i++){
            if(books.get(i) != null) {
                StringBuilder sbAuthorNames = new StringBuilder();
                for (int j = 0; j < books.get(i).getAuthors().length; j++) {
                    sbAuthorNames.append(books.get(i).getAuthors()[j].getFirstName())
                            .append(" ")
                            .append(books.get(i).getAuthors()[j].getLastName())
                            .append(". ");
                }
                System.out.println(i+1
                        +". "+books.get(i).getBookName()
                        +". "+books.get(i).getPublishedYear()
                        +". "+sbAuthorNames.toString());
//                        +". "+books[i].getAuthors()[0].getFirstName()
//                        +". "+books[i].getAuthors()[0].getLastName()
                        }
                        }
        
        System.out.println("Выберите номер книги: ");
        int numberBook = scanner.nextInt(); scanner.nextLine();
        
        System.out.println("Список читателей:");
        for (int i=0; i < readers.size(); i++){
            if(readers.get(i) != null) {
                System.out.println(i+1+". "+readers.get(i).toString());
            }
        }
        System.out.println("Выберите номер читателя: ");
        int numberReader = scanner.nextInt(); scanner.nextLine();
        
        history.setBook(books.get(numberBook-1));
        history.setReader(readers.get(numberReader-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        
        
        return history;
    }

}
