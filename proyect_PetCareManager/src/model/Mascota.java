package model;

import Libreria_generica.generic;

public class Mascota {
    private generic dt_m;
    private String nombre;
    private String especie;
    private String raza;
    private String edad;
    private String sexo;
    private String propietario; 

    public Mascota() {
        this.dt_m = new generic();
    }

    public Mascota(String idMascota, String nombre, String especie, String raza, String edad, String sexo, String propietario) {
        this.dt_m = new generic(idMascota);
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.propietario = propietario;
    }

    public Mascota(String[] datos) {
        this.dt_m = new generic(datos[0]);
        this.nombre = datos[1];
        this.especie = datos[2];
        this.raza = datos[3];
        this.edad = datos[4];
        this.sexo = datos[5];
        this.propietario = datos[6];
    }
    
    public String getIdMascota() {
        return (String) dt_m.getAttributeT1();
    }

    public void setIdMascota(String idMascota) {
        dt_m.setAttributeT1(idMascota);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String info() {
        return getIdMascota() + "," + nombre + "," + especie + "," + raza + "," + edad + "," + sexo + "," + propietario;
    }
}