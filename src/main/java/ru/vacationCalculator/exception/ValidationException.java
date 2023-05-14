package ru.vacationCalculator.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

}