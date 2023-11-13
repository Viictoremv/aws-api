package com.victor.testApi.entities;

public class Student{
    
    private Long id;
    private String nombres;
    private String apellidos;
    private Long matricula;
    private double promedio;
    
    public Student(Long id, String name, String lastName, Long studentId, double studentGPA){
        this.id = id;
        this.nombres = name;
        this.apellidos = lastName;
        this.matricula = studentId;
        this.promedio = studentGPA;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public Long getMatricula() {
        return this.matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public double getPromedio() {
        return this.promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
