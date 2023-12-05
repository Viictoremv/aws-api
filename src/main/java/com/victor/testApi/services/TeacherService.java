package com.victor.testApi.services;

import java.util.ArrayList;
import java.util.List;

import com.victor.testApi.entities.Student;
import com.victor.testApi.repository.StudentRepository;
import com.victor.testApi.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.testApi.entities.Teacher;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    public List<Teacher> getTeachers() {
        return this.teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        this.teacherRepository.save(teacher);
    }

    public void removeTeacher(int id){
        this.teacherRepository.deleteById(id);
    }

    public void modifyTeacher(Teacher teacher, int id){
        Teacher modifiedTeacher = searchTeacher(id);
        modifiedTeacher.setNombres(teacher.getNombres());
        modifiedTeacher.setApellidos(teacher.getApellidos());
        modifiedTeacher.setNumeroEmpleado(teacher.getNumeroEmpleado());
        modifiedTeacher.setHorasClase(teacher.getHorasClase());
        this.teacherRepository.save(modifiedTeacher);
    }

    public Teacher searchTeacher(int id){
        return this.teacherRepository.findById(id).orElse(null);
    }
}
