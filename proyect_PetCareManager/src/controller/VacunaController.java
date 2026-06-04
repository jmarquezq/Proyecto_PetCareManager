package controller;

import Libreria_generica.GenericDAO;
import model.Vacuna;
import java.io.IOException;
import java.util.regex.Pattern;

public class VacunaController {

    private GenericDAO<Vacuna> dao = new GenericDAO<>("src/doc/vacunas.txt");

    // EXPRESIONES REGULARES
    // Fecha: DD/MM/AAAA
    private static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
    // Nombre de vacuna: Letras, números y espacios, de 3 a 50 caracteres (ej. Triple Canina)
    private static final String REGEX_NOMBRE = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s]{3,50}$";
    // Observación: Opcional, pero si se escribe, que acepte letras, números, comas y puntos (hasta 100 caracteres)
    private static final String REGEX_OBS = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s.,-]{0,100}$";

    public String validarFormatos(String nombreVacuna, String fecha, String observacion) {
        if (!Pattern.matches(REGEX_NOMBRE, nombreVacuna)) return "El nombre de la vacuna es inválido o muy corto.";
        if (!Pattern.matches(REGEX_FECHA, fecha)) return "Formato de fecha incorrecto (use DD/MM/AAAA).";
        if (!Pattern.matches(REGEX_OBS, observacion)) return "La observación contiene caracteres no permitidos o es muy larga.";
        return "OK";
    }

    public boolean registrarVacuna(String idMascota, String nombreVacuna, String fechaAplicacion, String observacion) {
        // Generar un ID para la vacuna automáticamente
        int numAleatorio = (int)(Math.random() * 90000) + 10000;
        String idVacuna = "VAC-" + numAleatorio;

        // Si la observación está vacía, le ponemos un guion por defecto para no romper el archivo CSV
        if(observacion.trim().isEmpty()){
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