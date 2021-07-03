package ru.sbrf.finalproject.java.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.finalproject.java.news.models.User;
import ru.sbrf.finalproject.java.news.repositories.UserRepository;
import ru.sbrf.finalproject.java.news.services.userdetails.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping ("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        if (userService.deleteUser(id)) {
            return "Пользователь с id " + id + " был успешно удален";
        }
        else {
            return "Пользователь с id " + id + " не найден";
        }
    }

}
