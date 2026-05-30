package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> {
    private String rutaArchivo;
    
    public GenericDAO(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        verificarArchivo();
    }

    private void verificarArchivo() {
        try {
            File file = new File(rutaArchivo);
            File carpeta = file.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public boolean guardar(String lineaInfo) throws IOException {
        FileWriter out = new FileWriter(rutaArchivo, true);
        out.write(lineaInfo + "\n"); 
        out.close();
        return true;
    }

    public boolean eliminarPorID(String idEliminar, int posicionID) throws IOException {
        File archivoOriginal = new File(rutaArchivo);
        File archivoTemporal = new File(archivoOriginal.getParent(), "temp_" + archivoOriginal.getName());
        
        FileReader fr = new FileReader(archivoOriginal);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(archivoTemporal);
        BufferedWriter bw = new BufferedWriter(fw);

        String linea;
        boolean encontrado = false;
        
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length > posicionID) {
                String idArchivo = datos[posicionID].trim();
                if (idArchivo.equals(idEliminar.trim())) {
                    encontrado = true; 
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        }
        br.close();
        fr.close();
        bw.close();
        fw.close();

        if (encontrado) {
            archivoOriginal.delete();
            archivoTemporal.renameTo(archivoOriginal);
        } else {
            archivoTemporal.delete(); 
        }

        return encontrado;
    }

    public List<String[]> cargarTodo() throws IOException {
        List<String[]> listaDatos = new ArrayList<>();
        FileReader fr = new FileReader(rutaArchivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        
        while ((linea = br.readLine()) != null) {
            if (!linea.trim().isEmpty()) {
                String[] datos = linea.split(",");

                for (int i = 0; i < datos.length; i++) {
                    datos[i] = datos[i].trim();
                }
                listaDatos.add(datos);
            }
        }
        br.close();
        fr.close();
        return listaDatos;
    }
}