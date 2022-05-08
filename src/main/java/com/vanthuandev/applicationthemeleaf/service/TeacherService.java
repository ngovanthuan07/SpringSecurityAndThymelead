package com.vanthuandev.applicationthemeleaf.service;

import com.vanthuandev.applicationthemeleaf.pojos.Teacher;
import com.vanthuandev.applicationthemeleaf.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TeacherService extends UserDetailsService {
    Teacher getTeacherById(int userId);
    boolean addTeacher(Teacher teacher);
    Teacher getTeacher(String username);
}
