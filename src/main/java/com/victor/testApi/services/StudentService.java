package com.victor.testApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victor.testApi.entities.Student;

@Service
public class StudentService {
    private List<Student> studentsList = new ArrayList<>();

    public StudentService(){
        studentsList.add(new Student(1, "Rey", "Misterio", "A15", 5.15));
    }

    public List<Student> getStudents() {
        return this.studentsList;
    }

    public void addStudent(Student student){
        studentsList.add(student);
    }

    public void removeStudent(Long id){
        studentsList.removeIf(student -> student.getId() == id );
    }

    public void modifyStudent(Student student, Long id){
        Student modifiedStudent = searchStudent(id);
        modifiedStudent.setId(student.getId());
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
