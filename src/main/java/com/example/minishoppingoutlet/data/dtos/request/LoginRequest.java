package com.example.minishoppingoutlet.data.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "email cannot be blank")
    private String email;

    @Size(min = 6, max= 20, message= "Invalid password")
    private String password;
}
