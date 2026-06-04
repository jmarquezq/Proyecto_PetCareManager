package model;
import Libreria_generica.Entidad;

public class Usuario extends Entidad<String> {
    private String password;
    private String rol;

    public Usuario() {}

    public Usuario(String username, String password, String rol) {
        this.setId(username); // El username es el ID
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String[] datos) {
        this.setId(datos[0]);
        this.password = datos[1];
        this.rol = datos[2];
    }

    public String getUsername() { return getId(); }
    public void setUsername(String username) { this.setId(username); }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String info() {
        return getId() + "," + password + "," + rol;
    }
}