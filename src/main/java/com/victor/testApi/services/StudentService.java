package com.victor.testApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victor.testApi.entities.Student;

@Service
public class StudentService {
    private List<Student> studentsList = new ArrayList<>();

    public List<Student> getStudents() {
        return this.studentsList;
    }

    public StudentService() {
        studentsList.add(new Student(5L, "carlitos", "santana", 10L, 5.5));
        studentsList.add(new Student(6L, "alex", "leon", 11L, 5.5));
        studentsList.add(new Student(7L, "karina", "zabaleta", 12L, 5.5));
        studentsList.add(new Student(8L, "paola", "asencio", 13L, 5.5));
        studentsList.add(new Student(9L, "rey", "misterio", 14L, 5.5));
    }

    public void addStudent(Student student){
        studentsList.add(student);
    }

    public void removeStudent(Long id){
        studentsList.removeIf(student -> student.getId() == id );
    }

    public void modifyStudent(Student student, Long id){
        Student modifiedStudent = searchStudent(id);
        modifiedStudent.setNombres(student.getNombres());
        modifiedStudent.setApellidos(student.getApellidos());
        modifiedStudent.setMatricula(student.getMatricula());
        modifiedStudent.setPromedio(student.getPromedio());
    }

    public Student searchStudent(Long id){
        for(Student student : studentsList){
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
