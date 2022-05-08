package com.vanthuandev.applicationthemeleaf.repository;

import com.vanthuandev.applicationthemeleaf.pojos.Teacher;
import com.vanthuandev.applicationthemeleaf.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE t.username = ?1")
    Optional<Teacher> findTeacherByUsername(String username);
}
