package controller;

import util.GenericDAO; 
import model.Propietario;
import java.io.IOException;
import java.util.regex.Pattern;

public class PropietarioController {
    private GenericDAO<Propietario> dao = new GenericDAO<>("src/doc/propietarios.txt");

    private static final String REGEX_CEDULA = "^\\d{10}$";
    private static final String REGEX_TELEFONO = "^\\d{10}$";
    private static final String REGEX_CORREO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String REGEX_DIRECCION = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s.,#-]{5,100}$";
    private static final String REGEX_NOMBRE = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]{3,50}$";

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
            System.err.println("Error al guardar el propietario: " + e.getMessage());
            return false;
        }
    }
}