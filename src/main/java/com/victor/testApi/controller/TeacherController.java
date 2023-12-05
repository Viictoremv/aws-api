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
import org.springframework.web.bind.annotation.RestController;

import com.victor.testApi.entities.Teacher;
import com.victor.testApi.services.TeacherService;

import jakarta.validation.Valid;

@RestController
@Validated
public class TeacherController {
    
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/profesores")
    public List<Teacher> getTeachers(){
        return this.teacherService.getTeachers();
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable int id){
        Teacher teacher = this.teacherService.searchTeacher(id);
        if(teacher == null){
            return new ResponseEntity<String>("failure", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
        }
    }

    @PostMapping("/profesores")
    public ResponseEntity<Teacher> addTeacher(@RequestBody @Valid Teacher teacher){
        this.teacherService.addTeacher(teacher);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.CREATED);
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Teacher> modifyTeacher(@PathVariable int id, @RequestBody @Valid Teacher teacher){
        this.teacherService.modifyTeacher(teacher, id);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<String> removeTeacher(@PathVariable int id){
        if(this.teacherService.searchTeacher(id) == null){
            return new ResponseEntity<String>("failure", HttpStatus.NOT_FOUND);
        }else{
            this.teacherService.removeTeacher(id);
            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }
    }
}
