package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher getTeacher(Long id);
    Teacher saveTeacher(Teacher teacher);
    void deleteTeacher(Long id);
    List<Teacher> getTeachers();

    Teacher updateTeacher(Teacher teacher , Long id ) ;
}
