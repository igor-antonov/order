package ru.iantonov.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.iantonov.order.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
