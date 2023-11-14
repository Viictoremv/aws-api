package com.victor.testApi.entities;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class Student{
    
    private int id;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String nombres;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String apellidos;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^A\\d+$")
    private String matricula;
    @Digits(integer=1, fraction=3)
    private double promedio;
    
    public Student(int id, String name, String lastName, String studentId, double studentGPA){
        this.id = id;
        this.nombres = name;
        this.apellidos = lastName;
        this.matricula = studentId;
        this.promedio = studentGPA;
    }

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
}
