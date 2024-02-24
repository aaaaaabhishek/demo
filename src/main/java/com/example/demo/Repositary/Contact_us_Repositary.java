package com.example.demo.Repositary;

import com.example.demo.Payload.Contact_us_dto;
import com.example.demo.entity.Contact_us;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Contact_us_Repositary extends JpaRepository<Contact_us,Long> {
}
