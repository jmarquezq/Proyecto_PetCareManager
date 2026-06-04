package model;
import Libreria_generica.Entidad;

public class Propietario extends Entidad<String> {
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;

    public Propietario() {}

    public Propietario(String cedula, String nombre, String telefono, String direccion, String correo) {
        this.setId(cedula); // La cédula es el ID
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public Propietario(String[] datos) {
        this.setId(datos[0]);
        this.nombre = datos[1];
        this.telefono = datos[2];
        this.direccion = datos[3];
        this.correo = datos[4];
    }

    @Override
    public String info() {
        return getId() + "," + nombre + "," + telefono + "," + direccion + "," + correo;
    }
}