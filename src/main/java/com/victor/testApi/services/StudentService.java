package com.victor.testApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.testApi.entities.Student;
import com.victor.testApi.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    public void addStudent(Student student){
        this.studentRepository.save(student);
    }

    public void removeStudent(int id){
        this.studentRepository.deleteById(id);
    }

    public void modifyStudent(Student student, int id){
        Student modifiedStudent = searchStudent(id);
        modifiedStudent.setNombres(student.getNombres());
        modifiedStudent.setApellidos(student.getApellidos());
        modifiedStudent.setMatricula(student.getMatricula());
        modifiedStudent.setPromedio(student.getPromedio());
        this.studentRepository.save(modifiedStudent);
    }

    public Student searchStudent(int id){
        return this.studentRepository.findById(id).orElse(null);
    }
}
