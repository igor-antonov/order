package ru.iantonov.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.iantonov.order.domain.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
    List<OrderDetails> findAll();
    Optional<OrderDetails> findById(Long id);
}
