package com.vanthuandev.applicationthemeleaf.controllers;

import com.vanthuandev.applicationthemeleaf.pojos.Student;
import com.vanthuandev.applicationthemeleaf.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentControllers {
    private final StudentService studentService;
    @GetMapping("/student")
    public String student(Model model) {
        model.addAttribute("title", "Đây là trang student");
        model.addAttribute("student", new Student());
        return "home/student";
    }

    @PostMapping("/student")
    public String addStudent(Model model, @ModelAttribute(value = "student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/user/home";
    }

    @GetMapping("/student/{studentId}")
    public String studentById(Model model, @PathVariable(value= "studentId") Long studentId) {
        System.out.println("Student: " + studentService.findOneStudentById(studentId));
        model.addAttribute("test", null);
        model.addAttribute("student", studentService.findOneStudentById(studentId));
        return "/home/student-update";
    }
}
