package com.victor.testApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.victor.testApi.entities.Student;
import com.victor.testApi.services.BucketService;
import com.victor.testApi.services.StudentService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
public class StudentController {
    
    private final StudentService studentService;
    private final BucketService bucketService;

    @Autowired
    public StudentController(StudentService studentService, BucketService bucketService){
        this.studentService = studentService;
        this.bucketService = bucketService;
    }

    @GetMapping("/alumnos")
    public List<Student> getStudents(){
        // this.bucketService.uploadPhoto();
        System.out.println(this.studentService.getStudents());
        return this.studentService.getStudents();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id){
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

    @PostMapping("/alumnos/{id}/fotoPerfil")
    public ResponseEntity<Student> addProfilePhoto(@RequestPart("foto") MultipartFile profilePhoto, @PathVariable int id){
        Student student = this.studentService.searchStudent(id);
        String fileName = String.valueOf(student.getId());
        this.bucketService.uploadPhoto(profilePhoto, fileName);
        student.setFotoPerfilUrl(this.bucketService.getUrl(fileName).toString());
        this.studentService.modifyStudent(student, id);
        System.out.println(student.getFotoPerfilUrl());
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Student> modifyStudent(@PathVariable int id, @RequestBody @Valid Student student){
        this.studentService.modifyStudent(student, id);
        return new ResponseEntity<Student>(this.studentService.searchStudent(id), HttpStatus.OK);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable int id){
        if(this.studentService.searchStudent(id) == null){
            return new ResponseEntity<String>("failure", HttpStatus.NOT_FOUND);
        }else{
            this.studentService.removeStudent(id);
            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }
    }
}
