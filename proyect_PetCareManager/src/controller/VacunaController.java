package controller;

import util.GenericDAO; 
import model.Vacuna;
import java.io.IOException;
import java.util.regex.Pattern;

public class VacunaController {

    private GenericDAO<Vacuna> dao = new GenericDAO<>("src/doc/vacunas.txt");

    private static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
    private static final String REGEX_NOMBRE = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s]{3,50}$";
    private static final String REGEX_OBS = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s.,-]{0,100}$";

    public String validarFormatos(String nombreVacuna, String fecha, String observacion) {
        if (!Pattern.matches(REGEX_NOMBRE, nombreVacuna)) return "El nombre de la vacuna es inválido o muy corto.";
        if (!Pattern.matches(REGEX_FECHA, fecha)) return "Formato de fecha incorrecto (use DD/MM/AAAA).";
        if (!Pattern.matches(REGEX_OBS, observacion)) return "La observación contiene caracteres no permitidos o es muy larga.";
        return "OK";
    }

    public boolean registrarVacuna(String idMascota, String nombreVacuna, String fechaAplicacion, String observacion) {
        int numAleatorio = (int)(Math.random() * 90000) + 10000;
        String idVacuna = "VAC-" + numAleatorio;

        if (observacion.trim().isEmpty()) {
            observacion = "Sin observaciones";
        }
        Vacuna nueva = new Vacuna(idVacuna, idMascota, nombreVacuna, fechaAplicacion, observacion);

        try {
            dao.guardar(nueva.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar vacuna: " + e.getMessage());
            return false;
        }
    }
}