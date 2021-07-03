package ru.sbrf.finalproject.java.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String login);
}
