package com.victor.testApi.entities;

public class Teacher {
    
    private Long id;
    private int numeroEmpleado;
    private String nombres;
    private String apellidos;
    private int horasClase;
    
    public Teacher(Long id, int numeroEmpleado, String nombres, String apellidos, int horasClase){
        this.id = id;
        this.numeroEmpleado = numeroEmpleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.horasClase = horasClase;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
