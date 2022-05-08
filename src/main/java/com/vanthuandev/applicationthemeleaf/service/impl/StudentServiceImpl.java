package com.vanthuandev.applicationthemeleaf.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vanthuandev.applicationthemeleaf.pojos.Student;
import com.vanthuandev.applicationthemeleaf.repository.StudentRepository;
import com.vanthuandev.applicationthemeleaf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll().isEmpty() ? null : studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        try {
            Map r = this.cloudinary.uploader().upload(student.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            student.setImage((String) r.get("secure_url"));
            return studentRepository.save(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findOneStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(!studentOptional.isPresent()) {
            throw new IllegalStateException();
        }
        Student student = studentOptional.get();
        return student;
    }


}
