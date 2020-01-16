package ru.iantonov.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(name = "pass", nullable = false)
    private String password;
    @Column(name = "creation_date")
    private LocalDate creationDate;


    public User(String login, String password){
        this.login = login;
        this.password = password;
        this.creationDate = LocalDate.now();
    }
}
