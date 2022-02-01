package com.example.minishoppingoutlet.data.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class PasswordUpdateRequest {

    @Email(message = "email must not be null")
    private String email;

    @Size(min = 6, max = 20, message = "Password cannot be blank")
    private String password;


    @NotBlank(message = "password cannot be blank")
    private String oldPassword;
}
