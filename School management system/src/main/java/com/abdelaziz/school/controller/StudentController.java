package com.abdelaziz.school.controller;

import com.abdelaziz.school.entity.Student;
import com.abdelaziz.school.exception.ErrorResponse;
import com.abdelaziz.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Student Controller", description = "Create and Retrieve Students in DB")
@RestController
@RequestMapping("/school/student")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService ;

    // handler method to return all students in DB:
    @Operation(summary = "Retrieve All Students" , description = "Provides a list of students in DB")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of students", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents()
    {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    // handler method to return student by Id in DB:
    @Operation(summary = "Retrieve Student by Id", description = "Retrieve Student by ID from DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Student doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of student", content = @Content(schema = @Schema(implementation = Student.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id)
    {
        return new ResponseEntity<>(studentService.getStudent(id),HttpStatus.OK);
    }

    // handler method to save student into DB:
    @Operation(summary = "Create Student", description = "Create Student into DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of Student"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    // handler method to delete the student by id:
    @Operation(summary = "Delete Student", description = "Delete Student from DB by it's Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content - Student successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found - Student not found with the given ID"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudent(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    // handler method to update student:
    @Operation(summary = "Update Student", description = "Editing Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful edition of Student"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student, BindingResult bindingResult, @PathVariable Long id)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentService.updateStudent(student, id),HttpStatus.CREATED);
    }

}
