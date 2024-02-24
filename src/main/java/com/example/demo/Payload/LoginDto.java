package com.example.demo.Payload;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
