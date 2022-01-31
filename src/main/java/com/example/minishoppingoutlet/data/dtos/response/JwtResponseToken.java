package com.example.minishoppingoutlet.data.dtos.response;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JwtResponseToken {

    private String JwtToken;

    private String email;
}
