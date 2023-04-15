package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    void saveUser(User user);
    User getUser(Long id);
    void deleteUser(Long id);
    User findUserById(Long id);
    User findByUsername (String username);
    void update (User user, Long id);

    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
