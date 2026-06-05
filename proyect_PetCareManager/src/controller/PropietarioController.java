package controller;

import util.GenericDAO;
import model.Propietario;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class PropietarioController {

    private GenericDAO<Propietario> dao = new GenericDAO<>("src/doc/propietarios.txt");

    private static final String REGEX_CEDULA = "^\\d{10}$";
    private static final String REGEX_NOMBRE = "^[A-Za-zñÑáéíóúÁÉÍÓÚ\\s]{3,50}$";
    private static final String REGEX_TELEFONO = "^09\\d{8}$";
    private static final String REGEX_DIRECCION = "^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ\\s.,#-]{5,100}$";
    private static final String REGEX_CORREO = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public List<String[]> obtenerTodosLosPropietarios() {
        try {
            return dao.cargarTodo();
        } catch (IOException e) {
            System.err.println("Error al cargar propietarios: " + e.getMessage());
            return null;
        }
    }

    public String validarFormatos(String cedula, String nombre, String telefono, String direccion, String correo) {
        if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }
        
        if (!Pattern.matches(REGEX_CEDULA, cedula)) return "La cédula debe tener exactamente 10 dígitos numéricos.";
        if (!Pattern.matches(REGEX_NOMBRE, nombre)) return "El nombre debe contener solo letras y un mínimo de 3 caracteres.";
        if (!Pattern.matches(REGEX_TELEFONO, telefono)) return "El teléfono debe empezar con 09 y tener 10 dígitos.";
        if (!Pattern.matches(REGEX_DIRECCION, direccion)) return "La dirección debe tener al menos 5 caracteres válidos.";
        if (!Pattern.matches(REGEX_CORREO, correo)) return "El correo electrónico no tiene un formato válido (ejemplo@dominio.com).";
        
        return "OK";
    }

    public boolean registrarPropietario(String cedula, String nombre, String telefono, String direccion, String correo) {
        Propietario nuevo = new Propietario(cedula, nombre, telefono, direccion, correo);
        try {
            dao.guardar(nuevo.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar propietario: " + e.getMessage());
            return false;
        }
    }
}