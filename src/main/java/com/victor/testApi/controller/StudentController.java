package com.victor.testApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
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
    public Student getStudent(@PathVariable Long id){
        return this.studentService.searchStudent(id);
    }

    @PostMapping("/alumnos")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student){
        this.studentService.addStudent(student);
    }

    @PutMapping("/alumnos/{id}")
    public void modifyStudent(@PathVariable Long id, @RequestBody Student student){
        this.studentService.modifyStudent(student, id);
    }

    @DeleteMapping("/alumnos/{id}")
    public void removeStudent(@PathVariable Long id){
        this.studentService.removeStudent(id);
    }
}
