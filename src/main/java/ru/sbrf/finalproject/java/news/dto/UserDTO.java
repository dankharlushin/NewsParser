package ru.sbrf.finalproject.java.news.dto;

import lombok.Data;


@Data
public class UserDTO {

    private String username;
    private String password;
    private String passwordConfirm;
}
