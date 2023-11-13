package com.victor.testApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victor.testApi.entities.Teacher;

@Service
public class TeacherService {
        private List<Teacher> teacherList = new ArrayList<>();

    public List<Teacher> getTeachers() {
        return this.teacherList;
    }

    public TeacherService() {
        teacherList.add(new Teacher(5L, 8, "santana", "calderon", 5));
        teacherList.add(new Teacher(5L, 8, "santana", "calderon", 5));
        teacherList.add(new Teacher(5L, 8, "santana", "calderon", 5));
        teacherList.add(new Teacher(5L, 8, "santana", "calderon", 5));
        teacherList.add(new Teacher(5L, 8, "santana", "calderon", 5));
    }

    public void addTeacher(Teacher teacher){
        teacherList.add(teacher);
    }

    public void removeTeacher(Long id){
        teacherList.removeIf(teacher -> teacher.getId() == id);
    }

    public void modifyTeacher(Teacher teacher, Long id){
        Teacher modifiedTeacher = searchTeacher(id);
        modifiedTeacher.setNombres(teacher.getNombres());
        modifiedTeacher.setApellidos(teacher.getApellidos());
        modifiedTeacher.setNumeroEmpleado(teacher.getNumeroEmpleado());
        modifiedTeacher.setHorasClase(teacher.getHorasClase());
    }

    public Teacher searchTeacher(Long id){
        for(Teacher teacher : teacherList){
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }
}