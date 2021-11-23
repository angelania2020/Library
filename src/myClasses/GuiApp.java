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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Angelina
 */
public class GuiApp {

    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private final Keeping keeping = new Keeper();

    public GuiApp() {
        books = keeping.loadBooks();
        readers = keeping.loadReaders();
        histories = keeping.loadHistories();
    }

    public void run() {
        JFrame windows = new JFrame();

    }

}
