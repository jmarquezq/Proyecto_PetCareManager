package model;

public class Propietario {
    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;

    public Propietario() {}

    public Propietario(String cedula, String nombre, String telefono, String direccion, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public Propietario(String[] datos) {
        this.cedula = datos[0];
        this.nombre = datos[1];
        this.telefono = datos[2];
        this.direccion = datos[3];
        this.correo = datos[4];
    }

    public String info() {
        return cedula + "," + nombre + "," + telefono + "," + direccion + "," + correo;
    }
}