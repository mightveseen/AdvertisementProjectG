package com.senlainc.git_courses.java_training.petushok_valiantsin.exception;

public class FileNotExistException extends RuntimeException {
    public FileNotExistException(String reason, Throwable stackTrace) {
        super(reason, stackTrace);
    }
}
