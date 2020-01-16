package ru.iantonov.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.iantonov.order.domain.Order;
import ru.iantonov.order.domain.OrderDetails;
import ru.iantonov.order.repository.OrderRepository;
import ru.iantonov.order.service.OrderService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OderServiceTest {
    @MockBean
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    private Order order;

    @Before
    public void prepare() {
        OrderDetails orderDetails = new OrderDetails("багаж", "доставлен");
        order = new Order("q123");
        order.setOrderDetails(orderDetails);
    }

    @Test
    public void findAll() {
        given(orderRepository.findAll()).willReturn(Collections.singletonList(order));
        assertEquals(orderService.findAll().size(), 1);
    }
}