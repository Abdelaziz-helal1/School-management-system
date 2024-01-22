package com.abdelaziz.school.repository;

import com.abdelaziz.school.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // if you want to add custom operations you can add it here .....
}
