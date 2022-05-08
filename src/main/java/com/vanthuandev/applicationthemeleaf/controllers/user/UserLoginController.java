package com.vanthuandev.applicationthemeleaf.controllers.user;

import com.vanthuandev.applicationthemeleaf.pojos.User;
import com.vanthuandev.applicationthemeleaf.service.StudentService;
import com.vanthuandev.applicationthemeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user-panel")
public class UserLoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "home/user-login";
    }
}
