package com.vanthuandev.applicationthemeleaf.controllers.teacher;

import com.vanthuandev.applicationthemeleaf.pojos.Teacher;
import com.vanthuandev.applicationthemeleaf.pojos.User;
import com.vanthuandev.applicationthemeleaf.service.TeacherService;
import com.vanthuandev.applicationthemeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("teacher-panel")
public class TeacherLoginController {
    private final TeacherService teacherService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "home/teacher-login";
    }
}
