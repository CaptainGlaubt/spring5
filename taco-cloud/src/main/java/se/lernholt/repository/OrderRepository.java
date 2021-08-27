package se.lernholt.repository;

import se.lernholt.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
