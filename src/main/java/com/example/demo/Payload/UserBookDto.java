package com.example.demo.Payload;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Data
public class UserBookDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String check_In_Date;
    @NotNull
    private  String check_Out_Date;
    @NotNull
    private String Room_Type;
    @NotNull
    private String number_of_Guests;
}
