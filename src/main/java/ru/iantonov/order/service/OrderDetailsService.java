package ru.iantonov.order.service;

import org.springframework.stereotype.Service;
import ru.iantonov.order.domain.OrderDetails;
import ru.iantonov.order.repository.OrderDetailsRepository;

import java.util.List;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository repository;

    public OrderDetailsService(OrderDetailsRepository repository) {
        this.repository = repository;
    }

    public List<OrderDetails> findAll(){
        return repository.findAll();
    }

    public OrderDetails findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(Exception::new);
    }

    public OrderDetails save(OrderDetails orderDetails){
        return repository.save(orderDetails);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public OrderDetails update(Long id, OrderDetails orderDetails) throws Exception {
        OrderDetails orderDetailsOld = findById(id);
        orderDetails.setId(orderDetailsOld.getId());
        return repository.save(orderDetails);
    }
}
