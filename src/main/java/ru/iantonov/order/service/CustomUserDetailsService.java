package ru.iantonov.order.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.iantonov.order.domain.User;
import ru.iantonov.order.repository.UserRepository;
import ru.iantonov.order.security.CustomUserDetails;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByLogin(s);
        if (!optionalUser.isPresent()){
            throw new UsernameNotFoundException(s);
        }
        return new CustomUserDetails(optionalUser.get());
    }
}