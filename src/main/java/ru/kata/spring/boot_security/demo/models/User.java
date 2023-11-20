package ru.kata.spring.boot_security.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    @Size(min = 3, max = 24, message = "Please write the login in size from 5 to 24 characters")
    private String login;

    @Column(name = "password")
    @Size(min = 3, max = 255, message = "Please write the login in size from 5 to 24 characters")
    private String password;

    @Column(name = "roles")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Column(name = "name")
    @NotEmpty(message = "Please write your name")
    @Size(min = 3, max = 24, message = "Please write the name in size from 3 to 24 characters")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "Please write your last name")
    @Size(min = 3, max = 24, message = "Please write the last name in size from 3 to 24 characters")
    private String lastname;

    @Column(name = "age")
    @Min(value = 7, message = "Please specify the age of at least 7 years")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Please write your EMail")
    @Email(message = "Please write EMail with format ***@***.**")
    private String email;

    public User() {
    }

    public User(String name, String lastname, int age, String email,
                String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "roles=" + roles +
                '}';
    }
}
