package com.example.minishoppingoutlet.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;
}
