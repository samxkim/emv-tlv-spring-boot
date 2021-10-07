package com.omni.webapp.service;

import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Load the user from the users table by username. If not found, throw UsernameNotFoundException.
        // 2. Convert/wrap the user to a UserDetails object and return it.

        UserEntity user = userRepository.findUserEntityByUserName(username);
        List<SimpleGrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(user.getAuthorities()));
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
