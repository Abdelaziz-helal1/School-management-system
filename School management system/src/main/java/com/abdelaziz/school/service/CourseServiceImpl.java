package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Course;
import com.abdelaziz.school.entity.Student;
import com.abdelaziz.school.entity.Teacher;
import com.abdelaziz.school.exception.EntityNotFoundException;
import com.abdelaziz.school.repository.CourseRepository;
import com.abdelaziz.school.repository.StudentRepository;
import com.abdelaziz.school.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepository ;
    private StudentRepository studentRepository ;
    private TeacherRepository teacherRepository ;
    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id) ;
        if (course.isPresent())
        {
            return course.get();
        }
        else
        {
            throw new EntityNotFoundException(id, Course.class);
        }
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id) ;
        if (course.isPresent())
        {
           courseRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(id, Course.class);
        }
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        // first we check if the course exist or not :
        Course tempCourse = getCourse(courseId) ;

        // second we check if the student exist or not :
        Optional<Student> tempStudent = studentRepository.findById(studentId);
        if (tempStudent.isPresent())
        {
            tempCourse.getStudents().add(tempStudent.get());
            tempStudent.get().setCourses(Arrays.asList(tempCourse));
          return courseRepository.save(tempCourse);
        }
        else
        {
            throw new EntityNotFoundException(studentId, Student.class);
        }
    }

    @Override
    public Course addTeacherToCourse(Long teacherId, Long courseId) {
        // first we check if the course exist or not:
        Course tempCourse = getCourse(courseId);

        // second we check of the teacher exist or not :
        Optional<Teacher> tempTeacher = teacherRepository.findById(teacherId);
        if (tempTeacher.isPresent())
        {
            tempCourse.setTeacher(tempTeacher.get());
            tempTeacher.get().setCourses(Arrays.asList(tempCourse));
            return courseRepository.save(tempCourse);
        }
        else
        {
            throw new EntityNotFoundException(teacherId,Teacher.class);
        }
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll() ;
    }

    @Override
    public Course updateCourse(Course course, Long id) {
        // first we check if the course exist or not :
        Course tempCourse = getCourse(id) ;

        // update the course record:
        tempCourse.setCourseName(course.getCourseName());
        tempCourse.setDescription(course.getDescription());

        // save into the database :
        courseRepository.save(tempCourse) ;

        return tempCourse ;
    }
}
