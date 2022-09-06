package com.edu.lab6.security;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationRequest  {


    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Password confirmation is mandatory")
    private String passwordConfirmation;


    @NotBlank(message = "First name is mandatory")
    private String firstName;


    @NotBlank(message = "Last name is mandatory")
    private String lastName;

}
