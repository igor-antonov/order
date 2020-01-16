package ru.iantonov.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.iantonov.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    Optional<Order> findById(Long id);
}
