package se.lernholt.repository.jdbc;

import se.lernholt.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
