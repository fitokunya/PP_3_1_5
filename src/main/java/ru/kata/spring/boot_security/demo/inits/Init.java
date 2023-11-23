package ru.kata.spring.boot_security.demo.inits;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.repositorys.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

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

        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userUser = new User("user", "user", 23, "user@user.ru", "user", "user");
        userUser.setRoles(Collections.singletonList(roleUser));
        userService.add(userUser);

        User userAdmin = new User("admin", "admin", 23, "admin@admin.ru", "admin", "admin");
        userAdmin.setRoles(Collections.singletonList(roleAdmin));
        userService.add(userAdmin);
    }
}
