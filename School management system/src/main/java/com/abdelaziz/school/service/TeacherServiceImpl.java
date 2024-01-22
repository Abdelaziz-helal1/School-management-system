package com.abdelaziz.school.service;

import com.abdelaziz.school.entity.Teacher;
import com.abdelaziz.school.exception.EntityNotFoundException;
import com.abdelaziz.school.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository ;

    @Override
    public Teacher getTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent())
        {
            return teacher.get() ;
        }
        else
        {
            throw new EntityNotFoundException(id, Teacher.class);
        }
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent())
        {
            teacherRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(id, Teacher.class);
        }
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll() ;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher, Long id) {
        // we need to check first if the teacher exist or not :
        Teacher tempTeacher = getTeacher(id);

        // update the teacher record :
        tempTeacher.setFirstName(teacher.getFirstName());
        tempTeacher.setLastName(teacher.getLastName());
        tempTeacher.setEmail(teacher.getEmail());
        tempTeacher.setCourses(teacher.getCourses());

        // save the record into the database:
        teacherRepository.save(tempTeacher) ;

        return tempTeacher ;
    }
}
