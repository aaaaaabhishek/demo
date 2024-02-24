package com.example.demo.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    private String name;
}

