package com.victor.testApi.controller;

import java.util.List;
import java.util.Map;

import com.victor.testApi.services.NotificationService;
import com.victor.testApi.services.SessionService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final NotificationService notificationService;
    private final SessionService sessionService;

    @Autowired
    public StudentController(StudentService studentService, BucketService bucketService,
                             NotificationService notificationService, SessionService sessionService){
        this.studentService = studentService;
        this.bucketService = bucketService;
        this.notificationService = notificationService;
        this.sessionService = sessionService;
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

    @PostMapping (value = "/alumnos/{id}/email", produces = "application/json")
    public ResponseEntity<String> publishNotification(@PathVariable int id){
        Student student = this.studentService.searchStudent(id);
        if(student == null){
            return new ResponseEntity<String>("{\"error\": \"Not found\"}", HttpStatus.NOT_FOUND);
        }else{
            notificationService.createNotification(student.getNombres(),
                    student.getApellidos(),
                    student.getPromedio());
            return new ResponseEntity<String>("{\"email sent\"}", HttpStatus.OK);
        }
    }

    @PostMapping (value = "/alumnos/{id}/session/verify", produces = "application/json")
    public ResponseEntity<String> createSession(@PathVariable int id, @RequestBody Map<String, String> requestBody){
        String password = requestBody.get("password");
        Student student = this.studentService.searchStudent(id);
        if(!student.getPassword().equals(password)){
            return new ResponseEntity<String>("{\"error\": \"Not found\"}", HttpStatus.NOT_FOUND);
        }else{
            sessionService.createSession(id);
            return new ResponseEntity<String>("{\"Login Succesfull\"}", HttpStatus.OK);
        }
    }
}
