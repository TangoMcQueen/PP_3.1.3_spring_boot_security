package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.validator.CheckEmail;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users3")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(min = 2, max = 15, message = "Name must be min 2 symbols")
    @NotBlank(message = "Name should not be empty")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё]{2,15}$")
    private String name;
    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username should not be empty")
    @Size(min = 4, max = 50, message = "Username must be min 4 symbols")
    @Pattern(regexp = "^[A-Za-z]{4,50}$")
    private String username;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, max = 50, message = "Password must be min 4 symbols")
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;
    @Column(name = "last_name")
    @Size(min = 2, max = 25, message = "Last Name must be min 2 symbols")
    @NotBlank(message = "Last Name should not be empty")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё]{2,25}$")
    private String lastName;
    @Column
    @NotNull(message = "Age should not be empty")
    @Min(value = 18, message = "must be greater than 17")
    @Max(value = 100, message = "must be less than 101")
    private int age;
    @Column
    @Size(min = 7, max = 40, message = "Email must be min 7 symbols")
    @CheckEmail
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleList = new HashSet<>();

    public User() {   }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, Set<Role> roleList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleList = roleList;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String name) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoleList();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public String roleToString(){
        StringBuilder sb = new StringBuilder();
        for(Role role: roleList){
            sb.append(role.getNameRole()).append(" ");
        }
        return sb.toString();
    }
}


