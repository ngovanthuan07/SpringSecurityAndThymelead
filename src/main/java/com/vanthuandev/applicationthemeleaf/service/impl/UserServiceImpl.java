package com.vanthuandev.applicationthemeleaf.service.impl;

import com.vanthuandev.applicationthemeleaf.pojos.User;
import com.vanthuandev.applicationthemeleaf.repository.UserRepository;
import com.vanthuandev.applicationthemeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User getUser(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User does not exits");
        }
        return userOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User does not exits");
        }
        User user = userOptional.get();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails
                    .User(user.getUsername(), user.getPassword(), authorities);
    }
}
