package ru.iantonov.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.iantonov.order.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
