package com.example.demo.Repositary;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositary extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
