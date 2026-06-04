package model;

public class Cita {
    private String idCita;
    private String idMascota; // Cambio clave: Solo guardamos el ID
    private String fecha;
    private String hora;
    private String motivo;

    public Cita() {}

    public Cita(String idCita, String idMascota, String fecha, String hora, String motivo) {
        this.idCita = idCita;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Cita(String[] datos) {
        this.idCita = datos[0];
        this.idMascota = datos[1];
        this.fecha = datos[2];
        this.hora = datos[3];
        this.motivo = datos[4];
    }

    public String getIdCita() { return idCita; }
    public String getIdMascota() { return idMascota; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getMotivo() { return motivo; }

    public String info() {
        return idCita + "," + idMascota + "," + fecha + "," + hora + "," + motivo;
    }
}