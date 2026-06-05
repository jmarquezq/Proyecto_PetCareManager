package controller;

import java.util.List;
import java.util.regex.Pattern;
import model.Usuario;
import util.GenericDAO; 

public class LoginController {

    private GenericDAO<Usuario> dao = new GenericDAO<>("src/doc/usuarios.txt");

    private static final String REGEX_USUARIO = "^[a-zA-Z0-9]{4,15}$";
    private static final String REGEX_CLAVE = "^\\S{6,}$";

    public boolean validarFormato(String username, String password) {
        boolean userValido = Pattern.matches(REGEX_USUARIO, username);
        boolean passValida = Pattern.matches(REGEX_CLAVE, password);
        return userValido && passValida;
    }

    public boolean validarLogin(String username, String password) {
        try {
            List<String[]> lista = dao.cargarTodo();
            
            if (lista != null) {
                for (String[] datos : lista) {
                    if (datos.length >= 2) { 
                        Usuario u = new Usuario(datos);
                        if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                            return true; 
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error de lectura en base de datos: " + e.getMessage());
        }
        return false;
    }
}