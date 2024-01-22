package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();

    Student updateStudent(Student student , Long id );
}
