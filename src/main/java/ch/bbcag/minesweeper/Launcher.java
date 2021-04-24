package ch.bbcag.minesweeper;

import javafx.application.Application;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {

        Thread.currentThread().setDefaultUncaughtExceptionHandler((t, e) -> JOptionPane.showMessageDialog(null, "Something went wrong: " + e.getMessage()));

        Application.launch(App.class, args);
    }
}
