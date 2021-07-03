package ru.sbrf.finalproject.java.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.finalproject.java.news.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
