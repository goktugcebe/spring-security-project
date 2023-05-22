package com.example.securityproject.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your password")
    @Size(min = 6, message = "Passwords must be at least 6 characters")
    private String password;
    @NotBlank(message = "Re-enter your password")
    private String rpassword;
    @NotBlank(message = "Enter your first name")
    private String firstname;
    @NotBlank(message = "Enter your last name")
    private String lastname;


}
