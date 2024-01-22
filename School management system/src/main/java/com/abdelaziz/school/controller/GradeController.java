package com.abdelaziz.school.controller;

import com.abdelaziz.school.entity.Grade;
import com.abdelaziz.school.exception.ErrorResponse;
import com.abdelaziz.school.service.CourseService;
import com.abdelaziz.school.service.GradeService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grade Controller", description = "Create and Retrieve grades in DB")
@AllArgsConstructor
@RestController
@RequestMapping("/school/grade")
public class GradeController {

    private GradeService gradeService;
    private CourseService courseService ;
    private StudentService studentService ;

    // handler method to return all grades from DB:
    @Operation(summary = "Retrieve All grades" , description = "Provides a list of grades in DB")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of grades", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Grade.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getGrades()
    {
        return new ResponseEntity<>(gradeService.getAllGrades(), HttpStatus.OK);
    }

    // handler method to return grade based on student id and course id:
    @Operation(summary = "Retrieve Grade", description = "Retrieve grades based on student Id and course Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of grade"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId)
    {
        return new ResponseEntity<>(gradeService.getGrade(studentId,courseId), HttpStatus.OK);
    }

    // handler method to save grade based on student id and course id into DB:
    @Operation(summary = "Create Grade", description = "Create Grade into DB based on student Id and course Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of grade"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId)
    {
        return new ResponseEntity<>(gradeService.saveGrade(grade,studentId,courseId),HttpStatus.CREATED);
    }

    // handler method to update grade based on student id and course id into DB:
    @Operation(summary = "Update Grade", description = "Editing Grade based on student Id and course Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful edition of Grade"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@Valid @RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.updateGrade(grade.getScore(), studentId, courseId), HttpStatus.OK);
    }

    // handler method to delete grade based on student id and course id:
    @Operation(summary = "Delete Grade", description = "Delete Grade from DB by student Id and course Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content - Grade successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found - Grade not found with the given ID's"),
    })
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        gradeService.deleteGrade(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // handler method to return Student's grades based on studentId from DB:
    @Operation(summary = "Retrieve Student's grades", description = "Retrieve Student's grades based on student Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Student's grades"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId), HttpStatus.OK);
    }

    // handler method to return Course's grade based on Course id from DB:
    @Operation(summary = "Retrieve Course's grades", description = "Retrieve Course's grades based on course Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Course's grades"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.getCourseGrades(courseId), HttpStatus.OK);
    }
}
