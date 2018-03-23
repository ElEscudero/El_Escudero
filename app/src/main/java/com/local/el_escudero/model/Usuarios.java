package com.local.el_escudero.model;


public class Usuarios {
    private String nombre;
    private String email;
    private String direccion;
    private String password;
    private String telefono;
    public Usuarios(String nombre, String direccion, String email, String password, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.password = password;
        this.telefono = telefono;
    }

    public Usuarios(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Por si las moscas creamos un constructor vacio
    public Usuarios(){

    }




}
