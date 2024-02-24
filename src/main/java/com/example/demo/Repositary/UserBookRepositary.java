package com.example.demo.Repositary;

import com.example.demo.Payload.UserBookDto;
import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepositary extends JpaRepository<Book,Long> {
}
