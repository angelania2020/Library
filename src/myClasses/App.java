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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Angelina
 */
public class App {

    private Scanner scanner = new Scanner(System.in);
    //---------------Данные библиотеки-------------
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    //---------------Сохранение--------------------
    private Keeping keeping = new Keeper();

    // поля, конструкторы, методы - такой порядок
    public App() {
        books = keeping.loadBooks();
        readers = keeping.loadReaders();
        histories = keeping.loadHistories();
    }

    public void run() {
        String repeat = "r";
        do {
            System.out.println("Выберите номер задачи:");
            System.out.println("0: Закончить программу");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Показать список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Показать список читателей");
            System.out.println("5: Выдать книгу читателю");
            System.out.println("6: Вернуть книгу");
            System.out.println("7: Список выданных книг");

            int task = scanner.nextInt();
            scanner.nextLine();

            switch (task) {
                case 0:
                    repeat = "q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    addBook();
                    break;
                case 2:
                    printListBooks();
                    break;
                case 3:
                    addReader();
                    break;
                case 4:
                    printListReaders();
                    break;
                case 5:
                    addHistory();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    printListGivenBooks();
                    break;
                default:
                    System.out.println("Выберите цифру из списка!");
            }
        } while ("r".equals(repeat));
    }

    private void addBook() {
        System.out.println("--- Добавление книги ---");

        Book book = new Book();

        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());

        System.out.print("Введите год издания книги: ");
        book.setPublishedYear(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(scanner.nextInt());
        scanner.nextLine();
        book.setCount(book.getQuantity());

        System.out.println("Введите автора книги");

        System.out.print("Сколько авторов у книги: ");
        int countAuthors = scanner.nextInt();
        scanner.nextLine();
        Author[] authors = new Author[countAuthors];
        for (int i = 0; i < authors.length; i++) {
            Author author = new Author();
            System.out.println("Имя автора " + (i + 1) + ": ");
            author.setFirstName(scanner.nextLine());
            System.out.println("Фамилия автора: ");
            author.setLastName(scanner.nextLine());
            authors[i] = author;
        }
        book.setAuthors(authors);
        System.out.println("-----------------");
        books.add(book);
        keeping.saveBooks(books);
    }

    private void addReader() {
        System.out.println("--- Добавление читателя ---");
        Reader reader = new Reader();

        System.out.print("Введите имя читателя: ");
        reader.setFirstName(scanner.nextLine());

        System.out.print("Введите фамилию читателя: ");
        reader.setLastName(scanner.nextLine());

        System.out.print("Введите телефон читателя: ");
        reader.setTelephone(scanner.nextLine());

        System.out.println("-------------------");
        readers.add(reader);
        keeping.saveReaders(readers);
    }

    private void addHistory() {
        //throw new UnsupportedOperationException("Not supported yet."); //Заглушка To change body of generated methods, choose Tools | Templates.
        System.out.println("--- Выдача книги ---");
        System.out.println("Список книг");
        History history = new History();
        int n = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null && books.get(i).getCount() > 0) {
                StringBuilder sbAuthorNames = new StringBuilder();
                for (int j = 0; j < books.get(i).getAuthors().length; j++) {
                    sbAuthorNames.append(books.get(i).getAuthors()[j].getFirstName())
                            .append(" ")
                            .append(books.get(i).getAuthors()[j].getLastName())
                            .append(". ");
                }
                System.out.println(i + 1
                        + ". " + books.get(i).getBookName()
                        + ". " + books.get(i).getPublishedYear()
                        + ". " + sbAuthorNames.toString()
                        // +". "+books[i].getAuthors()[0].getFirstName()
                        // +". "+books[i].getAuthors()[0].getLastName()
                        + "В наличии: " + books.get(i).getCount()
                );
                n++;
            }
        }
        if (n < 1) {
            System.out.println("Нет книг для чтения.");
            return; // вместо history вернёт null
        }

        System.out.println("Выберите номер книги: ");
        int numberBook = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Список читателей:");
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i) != null) {
                System.out.println(i + 1 + ". " + readers.get(i).toString());
            }
        }
        System.out.println("Выберите номер читателя: ");
        int numberReader = scanner.nextInt();
        scanner.nextLine();

        history.setBook(books.get(numberBook - 1));
        history.setReader(readers.get(numberReader - 1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());

        history.getBook().setCount(history.getBook().getCount() - 1);
        keeping.saveBooks(books);
        histories.add(history);
        keeping.saveHistories(histories);
        System.out.println("Книга " + history.getBook().getBookName()
                + " выдана читателю " + history.getReader().getFirstName()
                + " " + history.getReader().getLastName()
                + " " + history.getGivenDate()
        );
        System.out.println("------------------");
    }

    private void printListBooks() {
        System.out.println("--- Список книг ---");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null && books.get(i).getCount() > 0) {
                System.out.printf("%d. %s. %s. %d. В наличии экземпляров: %d%n",
                        i + 1,
                        books.get(i).getBookName(),
                        Arrays.toString(books.get(i).getAuthors()),
                        books.get(i).getPublishedYear(),
                        books.get(i).getCount()
                );
            } else if (books.get(i) != null) {
                System.out.printf("%d. %s. %s. %d. Книга читается до: %s%n",
                        i + 1,
                        books.get(i).getBookName(),
                        Arrays.toString(books.get(i).getAuthors()),
                        books.get(i).getPublishedYear(),
                        getReturnDate(books.get(i))
                );
            }
        }
        System.out.println("------------------");
    }

    private String getReturnDate(Book book) {
        for (int i = 0; i < histories.size(); i++) {
            if (book.getBookName().equals(histories.get(i).getBook().getBookName())
                    && histories.get(i).getReturnedDate() == null) {
                Date givenDate = histories.get(i).getGivenDate();
                LocalDate localGivenDate = givenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        return "";
    }

    private void printListReaders() {
        System.out.println("--- Список читателей ---");
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i) != null) {
                System.out.println(readers.get(i).toString());
            }
        }
        System.out.println("------------------");
    }

    private void returnBook() {
        System.out.println("--- Возврат книги ---");
        System.out.println("Список читаемых книг:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null
                    && histories.get(i).getReturnedDate() == null
                    && histories.get(i).getBook().getCount()
                    < histories.get(i).getBook().getQuantity()) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n",
                        i + 1,
                        histories.get(i).getBook().getBookName(),
                        histories.get(i).getReader().getFirstName(),
                        histories.get(i).getReader().getLastName()
                );
                n++;
            }
        }
        if (n < 1) {
            System.out.println("Нет читаемых книг!");
            System.out.println("------------------");
            return;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int numberHistory = scanner.nextInt();
        scanner.nextLine();
        Calendar cal = new GregorianCalendar();
        histories.get(numberHistory - 1).setReturnedDate(cal.getTime());
        histories.get(numberHistory - 1).getBook().setCount(
                histories.get(numberHistory - 1).getBook().getCount() + 1
        );
        keeping.saveBooks(books);
        keeping.saveHistories(histories);
        System.out.println("Книга " + histories.get(numberHistory - 1).getBook().getBookName()
                + " возвращена"
                + " " + histories.get(numberHistory - 1).getReturnedDate()
        );
        System.out.println("------------------");
    }

    private void printListGivenBooks() {
        System.out.println("Список читаемых книг:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null && histories.get(i).getReturnedDate() == null) {
                System.out.println(i + 1 + ". Книгу "
                        + histories.get(i).getBook().getBookName()
                        + " читает " + histories.get(i).getReader().getFirstName()
                        + " " + histories.get(i).getReader().getLastName()
                );
                n++;
            }
        }
        if (n < 1) {
            System.out.println("Нет читаемых книг!");
            System.out.println("-------------------");
        }
    }

}
