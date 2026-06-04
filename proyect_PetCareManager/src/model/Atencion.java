package model;
import Libreria_generica.Entidad;

public class Atencion extends Entidad<String> {
    private String idCita;
    private String idMascota;
    private String fecha;
    private String peso;
    private String temperatura;
    private String diagnostico;
    private String tratamiento;
    private String observacion;

    public Atencion() {}

    public Atencion(String idAtencion, String idCita, String idMascota, String fecha, String peso, String temperatura, String diagnostico, String tratamiento, String observacion) {
        this.setId(idAtencion);
        this.idCita = idCita;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.peso = peso;
        this.temperatura = temperatura;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observacion = observacion;
    }

    public Atencion(String[] datos) {
        this.setId(datos[0]);
        this.idCita = datos[1];
        this.idMascota = datos[2];
        this.fecha = datos[3];
        this.peso = datos[4];
        this.temperatura = datos[5];
        this.diagnostico = datos[6];
        this.tratamiento = datos[7];
        this.observacion = datos[8];
    }

    @Override
    public String info() {
        return getId() + "," + idCita + "," + idMascota + "," + fecha + "," + peso + "," + temperatura + "," + diagnostico + "," + tratamiento + "," + observacion;
    }
}