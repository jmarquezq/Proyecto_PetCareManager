package controller;

import util.GenericDAO;
import model.Cita;
import java.io.IOException;
import java.util.regex.Pattern;

public class CitaController {
    
    private GenericDAO<Cita> dao = new GenericDAO<>("src/datos/citas.txt");
    private static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
    private static final String REGEX_HORA = "^([01]\\d|2[0-3]):([0-5]\\d)$";

    public boolean validarFormatos(String fecha, String hora) {
        boolean fechaValida = Pattern.matches(REGEX_FECHA, fecha);
        boolean horaValida = Pattern.matches(REGEX_HORA, hora);
        return fechaValida && horaValida;
    }

    public boolean agendarCita(String idMascota, String fecha, String hora, String motivo) {
        // Generar un ID secuencial/aleatorio formato CT-XXXX
        int numAleatorio = (int)(Math.random() * 9000) + 1000; // Número entre 1000 y 9999
        String idGenerado = "CT-" + numAleatorio;
        
        Cita nuevaCita = new Cita(idGenerado, idMascota, fecha, hora, motivo);
        
        try {
            dao.guardar(nuevaCita.info()); 
            return true;
        } catch (IOException e) {
            System.err.println("Error al agendar la cita: " + e.getMessage());
            return false;
        }
    }
}