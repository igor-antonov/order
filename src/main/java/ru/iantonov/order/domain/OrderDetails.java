package ru.iantonov.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;
    private int price;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    public OrderDetails(String name, int price, Order order) {
        this.name = name;
        this.price = price;
        this.order = order;
    }
}
