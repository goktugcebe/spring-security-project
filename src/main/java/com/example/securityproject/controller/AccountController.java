package com.example.securityproject.controller;

import com.example.securityproject.dto.UserDTO;
import com.example.securityproject.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO, Model model) {
        model.addAttribute("userDTO", userDTO);
        return "register";
    }

    @PostMapping("/register")
    public String save(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //check if the user exist
        if (userService.userExist(userDTO.getEmail())) {
            bindingResult.addError(new FieldError("userDTO", "email", "Email address already in use!"));
        }
        //check if the passwords match
        if (userDTO.getPassword() != null && userDTO.getRpassword() != null) {
            if (!userDTO.getPassword().equals(userDTO.getRpassword())) {
                bindingResult.addError(new FieldError("userDTO", "rpassword", "Password must match!"));
            }
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        //show success message
        redirectAttributes.addFlashAttribute("message",
                "Success! Your registration is now completed!");

        log.info(">> userDTO : {}", userDTO.toString());
        userService.register(userDTO);
        return "redirect:/login";
    }

}
