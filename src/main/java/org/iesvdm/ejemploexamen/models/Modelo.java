package org.iesvdm.ejemploexamen.models;

public class Modelo {

    // Atributos
    private int id;
    private String nombre;
    private int estatura;
    private int edad;
    private String localidad;

    // Constructor
    public Modelo(){

    }

    public Modelo(int id, String nombre, int estatura, int edad, String localidad) {
        this.id = id;
        this.nombre = nombre;
        this.estatura = estatura;
        this.edad = edad;
        this.localidad = localidad;
    }


    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    // Metodos
}
