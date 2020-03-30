package org.basecampcodingacademy.reflections.exception;

import java.time.LocalDate;

public class DateErrorMessageReflection extends Exception {
    public LocalDate date;
    public DateErrorMessageReflection(LocalDate date) {
        this.date = date;
    }
};