package controller;

import util.GenericDAO;
import model.Propietario;
import java.io.IOException;
import java.util.regex.Pattern;

public class PropietarioController {
    private GenericDAO<Propietario> dao = new GenericDAO<>("src/datos/propietarios.txt");

    // EXPRESIONES REGULARES
    private static final String REGEX_CEDULA = "^\\d{10}$";
    private static final String REGEX_TELEFONO = "^\\d{10}$";
    private static final String REGEX_CORREO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    // Permite letras (incluyendo acentos y ñ), números, espacios, comas, puntos y guiones (de 5 a 100 caracteres)
    private static final String REGEX_DIRECCION = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s.,#-]{5,100}$";
    // Solo letras y espacios (de 3 a 50 caracteres)
    private static final String REGEX_NOMBRE = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]{3,50}$";

    // Método que devuelve un mensaje de error específico si falla la validación, o "OK" si todo es correcto.
    public String validarFormatos(String cedula, String nombre, String telefono, String direccion, String correo) {
        if (!Pattern.matches(REGEX_CEDULA, cedula)) return "La cédula debe tener exactamente 10 números.";
        if (!Pattern.matches(REGEX_NOMBRE, nombre)) return "El nombre solo debe contener letras (mínimo 3 caracteres).";
        if (!Pattern.matches(REGEX_TELEFONO, telefono)) return "El teléfono debe tener exactamente 10 números.";
        if (!Pattern.matches(REGEX_DIRECCION, direccion)) return "La dirección es muy corta o tiene caracteres inválidos.";
        if (!Pattern.matches(REGEX_CORREO, correo)) return "El correo electrónico no tiene un formato válido (ej. usuario@mail.com).";
        return "OK";
    }

    public boolean registrarPropietario(String cedula, String nombre, String telefono, String direccion, String correo) {
        Propietario nuevo = new Propietario(cedula, nombre, telefono, direccion, correo);
        try {
            dao.guardar(nuevo.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}