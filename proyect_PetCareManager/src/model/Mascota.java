package model;

public class Mascota {
    private String idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private String edad;
    private String sexo;
    private String propietario;

    public Mascota() {}

    public Mascota(String idMascota, String nombre, String especie, String raza, String edad, String sexo, String propietario) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.propietario = propietario;
    }

    public Mascota(String[] datos) {
        this.idMascota = datos[0];
        this.nombre = datos[1];
        this.especie = datos[2];
        this.raza = datos[3];
        this.edad = datos[4];
        this.sexo = datos[5];
        this.propietario = datos[6];
    }

    public String info() {
        return idMascota + "," + nombre + "," + especie + "," + raza + "," + edad + "," + sexo + "," + propietario;
    }
}