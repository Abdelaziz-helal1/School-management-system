package com.abdelaziz.school.controller;

import com.abdelaziz.school.entity.Teacher;
import com.abdelaziz.school.exception.ErrorResponse;
import com.abdelaziz.school.service.TeacherService;
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

@Tag(name = "Teacher Controller", description = "Create and Retrieve Teachers from DB")
@RestController
@RequestMapping("/school/teacher")
@AllArgsConstructor
public class TeacherController {

    private TeacherService teacherService ;

    // handler method to return all teachers in DB:
    @Operation(summary = "Retrieve All Teachers" , description = "Provides a list of Teachers in DB")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of teachers", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Teacher.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getTeachers()
    {
        return new ResponseEntity<>(teacherService.getTeachers(), HttpStatus.OK);
    }

    // handler method to return teacher by id from DB:
    @Operation(summary = "Retrieve Teacher by Id", description = "Retrieve Teacher by ID from DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Teacher doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of teacher", content = @Content(schema = @Schema(implementation = Teacher.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id)
    {
        return new ResponseEntity<>(teacherService.getTeacher(id),HttpStatus.OK);
    }

    // handler method to save teacher into DB:
    @Operation(summary = "Create Teacher", description = "Create Teacher into DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of Teacher"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Teacher> saveTeacher(@Valid @RequestBody Teacher teacher, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(teacherService.saveTeacher(teacher),HttpStatus.CREATED);
    }

    // handler Method to update teacher record in DB:
    @Operation(summary = "Update Teacher", description = "Editing Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful edition of Teacher"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@Valid @RequestBody Teacher teacher, BindingResult bindingResult, @PathVariable Long id)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(teacherService.updateTeacher(teacher,id), HttpStatus.CREATED);
    }

    // handler method to delete teacher from DB:
    @Operation(summary = "Delete Teacher", description = "Delete Teacher from DB by it's Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content - Teacher successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found - Teacher not found with the given ID"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long id)
    {
        teacherService.deleteTeacher(id) ;
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
