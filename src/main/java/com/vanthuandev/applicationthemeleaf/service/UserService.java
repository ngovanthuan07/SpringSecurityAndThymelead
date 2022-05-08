package com.vanthuandev.applicationthemeleaf.service;

import com.vanthuandev.applicationthemeleaf.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserById(int userId);
    boolean addUser(User user);
    User getUser(String username);
}
