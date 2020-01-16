package ru.iantonov.order.service;

import org.springframework.stereotype.Service;
import ru.iantonov.order.domain.Order;

import java.util.List;

@Service
public class OrderService {

    private final OrderService repository;

    public OrderService(OrderService repository) {
        this.repository = repository;
    }

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        return repository.findById(id);
    }

    public Order add(Order order){
        return repository.add(order);
    }

    public void delete(Long id){
        repository.delete(id);
    }

    public void update(Long id, Order order){
        repository.update(id, order);
    }
}
