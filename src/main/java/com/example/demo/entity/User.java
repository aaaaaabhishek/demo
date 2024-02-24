package com.example.demo.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String username;
    private boolean emailVerified;
    @ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName ="id")
    )
    private Set<Role> roles;
}
