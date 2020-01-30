package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowOrder implements IAction {
    private final String parameter;
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    public ShowOrder(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        Hotel.getInstance().sortOrder(parameter).forEach(System.out::println);
        LOGGER.log(Level.INFO, "Show order list sorted by: " + parameter);
    }
}
