package com.vanthuandev.applicationthemeleaf.handlers;

import com.vanthuandev.applicationthemeleaf.pojos.Teacher;
import com.vanthuandev.applicationthemeleaf.pojos.User;
import com.vanthuandev.applicationthemeleaf.service.TeacherService;
import com.vanthuandev.applicationthemeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler  implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        boolean hasTeacherRole = authentication.getAuthorities().stream().anyMatch(
                r -> r.getAuthority().equals("ROLE_TEACHER")
        );
        if(hasTeacherRole) {
            Teacher teacher = teacherService.getTeacher(authentication.getName());
            request.getSession().setAttribute("currentUser", teacher);
        } else {
            User user = userService.getUser(authentication.getName());
            request.getSession().setAttribute("currentUser", user);
        }
        response.sendRedirect("/home");
    }
}
