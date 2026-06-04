package model;
import Libreria_generica.Entidad;

public class Vacuna extends Entidad<String> {
    private String idMascota;
    private String nombreVacuna;
    private String fechaAplicacion;
    private String observacion;

    public Vacuna() {}

    public Vacuna(String idVacuna, String idMascota, String nombreVacuna, String fechaAplicacion, String observacion) {
        this.setId(idVacuna);
        this.idMascota = idMascota;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.observacion = observacion;
    }

    public Vacuna(String[] datos) {
        this.setId(datos[0]);
        this.idMascota = datos[1];
        this.nombreVacuna = datos[2];
        this.fechaAplicacion = datos[3];
        this.observacion = datos[4];
    }

    @Override
    public String info() {
        return getId() + "," + idMascota + "," + nombreVacuna + "," + fechaAplicacion + "," + observacion;
    }
}