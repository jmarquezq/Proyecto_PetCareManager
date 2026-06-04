package model;

public class Vacuna {
    private String idVacuna;
    private String idMascota;
    private String nombreVacuna;
    private String fechaAplicacion;
    private String observacion;

    public Vacuna() {}

    public Vacuna(String idVacuna, String idMascota, String nombreVacuna, String fechaAplicacion, String observacion) {
        this.idVacuna = idVacuna;
        this.idMascota = idMascota;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.observacion = observacion;
    }

    public Vacuna(String[] datos) {
        this.idVacuna = datos[0];
        this.idMascota = datos[1];
        this.nombreVacuna = datos[2];
        this.fechaAplicacion = datos[3];
        this.observacion = datos[4];
    }

    public String getIdVacuna() { return idVacuna; }
    public String getIdMascota() { return idMascota; }
    public String getNombreVacuna() { return nombreVacuna; }
    public String getFechaAplicacion() { return fechaAplicacion; }
    public String getObservacion() { return observacion; }

    public String info() {
        return idVacuna + "," + idMascota + "," + nombreVacuna + "," + fechaAplicacion + "," + observacion;
    }
}