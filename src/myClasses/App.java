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
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Angelina
 */
public class App {

    private final Scanner scanner = new Scanner(System.in);
    //---------------Данные библиотеки-------------
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    //---------------Сохранение--------------------
//    private Keeping keeping = new Keeper();
    private final Keeping keeping = new KeeperToBase();

    // поля, конструкторы, методы - такой порядок
    public App() {
        books = keeping.loadBooks();
        authors = keeping.loadAuthors();
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
            System.out.println("8: Добавить автора");
            System.out.println("9: Список авторов");
            System.out.println("10: Изменить данные книги");

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
                case 8:
                    addAuthor();
                    break;
                case 9:
                    printListAuthors();
                    break;
                case 10:
                    changeBook();
                    break;
                default:
                    System.out.println("Выберите цифру из списка!");
            }
        } while ("r".equals(repeat));
    }

    private void addBook() {
        System.out.println("--- Добавление книги ---");

        if (isQuit()) {
            return;
        }

        Book book = new Book();

        Set<Integer> setNumbersAuthors = printListAuthors();
        if (setNumbersAuthors.isEmpty()) {
            return;
        }
        System.out.println("Если есть авторы книги в списке, выберите 1, если нет, выберите 2:");
        if (getNumber() != 1) {
            System.out.println("Добавьте автора!");
            return;
        }
        System.out.print("Сколько авторов у книги: ");
        int countAuthors = getNumber();
        List<Author> authorsBook = new ArrayList<>();
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("Введите номер автора " + (i + 1));
            int numberAuthor = insertNumber(setNumbersAuthors);
            authorsBook.add(authors.get(numberAuthor - 1));
        }
        book.setAuthors(authorsBook);

        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());

        System.out.print("Введите год издания книги: ");
        book.setPublishedYear(getNumber());

        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());

        System.out.println("-----------------");
        books.add(book);
        keeping.saveBooks(books);
    }

    private void addReader() {
        if (isQuit()) {
            return;
        }

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
        if (isQuit()) {
            return;
        }
        History history = new History();
        System.out.println("Выберите номер книги: ");
        Set<Integer> setNumbersBooks = printListBooks();
        if (setNumbersBooks.isEmpty()) {
            return;
        }
        int numberBook = insertNumber(setNumbersBooks);

        Set<Integer> setNumbersReaders = printListReaders();
        if (setNumbersReaders.isEmpty()) {
            return;
        }
        System.out.println("Выберите номер читателя: ");
        int numberReader = insertNumber(setNumbersReaders);

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
        System.out.println("-----------------------");
    }

    private Set<Integer> printListBooks() {
        System.out.println("--- Список книг ---");
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null && books.get(i).getCount() > 0) {
                System.out.printf("%d. %s. %s. %d. В наличии экземпляров: %d%n",
                        i + 1,
                        books.get(i).getBookName(),
                        Arrays.toString(books.get(i).getAuthors().toArray()),
                        books.get(i).getPublishedYear(),
                        books.get(i).getCount()
                );
                setNumbersBooks.add(i + 1);
            } else if (books.get(i) != null && books.get(i).getQuantity() > 0) {
                System.out.printf("%1$d. (Книга читается до: %5$s) %2$s. %4$d. %3$s%n",
                        i + 1,
                        books.get(i).getBookName(),
                        Arrays.toString(books.get(i).getAuthors().toArray()),
                        books.get(i).getPublishedYear(),
                        getReturnDate(books.get(i))
                );
            }
        }
        if (setNumbersBooks.isEmpty()) {
            System.out.println("Нет (свободных для чтения) книг в библиотеке!");
            System.out.println("------------------------");
        }
        return setNumbersBooks;
    }

    private Set<Integer> printListAllBooks() {
        System.out.println("--- Список книг ---");
        Set<Integer> setNumbersBooks = new HashSet<>();
        for (int i = 0; i < books.size(); i++) {
            //if (books.get(i) != null && books.get(i).getCount() >= 0) {
            System.out.printf("%d. %s. %s. %d. Всего книг: %d. В наличии экземпляров: %d.%n",
                    i + 1,
                    books.get(i).getBookName(),
                    Arrays.toString(books.get(i).getAuthors().toArray()),
                    books.get(i).getPublishedYear(),
                    books.get(i).getQuantity(),
                    books.get(i).getCount()
            );
            setNumbersBooks.add(i + 1);
        } //else if (books.get(i) != null) {
        //System.out.printf("%1$d. (Книга читается до: %5$s) %2$s. %4$d. %3$s%n",
        //i + 1,
        //books.get(i).getBookName(),
        //Arrays.toString(books.get(i).getAuthors().toArray()),
        //books.get(i).getPublishedYear(),
        //getReturnDate(books.get(i))
        //);
        //}
        //}
        if (setNumbersBooks.isEmpty()) {
            System.out.println("Нет книг в библиотеке!");
            System.out.println("------------------------");
        }
        return setNumbersBooks;
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

    private Set<Integer> printListReaders() {
        System.out.println("--- Список читателей ---");
        Set<Integer> setNumbersReaders = new HashSet<>();
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i) != null) {
                System.out.printf("%d. %s %s. Телефон: %s%n",
                        i + 1,
                        readers.get(i).getFirstName(),
                        readers.get(i).getLastName(),
                        readers.get(i).getTelephone()
                );
                setNumbersReaders.add(i + 1);
            }
        }
        if (setNumbersReaders.isEmpty()) {
            System.out.println("Нет читателей в библиотеке!");
            System.out.println("------------------------");
        }
        return setNumbersReaders;
    }

    private void returnBook() {
        System.out.println("--- Возврат книги ---");
        if (isQuit()) {
            return;
        }
        Set<Integer> setNumbersGivenBooks = printListGivenBooks();
        if (setNumbersGivenBooks.isEmpty()) {
            return;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int numberHistory = insertNumber(setNumbersGivenBooks);
        Calendar cal = new GregorianCalendar();
        histories.get(numberHistory - 1).setReturnedDate(cal.getTime());
//         Здесь объясняется что значит передача по ссылке в Java
//         https://coderoad.ru/40480/%D0%AD%D1%82%D0%BE-Java-pass-by-reference-%D0%B8%D0%BB%D0%B8-pass-by-value
//         Постарайтесь понять, почему код ниже не изменит count в book, которая находится в books.
//         histories.get(numberHistory - 1).getBook().setCount(
//                histories.get(numberHistory - 1).getBook().getCount()+1
//         );
//        А этот код будет делать что надо.
        for (int i = 0; i < books.size(); i++) {
            if (histories.get(numberHistory - 1).getBook().getBookName().equals(books.get(i).getBookName())) {
                books.get(i).setCount(books.get(i).getCount() + 1);
                break;
            }
        }
        keeping.saveBooks(books);
        keeping.saveHistories(histories);
        System.out.println("Книга " + histories.get(numberHistory - 1).getBook().getBookName()
                + " возвращена"
                + " " + histories.get(numberHistory - 1).getReturnedDate()
        );
        System.out.println("------------------");
    }

    private Set<Integer> printListGivenBooks() {
        System.out.println("Список читаемых книг:");
        Set<Integer> setNumbersBook = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null && histories.get(i).getReturnedDate() == null) {
                System.out.println(i + 1 + ". Книгу "
                        + histories.get(i).getBook().getBookName()
                        + " читает " + histories.get(i).getReader().getFirstName()
                        + " " + histories.get(i).getReader().getLastName()
                );
                setNumbersBook.add(i + 1);
            }
        }
        if (setNumbersBook.isEmpty()) {
            System.out.println("Нет читаемых книг!");
            System.out.println("---------------------");
        }
        return setNumbersBook;
    }

    private int getNumber() {
        int number;
        do {
            String strNumber = scanner.nextLine();
            try {
                number = Integer.parseInt(strNumber);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Попробуй еще раз: ");
            }
        } while (true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        int number;
        do {
            number = getNumber();
            if (setNumbers.contains(number)) {
                return number;
            }
            System.out.println("Попробуй еще: ");
        } while (true);
    }

    private Set<Integer> printListAuthors() {
        Set<Integer> numbersAuthors = new HashSet<>();
        if (authors.isEmpty()) {
            System.out.println("Список авторов пуст, добавьте авторов книги.");
            return new HashSet<>();
        } else {
            System.out.println("Список авторов:");
        }
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s %s%n",
                    i + 1,
                    authors.get(i).getFirstName(),
                    authors.get(i).getLastName()
            );
            numbersAuthors.add(i + 1);
        }
        return numbersAuthors;
    }

    private void addAuthor() {
        System.out.println("---- Добавить автора -----");
        if (isQuit()) {
            return;
        }
        Author author = new Author();
        System.out.println("Имя автора: ");
        author.setFirstName(scanner.nextLine());
        System.out.println("Фамилия автора: ");
        author.setLastName(scanner.nextLine());
        authors.add(author);
        keeping.saveAuthors(authors);
    }

    private void changeBook() {
        System.out.println("----- Изменение данных книги ------");
        if (isQuit()) {
            return;
        }
        /**
         * 1. Выводим список книг 2. Выбрать номер книги 3. Показать содержимое
         * поля книги 4. Спросить у пользователя, нужно ли менять поле. 5.
         * Ввести новое значение поля. ----- Повторить 3-5 для других полей
         * книги. Сохранить список существующих книг
         */
        Set<Integer> changeNumbers = new HashSet<>();
        changeNumbers.add(1);
        changeNumbers.add(2);

        Set<Integer> setNumbersBooks = printListAllBooks();
        if (setNumbersBooks.isEmpty()) {
            return;
        }

        System.out.println("Введите номер книги: ");
        int numberBook = insertNumber(setNumbersBooks);
        System.out.println("Название книги: " + books.get(numberBook - 1).getBookName());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        int change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новое название книги: ");
            books.get(numberBook - 1).setBookName(scanner.nextLine());
        }
        System.out.println("Год издания книги: " + books.get(numberBook - 1).getPublishedYear());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новый год издания книги: ");
            books.get(numberBook - 1).setPublishedYear(getNumber());
        }
        System.out.println("Количество экземпляров книги: " + books.get(numberBook - 1).getQuantity());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            System.out.println("Введите новое количество экземпляров книги: ");
            int oldQuantity = books.get(numberBook - 1).getQuantity();
            int oldCount = books.get(numberBook - 1).getCount();
            int newQuantity;
            do {
                newQuantity = getNumber();
                if (newQuantity >= oldQuantity - oldCount) {
                    break;
                }
                System.out.println("Книг должно быть больше. Введите новое количество экземпляров книги: ");
            } while (true);
            int newCount = oldCount + (newQuantity - oldQuantity);

            books.get(numberBook - 1).setQuantity(newQuantity);
            books.get(numberBook - 1).setCount(newCount);
        }
        System.out.println("Автор(ы) книги: ");
        for (int i = 0; i < books.get(numberBook - 1).getAuthors().size(); i++) {
            System.out.printf("%d. %s %s%n",
                    i + 1,
                    books.get(numberBook - 1).getAuthors().get(i).getFirstName(),
                    books.get(numberBook - 1).getAuthors().get(i).getLastName()
            );
        }
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if (1 == change) {
            books.get(numberBook - 1).getAuthors().clear();
             
            System.out.println("Введите новое количество авторов: ");
            int countAuthors = getNumber();
                    
            Set<Integer> setNumbersAuthors = printListAuthors();
            if (setNumbersAuthors.isEmpty()) {
                return;
            }else if (countAuthors > setNumbersAuthors.size()) {
                System.out.println("Количестно новых авторов не должно превышать количество авторов в списке. Добавьте авторов в базу.");
                System.out.println("-----------------------");
                return;
            } 
            
            for (int i = 0; i < countAuthors; i++) {
                System.out.println("Введите номер автора " + (i + 1) + " из списка: ");
                int numberAuthor = insertNumber(setNumbersAuthors);
                books.get(numberBook - 1).getAuthors().add(authors.get(numberAuthor - 1));
            }
        }
        keeping.saveBooks(books);
    }

    private boolean isQuit() {
        System.out.println("Чтобы закончить задачу нажми \"q\"");
        String q = scanner.nextLine();
        return ("q".equals(q));
    }
}
