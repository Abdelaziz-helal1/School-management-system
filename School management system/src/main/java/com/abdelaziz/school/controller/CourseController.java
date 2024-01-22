package com.abdelaziz.school.controller;

import com.abdelaziz.school.entity.Course;
import com.abdelaziz.school.exception.ErrorResponse;
import com.abdelaziz.school.service.CourseService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Course controller", description = "Create and Retrieve Courses in DB")
@RestController
@RequestMapping("/school/course")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService ;

    // handler method to return all courses from DB :
    @Operation(summary = "Retrieve All Courses" , description = "Provides a list of courses in DB")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of courses", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Course.class))))
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    // handler method to get course by id from DB:
    @Operation(summary = "Retrieve Course by Id", description = "Retrieve Course by it's ID from DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Course doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Course", content = @Content(schema = @Schema(implementation = Course.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    // handler method to save course into DB:
    @Operation(summary = "Create Course", description = "Create course into DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of Course"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    // handler method to delete course from DB by id:
    @Operation(summary = "Delete Course", description = "Delete Course from DB by it's Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content - Course successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found - Course not found with the given ID"),
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // handler method to update course in DB:
    @Operation(summary = "Update Course", description = "Editing Course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful edition of Course"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course , BindingResult bindingResult , @PathVariable Long id )
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(courseService.updateCourse(course,id),HttpStatus.CREATED);
    }

    // handler method to add student to course by Student ID and Course ID :
    @Operation(summary = "Add Student to Course", description = "Adding Student to Course by course Id and student Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Student addition to course"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/register/course/{courseId}/student/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> addStudentToCourse(@PathVariable Long courseId,@PathVariable Long studentId)
    {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId,courseId),HttpStatus.CREATED);
    }

    // handler method to add teacher to course by teacher ID and Course ID :
    @Operation(summary = "Add Teacher to Course",description = "Adding Teacher to Course by course Id and Teacher Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Teacher Addition to course"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/register/course/{courseId}/teacher/{teacherId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> addTeacherToCourse(@PathVariable Long courseId,@PathVariable Long teacherId)
    {
        return new ResponseEntity<>(courseService.addTeacherToCourse(teacherId,courseId),HttpStatus.CREATED);
    }
}
