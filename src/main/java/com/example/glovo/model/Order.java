package com.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * 1. Реалізувати клас Order. Цей клас зберігатиме значення: id, date, cost, products
 * <p>
 * 2. Реалізувати клас Product. Цей клас зберігатиме значення: id, name, cost
 * 2.1 Реалізувати метод отримання замовлення за ID
 * <p>
 * 3.2 Реалізувати метод отримання всіх замовлень
 * 3.3 Реалізувати метод додавання замовлення
 * <p>
 * 5.1 Отримання конкретного замовлення
 * 5.2 Отримання всіх замовлень
 * 5.3 Додавання замовлення
 * 5.4 Додавання product до замовлення
 * ВАЖЛИВО: Базу даних не потрібно підключати. OrderService буде зберігати певні дані.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    private static int counter = 0;

    private int id;
    private LocalDate date;
    private double cost;
    private List<Product> products;

    public Order(Order order) {
        this.id = counter++;
        this.date = LocalDate.now();
        this.cost = order.cost;
        this.products = order.products;
    }
}


