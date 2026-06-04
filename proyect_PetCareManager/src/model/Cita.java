package model;
import Libreria_generica.Entidad;

public class Cita extends Entidad<String> {
    private String idMascota;
    private String fecha;
    private String hora;
    private String motivo;

    public Cita() {}

    public Cita(String idCita, String idMascota, String fecha, String hora, String motivo) {
        this.setId(idCita);
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Cita(String[] datos) {
        this.setId(datos[0]);
        this.idMascota = datos[1];
        this.fecha = datos[2];
        this.hora = datos[3];
        this.motivo = datos[4];
    }

    @Override
    public String info() {
        return getId() + "," + idMascota + "," + fecha + "," + hora + "," + motivo;
    }
}