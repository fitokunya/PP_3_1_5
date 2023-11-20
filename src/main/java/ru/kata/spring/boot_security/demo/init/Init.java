package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class Init {

    private final UserService userService;

    private final RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userUser = new User("user", "user", 23, "user@user.ru", "user",
                passwordEncoder.encode("user"));
        userUser.setRoles(Collections.singletonList(roleUser));
        userService.add(userUser);

        User userAdmin = new User("admin", "admin", 23, "admin@admin.ru", "admin",
                passwordEncoder.encode("admin"));
        userAdmin.setRoles(Collections.singletonList(roleAdmin));
        userService.add(userAdmin);
    }
}
