package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="BooKData")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String check_In_Date;
    private  String check_Out_Date;
    private String Room_Type;
    private String number_of_Guests;
}
