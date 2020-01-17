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
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@ComponentScan
@DataJpaTest
public class OrderDetailsRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository detailsRepository;
    private OrderDetails orderDetails;
    private Order order;

    @Before
    public void prepare(){
        order = orderRepository.save(new Order());
        orderDetails = detailsRepository.save(new OrderDetails("телефон", 800, order));
    }

    @Test
    public void deleteOrderDetails(){
        assertEquals(detailsRepository.findById(orderDetails.getId()).orElse(null), orderDetails);
        assertEquals(orderRepository.findAll().size(), 1);
        detailsRepository.deleteById(orderDetails.getId());
        assertNull(detailsRepository.findById(orderDetails.getId()).orElse(null));
    }
}
