package controller;

import Libreria_generica.GenericDAO;
import model.Atencion;
import java.io.IOException;
import java.util.regex.Pattern;

public class AtencionController {

    private GenericDAO<Atencion> dao = new GenericDAO<>("src/doc/atenciones.txt");

    // EXPRESIONES REGULARES
    // Peso: 1 a 3 dígitos, opcionalmente seguidos de un punto y 1 o 2 decimales (ej. 15.5 o 5)
    private static final String REGEX_PESO = "^\\d{1,3}(\\.\\d{1,2})?$";
    // Temperatura: 2 dígitos, opcionalmente un decimal (ej. 38 o 39.5)
    private static final String REGEX_TEMP = "^\\d{2}(\\.\\d{1})?$";
    // Textos (Diagnóstico, Tratamiento, Observación): Letras, números, espacios y puntuación. Mínimo 5 caracteres.
    private static final String REGEX_TEXTO = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s.,:;-]{5,200}$";

    public String validarFormatos(String peso, String temp, String diag, String trat, String obs) {
        if (!Pattern.matches(REGEX_PESO, peso)) return "El peso debe ser numérico (ej. 12.5).";
        if (!Pattern.matches(REGEX_TEMP, temp)) return "La temperatura debe ser numérica de dos dígitos (ej. 38.5).";
        if (!Pattern.matches(REGEX_TEXTO, diag)) return "El diagnóstico debe tener al menos 5 caracteres válidos.";
        if (!Pattern.matches(REGEX_TEXTO, trat)) return "El tratamiento debe tener al menos 5 caracteres válidos.";
        if (!Pattern.matches(REGEX_TEXTO, obs)) return "La observación debe tener al menos 5 caracteres válidos.";
        return "OK";
    }

    public boolean registrarAtencion(String idCita, String idMascota, String fecha, String peso, String temp, String diag, String trat, String obs) {
        // Generar un ID secuencial/aleatorio formato ATE-XXXX
        int numAleatorio = (int)(Math.random() * 9000) + 1000;
        String idAtencion = "ATE-" + numAleatorio;

        Atencion nueva = new Atencion(idAtencion, idCita, idMascota, fecha, peso, temp, diag, trat, obs);

        try {
            dao.guardar(nueva.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar la atención: " + e.getMessage());
            return false;
        }
    }
}