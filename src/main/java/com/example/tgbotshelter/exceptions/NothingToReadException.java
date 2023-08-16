package com.example.tgbotshelter.exceptions;

public class NothingToReadException extends RuntimeException {
    public NothingToReadException() {
        super("Такого файла нет!");
    }
}