package com.example.tl01examen0882.Entidades;

public class Contactos {

    private int id;
    private String Pais;
    private String Nombre;
    private String Telefono;
    private  String Nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }
}
