package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.finalproject.java.news.dto.UserDTO;
import ru.sbrf.finalproject.java.news.models.User;
import ru.sbrf.finalproject.java.news.services.userdetails.UserService;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "Registration page";
    }


    @PostMapping("/registration")
    public String addUser(@RequestBody UserDTO userForm) {

        if (!userForm.getPasswordConfirm().equals(userForm.getPassword())) {
            return "Пароли не совпадают";
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        if (!userService.saveUser(user)) {
            return "Пользователь с таким именем уже существует";
        }
        else {
            return "Пользователь с именем \"" + user.getUsername() + "\" успешно зарегистрирован";
        }
    }
}
