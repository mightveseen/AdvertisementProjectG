package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger.CustomLogger;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {
    private final MenuNavigator menuNavigator;
    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getSimpleName());

    public MenuController() {
        final MenuBuilder menuBuilder = new MenuBuilder();
        final Menu menu = menuBuilder.getRootMenu();
        this.menuNavigator = new MenuNavigator(menu);
    }

    public void showMenu() throws InterruptedException {
        try {
            Thread.sleep(10);
            menuNavigator.printMenu();
            System.out.print("Choose operation: ");
            Integer index = Integer.parseInt(new Scanner(System.in).next());
            menuNavigator.navigate(index);
        } catch (NullPointerException | NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                showMenu();
            }
        } catch (InterruptedException ignored) {

        }
    }
}
