package com.abdelaziz.school.repository;

import com.abdelaziz.school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // if you want to add custom operations you can add it here .....
}
