package controller;

import util.GenericDAO;
import model.Vacuna;
import model.Mascota;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class VacunaController {

    private GenericDAO<Vacuna> dao = new GenericDAO<>("src/doc/vacunas.txt");
    private GenericDAO<Mascota> mascotaDAO = new GenericDAO<>("src/doc/mascota.txt");

    private static final String REGEX_VACUNA = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ\\s.-]{3,50}$";
    private static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
    private static final String REGEX_OBS = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ\\s.,:;-]{0,100}$";

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

    public List<String[]> obtenerTodasLasVacunas() {
        try {
            return dao.cargarTodo();
        } catch (IOException e) {
            System.err.println("Error al cargar vacunas: " + e.getMessage());
            return null;
        }
    }

    public String validarFormatos(String nombreVac, String fecha, String obs) {
        if (nombreVac.isEmpty() || fecha.isEmpty()) {
            return "El nombre de la vacuna y la fecha son obligatorios.";
        }
        if (!Pattern.matches(REGEX_VACUNA, nombreVac)) {
            return "El nombre de la vacuna no es válido (mínimo 3 caracteres).";
        }
        if (!Pattern.matches(REGEX_FECHA, fecha)) {
            return "Formato de fecha incorrecto (use DD/MM/AAAA).";
        }
        if (!Pattern.matches(REGEX_OBS, obs)) {
            return "La observación contiene caracteres no válidos o excede los 100 caracteres.";
        }
        return "OK";
    }

    public boolean registrarVacuna(String idMascota, String nombreVac, String fecha, String obs) {
        int numAleatorio = (int)(Math.random() * 9000) + 1000;
        String idVacuna = "VAC-" + numAleatorio;
        Vacuna nueva = new Vacuna(idVacuna, idMascota, nombreVac, fecha, obs);
        try {
            dao.guardar(nueva.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar vacuna: " + e.getMessage());
            return false;
        }
    }
}