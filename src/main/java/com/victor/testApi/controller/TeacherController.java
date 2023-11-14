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

import com.victor.testApi.entities.Teacher;
import com.victor.testApi.services.TeacherService;

@RestController
public class TeacherController {
    
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/profesores")
    public List<Teacher> getTeachers(){
        return this.teacherService.getTeachers();
    }

    @GetMapping("/profesores/{id}")
    public Teacher getTeacher(@PathVariable int id){
        return this.teacherService.searchTeacher(id);
    }

    @PostMapping("/profesores")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@RequestBody Teacher teacher){
        this.teacherService.addTeacher(teacher);
    }

    @PutMapping("/profesores/{id}")
    public void modifyTeacher(@PathVariable int id, @RequestBody Teacher teacher){
        this.teacherService.modifyTeacher(teacher, id);
    }

    @DeleteMapping("/profesores/{id}")
    public void removeTeacher(@PathVariable Long id){
        this.teacherService.removeTeacher(id);
    }
}
