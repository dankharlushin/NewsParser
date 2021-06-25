package ru.sbrf.finalproject.java.news.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.sbrf.finalproject.java.news.models.User;
import ru.sbrf.finalproject.java.news.repositories.UserRepository;

@Component
public class DefaultUsers implements InitializingBean {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setLogin("1");
        user.setPassword(passwordEncoder.encode("1"));
        user.setEmail("test@mail.com");
        user.setEnabled(true);
        user.setRole("Admin");
        if (!userRepository.existsById(1L)) {
            userRepository.save(user);
        }
    }
}
