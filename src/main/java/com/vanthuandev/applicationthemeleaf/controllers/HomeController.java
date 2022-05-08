package com.vanthuandev.applicationthemeleaf.controllers;


import com.vanthuandev.applicationthemeleaf.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final StudentService studentService;

    @GetMapping(value = {"/home"})
    public String index(Model model) {
        model.addAttribute("title", "Đây Là Trang Home");
        model.addAttribute("students", studentService.findAllStudent());
        return "home/home";
    }
    @GetMapping(value = {"/home/index"})
    public String newHome(Model model) {
        model.addAttribute("title", "Đây Là Trang Home");
        model.addAttribute("students", studentService.findAllStudent());
        return "home/home";
    }
}
