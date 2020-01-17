package ru.iantonov.order.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "creation_date")
    private LocalDate creationDate;
    private String comment;

    public Order() {
        this.creationDate = LocalDate.now();
    }
}
