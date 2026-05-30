package model;

import Libreria_generica.generic;

public class Mascota {
    private generic<String, String> dt_m;

    public Mascota() {
        dt_m = new generic<>();
    }

    public Mascota(String... data) {
        dt_m = new generic<>(data[0].trim(), data[1].trim(), data[2].trim());
    }

    public String getNombre() {
        return dt_m.getAttributeT1();
    }

    public int getEdad() {
        return Integer.parseInt(dt_m.getAttributeT2().trim());
    }

    public String getEspecie() {
        return dt_m.getAttributeS3();
    }

    public void setNombre(String n) {
        dt_m.setAttributeT1(n);
    }

    public void setEdad(int e) {
        dt_m.setAttributeT2(String.valueOf(e));
    }

    public void setEspecie(String es) {
        dt_m.setAttributeS3(es);
    }

    public String info() {
        return String.format("%s,%d,%s", getNombre(), getEdad(), getEspecie());
    }
}