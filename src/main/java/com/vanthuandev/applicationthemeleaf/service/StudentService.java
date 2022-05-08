package com.vanthuandev.applicationthemeleaf.service;

import com.vanthuandev.applicationthemeleaf.pojos.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudent();
    Student saveStudent(Student student);
    Student findOneStudentById(Long id);
}
