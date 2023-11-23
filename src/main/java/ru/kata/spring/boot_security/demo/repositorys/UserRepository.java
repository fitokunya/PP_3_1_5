package ru.kata.spring.boot_security.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.login=?1")
    User findByLoginUser(String name);

    @Override
    Optional<User> findById(Long id);
}
