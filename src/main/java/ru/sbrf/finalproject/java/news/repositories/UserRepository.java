package ru.sbrf.finalproject.java.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String login);
    User getUserById(Long id);
}
