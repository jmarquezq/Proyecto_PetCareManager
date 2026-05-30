package controller;

import util.GenericDAO;
import model.Mascota;
import java.io.IOException;
import java.util.List;

public class MascotaController {
    private GenericDAO<Mascota> dao = new GenericDAO<>("src/doc/mascota.txt");

    public void registrarMascota(String nombre, String edad, String especie) {
        Mascota nueva = new Mascota(nombre, edad, especie);
        try {
            dao.guardar(nueva.info()); 
            System.out.println("¡Mascota guardada con éxito!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public Mascota buscarMascota(String nombreBuscado) {
        try {
            List<String[]> todas = dao.cargarTodo();
            for (String[] datos : todas) {
                if (datos[0].equals(nombreBuscado.trim())) {
                    return new Mascota(datos);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null; 
    }

    public boolean eliminarMascota(String nombreEliminar) {
        try {
            return dao.eliminarPorID(nombreEliminar, 0);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}
