package ru.iantonov.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;
    private String comment;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    public OrderDetails(String name, String comment, LocalDate creationDate) {
        this.name = name;
        this.comment = comment;
        this.creationDate = LocalDate.now();
    }
}
