package controller;

import util.GenericDAO; 
import model.Mascota;
import model.Propietario;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class MascotaController {

    private GenericDAO<Mascota> dao = new GenericDAO<>("src/doc/mascota.txt");
    private GenericDAO<Propietario> propietarioDAO = new GenericDAO<>("src/doc/propietarios.txt");

    private static final String REGEX_ID = "^[A-Za-z0-9-]{3,10}$";
    private static final String REGEX_TEXTO_LETRAS = "^[A-Za-zñÑáéíóúÁÉÍÓÚ\\s]{2,50}$";
    private static final String REGEX_EDAD = "^\\d{1,2}$";

    public List<String[]> obtenerTodosLosPropietarios() {
        try {
            return propietarioDAO.cargarTodo();
        } catch (IOException e) {
            System.err.println("Error al cargar propietarios: " + e.getMessage());
            return null;
        }
    }

    public List<String[]> obtenerTodasLasMascotas() {
        try {
            return dao.cargarTodo();
        } catch (IOException e) {
            System.err.println("Error al cargar mascotas: " + e.getMessage());
            return null;
        }
    }

    public String validarFormatos(String id, String nombre, String especie, String raza, String edad) {
        if (id.isEmpty() || nombre.isEmpty() || especie.isEmpty() || raza.isEmpty() || edad.isEmpty()) {
            return "Debe completar todos los datos de la mascota.";
        }
        
        if (!Pattern.matches(REGEX_ID, id)) return "El ID debe ser alfanumérico y tener entre 3 y 10 caracteres (ej. M001).";
        if (!Pattern.matches(REGEX_TEXTO_LETRAS, nombre)) return "El nombre de la mascota solo debe contener letras.";
        if (!Pattern.matches(REGEX_TEXTO_LETRAS, especie)) return "La especie solo debe contener letras (ej. Perro, Gato).";
        if (!Pattern.matches(REGEX_TEXTO_LETRAS, raza)) return "La raza solo debe contener letras.";
        if (!Pattern.matches(REGEX_EDAD, edad)) return "La edad debe ser un número válido (máximo 2 dígitos).";
        
        return "OK";
    }

    public boolean registrarMascota(String id, String nombre, String especie, String raza, String edad, String sexo, String prop) {
        Mascota nueva = new Mascota(id, nombre, especie, raza, edad, sexo, prop);
        try {
            dao.guardar(nueva.info()); 
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar la mascota: " + e.getMessage());
            return false;
        }
    }
}