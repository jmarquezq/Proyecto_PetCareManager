package controller;

import util.GenericDAO; 
import model.Atencion;
import model.Cita;
import model.Mascota;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class AtencionController {

    private GenericDAO<Atencion> dao = new GenericDAO<>("src/doc/atenciones.txt");
    private GenericDAO<Cita> citaDAO = new GenericDAO<>("src/doc/citas.txt");
    private GenericDAO<Mascota> mascotaDAO = new GenericDAO<>("src/doc/mascota.txt");

    private static final String REGEX_PESO = "^\\d{1,3}(\\.\\d{1,2})?$";
    private static final String REGEX_TEMP = "^\\d{2}(\\.\\d{1})?$";
    private static final String REGEX_TEXTO = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s.,:;-]{5,200}$";

    public String[] buscarDatosCita(String idCita) {
        try {
            List<String[]> citas = citaDAO.cargarTodo();
            for (String[] c : citas) {
                if (c[0].equalsIgnoreCase(idCita)) {
                    return c;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar citas: " + e.getMessage());
        }
        return null; 
    }

    public String buscarNombreMascota(String idMascota) {
        try {
            List<String[]> mascotas = mascotaDAO.cargarTodo();
            for (String[] m : mascotas) {
                if (m[0].equalsIgnoreCase(idMascota)) {
                    return m[1];
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar mascotas: " + e.getMessage());
        }
        return "Desconocido";
    }

    public String[] buscarMascotaCompleta(String termino) {
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

    public String obtenerHistorialClinico(String idMascota) {
        StringBuilder sb = new StringBuilder();
        boolean tieneAtenciones = false;

        try {
            List<String[]> atenciones = dao.cargarTodo();
            for (String[] a : atenciones) {
                if (a.length >= 9 && a[2].equalsIgnoreCase(idMascota)) {
                    sb.append("--------------------------------\n");
                    sb.append("Atención: ").append(a[0]).append("\n"); 
                    sb.append("Fecha: ").append(a[3]).append("\n\n"); 
                    
                    sb.append("Diagnóstico:\n");
                    sb.append(a[6]).append("\n\n"); 
                    
                    sb.append("Tratamiento:\n");
                    sb.append(a[7]).append("\n"); 
                    
                    tieneAtenciones = true;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al leer archivo de atenciones: " + ex.getMessage());
            return "Error al cargar las consultas anteriores.";
        }

        if (tieneAtenciones) {
            sb.append("--------------------------------\n");
        } else {
            sb.append("--------------------------------\n");
            sb.append("No hay atenciones médicas registradas para este paciente.\n");
            sb.append("--------------------------------\n");
        }

        return sb.toString();
    }

    public String validarFormatos(String peso, String temp, String diag, String trat, String obs) {
        if (peso.isEmpty() || temp.isEmpty() || diag.isEmpty() || trat.isEmpty() || obs.isEmpty()) {
            return "Todos los campos médicos son obligatorios.";
        }
        
        if (!Pattern.matches(REGEX_PESO, peso)) return "El peso debe ser numérico (ej. 12.5).";
        if (!Pattern.matches(REGEX_TEMP, temp)) return "La temperatura debe ser numérica de dos dígitos (ej. 38.5).";
        if (!Pattern.matches(REGEX_TEXTO, diag)) return "El diagnóstico debe tener al menos 5 caracteres válidos.";
        if (!Pattern.matches(REGEX_TEXTO, trat)) return "El tratamiento debe tener al menos 5 caracteres válidos.";
        if (!Pattern.matches(REGEX_TEXTO, obs)) return "La observación debe tener al menos 5 caracteres válidos.";
        
        return "OK";
    }

    public boolean registrarAtencion(String idCita, String idMascota, String fecha, String peso, String temp, String diag, String trat, String obs) {
        int numAleatorio = (int)(Math.random() * 9000) + 1000;
        String idAtencion = "ATE-" + numAleatorio;

        Atencion nueva = new Atencion(idAtencion, idCita, idMascota, fecha, Double.parseDouble(peso), temp, diag, trat, obs);

        try {
            dao.guardar(nueva.info());
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar la atención: " + e.getMessage());
            return false;
        }
    }
}