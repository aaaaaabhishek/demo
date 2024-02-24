package com.example.demo.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {
    private  String userSetEmail;
    private String email;
    private String password;
    private String username;
    private String name;
}
