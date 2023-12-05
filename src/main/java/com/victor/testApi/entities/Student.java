package com.victor.testApi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "alumnos")
@Table(name = "alumnos")
public class Student{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @NotNull
    private String nombres;
    @NotBlank
    @NotNull
    private String apellidos;
    @NotBlank
    @NotNull
    private String matricula;
    private double promedio;
    private String fotoPerfilUrl;
    private String password;
    
    public Student(String name, String lastName, String studentId, double studentGPA){
        this.nombres = name;
        this.apellidos = lastName;
        this.matricula = studentId;
        this.promedio = studentGPA;
        this.fotoPerfilUrl = "";
    }

    public Student(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPromedio() {
        return this.promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public void setFotoPerfilUrl(String fotoPerfilUrl){
        this.fotoPerfilUrl = fotoPerfilUrl;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }
}
