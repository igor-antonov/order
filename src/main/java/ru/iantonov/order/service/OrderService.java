package ru.iantonov.order.service;

import org.springframework.stereotype.Service;
import ru.iantonov.order.domain.Order;
import ru.iantonov.order.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(Exception::new);
    }

    public Order add(Order order){
        return repository.save(order);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Order updateById(Long id, Order order) throws Exception {
        Order orderOld = findById(id);
        order.setId(orderOld.getId());
        return repository.save(order);
    }
}
