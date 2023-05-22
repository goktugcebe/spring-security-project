package com.example.securityproject.controller;

import com.example.securityproject.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * By default, the methods in an @ControllerAdvice apply globally to all controllers.
 * User selectors to define a more narrow subset of targeted
 */

@ControllerAdvice
public class AddUserToView {

    @ModelAttribute("user")
    public User addUserToModel(Authentication authentication) {
        return authentication != null ? (User) authentication.getPrincipal() : null;
    }
}
