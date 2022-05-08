package com.vanthuandev.applicationthemeleaf.service.impl;

import com.vanthuandev.applicationthemeleaf.pojos.Teacher;
import com.vanthuandev.applicationthemeleaf.repository.TeacherRepository;
import com.vanthuandev.applicationthemeleaf.service.TeacherService;
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

@Service("teacherService")
@Transactional
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    public final TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherById(int userId) {
        return null;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return false;
    }

    @Override
    public Teacher getTeacher(String username) {
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByUsername(username);
        if(!teacherOptional.isPresent()) {
            throw new UsernameNotFoundException("Teacher does not exits");
        }
        return teacherOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByUsername(username);
        if(!teacherOptional.isPresent()) {
            throw new UsernameNotFoundException("Teacher does not exits");
        }

        Teacher teacher = teacherOptional.get();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));

        return new org.springframework.security.core.userdetails
                .User(teacher.getUsername(), teacher.getPassword(), authorities);
    }
}
