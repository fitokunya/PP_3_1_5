package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositorys.RoleRepository;

import java.util.List;

@Service
@EnableTransactionManagement(proxyTargetClass = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }


}
