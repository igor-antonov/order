package ru.iantonov.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "order_number")
    private String orderNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    public Order(String orderNumber, OrderDetails orderDetails) {
        this.orderNumber = orderNumber;
        this.orderDetails = orderDetails;
    }
}
