package com.victor.testApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.testApi.entities.Student;
import com.victor.testApi.services.StudentService;

import jakarta.validation.Valid;

@RestController
@Validated
public class StudentController {
    
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/alumnos")
    public List<Student> getStudents(){
        return this.studentService.getStudents();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        Student student = this.studentService.searchStudent(id);
        if(student == null){
            return new ResponseEntity<String>("failure", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
    }

    @PostMapping("/alumnos")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student){
        this.studentService.addStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Student> modifyStudent(@PathVariable Long id, @RequestBody @Valid Student student){
        this.studentService.modifyStudent(student, id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable Long id){
        if(this.studentService.searchStudent(id) == null){
            return new ResponseEntity<String>("failure", HttpStatus.NOT_FOUND);
        }else{
            this.studentService.removeStudent(id);
            return new ResponseEntity<String>("failure", HttpStatus.OK);
        }
    }
}
