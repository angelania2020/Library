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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Angelina
 */
public class KeeperToBase implements Keeping {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();

    @Override
    public void saveBooks(List<Book> books) {
        tx.begin();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == null) {
                em.persist(books.get(i));
            }
        }
        tx.commit();

    }

    @Override
    public void saveAuthors(List<Author> authors) {
        tx.begin();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == null) {
                em.persist(authors.get(i));
            }
        }
        tx.commit();

    }

    @Override
    public List<Book> loadBooks() {
        if (em.createQuery("SELECT b FROM Book b").getResultList().isEmpty()) {
            System.out.println("Таблица book пуста.");
        }
        try {
            return (List<Book>) em.createQuery("SELECT b FROM Book b").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица book пуста.");
        }
        return new ArrayList<>();  // чтобы не было NullPointerException
    }

    @Override
    public List<Author> loadAuthors() {
        if (em.createQuery("SELECT a FROM Author a").getResultList().isEmpty()) {
            System.out.println("Таблица author пуста.");
        }
        try {
            return (List<Author>) em.createQuery("SELECT a FROM Author a").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица author пуста.");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        tx.begin();
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getId() == null) {
                em.persist(readers.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<Reader> loadReaders() {
        if (em.createQuery("SELECT r FROM Reader r").getResultList().isEmpty()) {
            System.out.println("Таблица reader пуста.");
        }
        try {
            return (List<Reader>) em.createQuery("SELECT r FROM Reader r").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица reader пуста.");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getId() == null) {
                em.persist(histories.get(i));
            }
        }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        if (em.createQuery("SELECT h FROM History h").getResultList().isEmpty()) {
            System.out.println("Таблица history пуста.");
        }
        try {
            return (List<History>) em.createQuery("SELECT h FROM History h").getResultList();
        } catch (Exception e) {
            System.out.println("Таблица history пуста.");
        }
        return new ArrayList<>();
    }

}
