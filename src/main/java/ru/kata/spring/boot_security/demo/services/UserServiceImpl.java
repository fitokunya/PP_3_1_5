package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositorys.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// UPDATE

@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Optional<User> checkByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLoginUser(login));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLoginUser(String login) {
        return userRepository.findByLoginUser(login);
    }

    @Override
    @Transactional(readOnly = true)
    public User show(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User " + id + " found"));
    }

    @Override
    @Transactional
    public void add(User user) {
        if (checkByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("The user " + user.getLogin() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        if (checkByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("The user " + user.getLogin() + " already exists");
        }
        if (!user.getPassword().equals(show(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
