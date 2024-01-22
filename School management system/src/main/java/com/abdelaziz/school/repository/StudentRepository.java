package com.abdelaziz.school.repository;

import com.abdelaziz.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // if you want to add custom operations you can add it here .....
}
