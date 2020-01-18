package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

public class ShowRoom implements IAction {
    private String parameter;

    public ShowRoom(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        if (parameter.equals("default")) {
            Hotel.getInstance().showRoom("all");
            return;
        }
        Hotel.getInstance().sortRoom(parameter);
    }
}
