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
import ru.iantonov.order.repository.OrderDetailsRepository;
import ru.iantonov.order.repository.OrderRepository;
import ru.iantonov.order.service.OrderDetailsService;
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
public class OderDetailsServiceTest {
    @MockBean
    private OrderDetailsRepository repository;
    @Autowired
    private OrderDetailsService detailsService;
    private Order order;
    private OrderDetails orderDetails;

    @Before
    public void prepare() {
        order = new Order();
        orderDetails = new OrderDetails("телефон", 800, order);
    }

    @Test
    public void findAll() {
        given(repository.findByOrderId(1L)).willReturn(Collections.singletonList(orderDetails));
        assertEquals(detailsService.findByOrderId(1L).size(), 1);
    }
}