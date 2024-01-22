package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Course;
import com.abdelaziz.school.entity.Grade;
import com.abdelaziz.school.entity.Student;
import com.abdelaziz.school.exception.GradeNotFoundException;
import com.abdelaziz.school.repository.CourseRepository;
import com.abdelaziz.school.repository.GradeRepository;
import com.abdelaziz.school.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRepository  ;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;


    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId,courseId);
        if (grade.isPresent())
        {
            return grade.get();
        }
        else
        {
            throw new GradeNotFoundException(studentId,courseId);
        }
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();

        grade.setCourse(course);
        grade.setStudent(student);

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (grade.isPresent()) {
            Grade tempGrade = grade.get();
            tempGrade.setScore(score);
            return gradeRepository.save(tempGrade);
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId,courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll() ;
    }
}
