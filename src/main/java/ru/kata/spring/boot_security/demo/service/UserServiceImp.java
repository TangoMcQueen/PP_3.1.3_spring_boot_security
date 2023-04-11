package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import java.util.List;


@Service
public class UserServiceImp implements UserService{
    private UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.getById(id);
    }
    @Override
    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
