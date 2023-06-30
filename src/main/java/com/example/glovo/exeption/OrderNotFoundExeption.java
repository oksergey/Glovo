package com.example.glovo.exeption;

public class OrderNotFoundExeption extends RuntimeException {
    public OrderNotFoundExeption(String message) {
        super(message);
    }
}
