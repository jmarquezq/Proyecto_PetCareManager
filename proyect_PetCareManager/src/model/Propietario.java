package model;

import Libreria_generica.generic;

public class Propietario {
    private generic dt_p;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;

    public Propietario() {
        this.dt_p = new generic();
    }

    public Propietario(String cedula, String nombre, String telefono, String direccion, String correo) {
        this.dt_p = new generic(cedula);
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public Propietario(String[] datos) {
        this.dt_p = new generic(datos[0]);
        this.nombre = datos[1];
        this.telefono = datos[2];
        this.direccion = datos[3];
        this.correo = datos[4];
    }

    public String getCedula() {
        return (String) dt_p.getAttributeT1();
    }

    public void setCedula(String cedula) {
        dt_p.setAttributeT1(cedula);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String info() {
        return getCedula() + "," + nombre + "," + telefono + "," + direccion + "," + correo;
    }
}