package controller;

import util.GenericDAO;
import model.Cita;
import model.Mascota;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class CitaController {

    private GenericDAO<Cita> dao = new GenericDAO<>("src/doc/citas.txt");
    private GenericDAO<Mascota> mascotaDAO = new GenericDAO<>("src/doc/mascota.txt");

    private static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
    private static final String REGEX_HORA = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";

    public String[] buscarMascota(String termino) {
        try {
            List<String[]> mascotas = mascotaDAO.cargarTodo();
            for (String[] m : mascotas) {
                if (m[0].equalsIgnoreCase(termino) || m[1].equalsIgnoreCase(termino)) {
                    return m;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al buscar mascota: " + e.getMessage());
        }
        return null;
    }

    public String obtenerHistorialTurnos(String idMascota) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------\n");
        boolean tieneCitas = false;
        try {
            List<String[]> citas = dao.cargarTodo();
            for (String[] c : citas) {
                if (c.length == 5 && c[1].equals(idMascota)) {
                    sb.append(c[2]).append(" ").append(c[4]).append("\n");
                    tieneCitas = true;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al leer citas en controlador: " + ex.getMessage());
            return "Error al cargar las citas.";
        }
        if (!tieneCitas) {
            sb.append("Sin atenciones registradas.\n");
        }
        sb.append("--------------------------------\n");
        return sb.toString();
    }

    public List<String[]> obtenerTodasLasCitas() {
        try {
            return dao.cargarTodo();
        } catch (IOException e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
            return null;
        }
    }

    public String validarFormatos(String fecha, String hora) {
        if (fecha.isEmpty() || hora.isEmpty()) {
            return "Llene la fecha y la hora.";
        }
        if (!Pattern.matches(REGEX_FECHA, fecha)) {
            return "Formato de fecha incorrecto (use DD/MM/AAAA).";
        }
        if (!Pattern.matches(REGEX_HORA, hora)) {
            return "Formato de hora incorrecto (use HH:MM).";
        }
        return "OK";
    }

    public boolean agendarCita(String idMascota, String fecha, String hora, String motivo) {
        int numAleatorio = (int)(Math.random() * 9000) + 1000;
        String idCita = "CT-" + numAleatorio;
        Cita nueva = new Cita(idCita, idMascota, fecha, hora, motivo);
        try {
            dao.guardar(nueva.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error al agendar cita: " + e.getMessage());
            return false;
        }
    }
}