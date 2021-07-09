package ru.sbrf.finalproject.java.news.services.userdetails;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.models.Role;
import ru.sbrf.finalproject.java.news.models.User;
import ru.sbrf.finalproject.java.news.repositories.RoleRepository;
import ru.sbrf.finalproject.java.news.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("invalid login or password");
        }

        return user;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User dbUser = userRepository.getUserByUsername(user.getUsername());

        if (dbUser != null) {
            return false;
        }

        else {
            user.setRoles(Collections.singleton(roleRepository.save(new Role(2L, "ROLE_USER"))));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            userRepository.save(user);
            LOGGER.info("A new user with username: \"" + user.getUsername() + "\" has been registered");
            return true;
        }

    }

    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            String username = userRepository.getUserById(id).getUsername();
            userRepository.deleteById(id);
            LOGGER.info("User with username: \"" + username + "\" has been deleted");
            return true;
        }
        else {
            return false;
        }
    }

}
