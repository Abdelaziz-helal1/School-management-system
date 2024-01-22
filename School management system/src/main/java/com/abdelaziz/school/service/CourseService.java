package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Course;

import java.util.List;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    Course addStudentToCourse(Long studentId, Long courseId);
    Course addTeacherToCourse(Long teacherId, Long courseId);
    List<Course> getCourses();
    Course updateCourse(Course course , Long id ) ;
}
