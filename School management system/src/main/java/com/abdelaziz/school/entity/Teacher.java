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
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @NotBlank(message = "First name can not be blank")
    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName ;

    @NotBlank(message = "Last name can not be blank")
    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName ;

    @Column(name = "email")
    private String email ;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courses ;
}
