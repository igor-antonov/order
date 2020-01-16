package ru.iantonov.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.iantonov.order.domain.Order;
import ru.iantonov.order.domain.OrderDetails;
import ru.iantonov.order.repository.OrderDetailsRepository;
import ru.iantonov.order.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ComponentScan
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository detailsRepository;
    private OrderDetails orderDetails;
    private Order order;

    @Before
    public void prepare(){
        OrderDetails orderDetails = new OrderDetails("багаж", "доставлен");
        order = new Order("q123");
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
    }

    @Test
    public void delete(){
        assertEquals(detailsRepository.findAll().size(), 1);
        assertEquals(orderRepository.findAll().size(), 1);
        orderRepository.delete(order);
        assertEquals(detailsRepository.findAll().size(), 0);
        assertEquals(orderRepository.findAll().size(), 0);
    }
}
