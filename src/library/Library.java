/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

//import java.util.Arrays;
import myClasses.App;
//import myClasses.GuiApp;

/**
 *
 * @author Angelina
 */
public class Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.print(args[0]);
//        System.out.print(" ");
//        System.out.print(args[1]);
//
//        System.out.println(Arrays.toString(args));

        App app; //link null
        app = new App();
        app.run();
        //app.toString(); //новый метод перекрыл тот, что был в объекте
        //app.super.toString(); //тот, который в объекте !!! пока не работало?
    }

}
