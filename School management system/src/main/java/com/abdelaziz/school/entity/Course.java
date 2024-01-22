package com.abdelaziz.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @NotBlank(message = "Course name can not be blank")
    @NonNull
    @Column(name = "course_name", nullable = false)
    private String courseName ;

    @NotBlank(message = "The Course's description can not be blank")
    @NonNull
    @Column(name = "description", nullable = false)
    private String description ;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students ;

    @ManyToOne()
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher ;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Grade> grades ;
}
