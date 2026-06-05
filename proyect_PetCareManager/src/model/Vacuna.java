package model;

import Libreria_generica.generic;

public class Vacuna {
    private generic dt_v;
    private String nombreVacuna;
    private String fechaAplicacion;
    private String observacion;

    public Vacuna() {
        this.dt_v = new generic();
    }

    public Vacuna(String idVacuna, String idMascota, String nombreVacuna, String fechaAplicacion, String observacion) {
        this.dt_v = new generic(idVacuna, idMascota);
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.observacion = observacion;
    }

    public Vacuna(String[] datos) {
        this.dt_v = new generic(datos[0], datos[1]);
        this.nombreVacuna = datos[2];
        this.fechaAplicacion = datos[3];
        this.observacion = datos[4];
    }

    public String getIdVacuna() {
        return (String) dt_v.getAttributeT1();
    }

    public void setIdVacuna(String idVacuna) {
        dt_v.setAttributeT1(idVacuna);
    }

    public String getIdMascota() {
        return (String) dt_v.getAttributeT2();
    }

    public void setIdMascota(String idMascota) {
        dt_v.setAttributeT2(idMascota);
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String info() {
        return getIdVacuna() + "," + getIdMascota() + "," + nombreVacuna + "," + fechaAplicacion + "," + observacion;
    }
}