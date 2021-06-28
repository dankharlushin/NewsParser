package ru.sbrf.finalproject.java.news.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=4, message = "Не меньше 4 знаков")
    private String login;

    @Size(min=4, message = "Не меньше 4 знаков")
    private String password;

    private String email;

    private String role;

    private boolean enabled;
}
