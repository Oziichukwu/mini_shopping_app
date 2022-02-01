package com.example.minishoppingoutlet.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Setter
@Getter
@AllArgsConstructor
public class PasswordResetRequest {

    @Email(message = "Email cannot be blank")
    private String email;

    @NotBlank
    @Size(min = 6 , max = 20, message = "password cannot be blank")
    private String password;

}
