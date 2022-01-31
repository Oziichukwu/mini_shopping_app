package com.example.minishoppingoutlet.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseToken {

    private String JwtToken;

    private String email;
}
