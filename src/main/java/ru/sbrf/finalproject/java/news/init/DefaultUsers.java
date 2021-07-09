package ru.sbrf.finalproject.java.news.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sbrf.finalproject.java.news.models.Role;
import ru.sbrf.finalproject.java.news.models.User;
import ru.sbrf.finalproject.java.news.repositories.RoleRepository;
import ru.sbrf.finalproject.java.news.repositories.UserRepository;

import java.util.Collections;

@Component
public class DefaultUsers implements InitializingBean {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
            User user = new User();
            user.setRoles(Collections.singleton(roleRepository.save(new Role(1L, "ROLE_ADMIN"))));
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setEnabled(true);
            userRepository.save(user);
    }
}
