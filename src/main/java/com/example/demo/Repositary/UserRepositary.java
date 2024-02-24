package com.example.demo.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

import java.util.Optional;

public interface UserRepositary extends JpaRepository<User,Long>{

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Object> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
    User findByEmail(String email);

    void deleteByUsername(String username);
}
