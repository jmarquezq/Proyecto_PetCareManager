package model;

import Libreria_generica.generic;

public class Atencion {
    private generic dt_a;
    private String idMascota;
    private String fecha;        
    private double peso;        
    private String temperatura;  
    private String diagnostico;
    private String tratamiento;
    private String observacion;

    public Atencion() {
        this.dt_a = new generic();
    }

    public Atencion(String idAtencion, String idCita, String idMascota, String fecha, double peso, String temperatura, String diagnostico, String tratamiento, String observacion) {
        this.dt_a = new generic(idAtencion, idCita);
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.peso = peso;
        this.temperatura = temperatura;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observacion = observacion;
    }

    public Atencion(String[] datos) {
        this.dt_a = new generic(datos[0], datos[1]);
        this.idMascota = datos[2];
        this.fecha = datos[3];
        this.peso = Double.parseDouble(datos[4]); 
        this.temperatura = datos[5];
        this.diagnostico = datos[6];
        this.tratamiento = datos[7];
        this.observacion = datos[8];
    }
    
    public String getIdAtencion() {
        return (String) dt_a.getAttributeT1();
    }

    public void setIdAtencion(String idAtencion) {
        dt_a.setAttributeT1(idAtencion);
    }

    public String getIdCita() {
        return (String) dt_a.getAttributeT2();
    }

    public void setIdCita(String idCita) {
        dt_a.setAttributeT2(idCita);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPeso() { 
        return peso;
    }

    public void setPeso(double peso) { 
        this.peso = peso;
    }

    public String info() {
        return getIdAtencion() + "," + getIdCita() + "," + idMascota + "," + fecha + "," + peso + "," + temperatura + "," + diagnostico + "," + tratamiento + "," + observacion;
    }
}