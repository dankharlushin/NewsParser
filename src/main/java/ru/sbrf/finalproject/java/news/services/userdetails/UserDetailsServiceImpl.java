package ru.sbrf.finalproject.java.news.services.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sbrf.finalproject.java.news.models.User;
import ru.sbrf.finalproject.java.news.repositories.UserRepository;

import java.util.Collection;
import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("invalid login or password");
        }
        else {
            Collection<SimpleGrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, roles);
        }
    }
}
