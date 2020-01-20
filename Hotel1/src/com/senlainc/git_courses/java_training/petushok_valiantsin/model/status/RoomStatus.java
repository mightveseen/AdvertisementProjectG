package com.senlainc.git_courses.java_training.petushok_valiantsin.model.status;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.ConsoleColor;

public enum RoomStatus {
    FREE("Free"),
    RENTED("Rented"),
    SERVED("Served");

    private final String name;

    RoomStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        switch (RoomStatus.this) {
            default:
                return ConsoleColor.GREEN.getCode() + this.name + ConsoleColor.RESET.getCode();
            case RENTED:
                return ConsoleColor.RED.getCode() + this.name + ConsoleColor.RESET.getCode();
            case SERVED:
                return ConsoleColor.YELLOW.getCode() + this.name + ConsoleColor.RESET.getCode();
        }
    }
}
