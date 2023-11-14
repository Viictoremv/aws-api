package com.victor.testApi.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class Teacher {
    
    private int id;
    private int numeroEmpleado;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String nombres;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String apellidos;

    @Positive
    private int horasClase;
    
    public Teacher(int id, int numeroEmpleado, String nombres, String apellidos, int horasClase){
        this.id = id;
        this.numeroEmpleado = numeroEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.horasClase = horasClase;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroEmpleado() {
        return this.numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
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

    public int getHorasClase() {
        return this.horasClase;
    }

    public void setHorasClase(int horasClase) {
        this.horasClase = horasClase;
    }

}
