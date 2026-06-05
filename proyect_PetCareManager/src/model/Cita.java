package model;

import Libreria_generica.generic;

public class Cita {
    private generic dt_c;
    private String fecha;
    private String hora;
    private String motivo;

    public Cita() {
        this.dt_c = new generic();
    }

    public Cita(String idCita, String idMascota, String fecha, String hora, String motivo) {
        this.dt_c = new generic(idCita, idMascota);
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Cita(String[] datos) {
        this.dt_c = new generic(datos[0], datos[1]);
        this.fecha = datos[2];
        this.hora = datos[3];
        this.motivo = datos[4];
    }
    
    public String getIdCita() {
        return (String) dt_c.getAttributeT1();
    }

    public void setIdCita(String idCita) {
        dt_c.setAttributeT1(idCita);
    }

    public String getIdMascota() {
        return (String) dt_c.getAttributeT2();
    }

    public void setIdMascota(String idMascota) {
        dt_c.setAttributeT2(idMascota);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public String info() {
        return getIdCita() + "," + getIdMascota() + "," + fecha + "," + hora + "," + motivo;
    }
}