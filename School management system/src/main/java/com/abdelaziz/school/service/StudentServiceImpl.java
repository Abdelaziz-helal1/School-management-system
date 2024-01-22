package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Student;
import com.abdelaziz.school.exception.EntityNotFoundException;
import com.abdelaziz.school.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository ;

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
        {
            return student.get() ;
        }
        else
        {
            throw new EntityNotFoundException(id,Student.class);
        }
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
        {
            studentRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(id, Student.class);
        }
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll() ;
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        // first we need to check if the student existed or not :
       Student tempStudent = getStudent(id) ;

       // update our record:
        tempStudent.setFirstName(student.getFirstName());
        tempStudent.setLastName(student.getLastName());
        tempStudent.setEmail(student.getEmail());
        tempStudent.setCourses(student.getCourses());
        tempStudent.setGrades(student.getGrades());

        // save the updates into the database:
        studentRepository.save(tempStudent) ;

        return tempStudent ;
    }
}
