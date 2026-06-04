package controller;

import java.util.List;
import java.util.regex.Pattern;
import model.Usuario;
import Libreria_generica.GenericDAO;

public class LoginController {

    private GenericDAO<Usuario> dao = new GenericDAO<>("src/doc/usuarios.txt");

    // REGLAS DE EXPRESIONES REGULARES
    // ^[a-zA-Z0-9]{4,15}$ -> Solo letras y números, entre 4 y 15 caracteres.
    private static final String REGEX_USUARIO = "^[a-zA-Z0-9]{4,15}$";
    
    // ^\\S{6,}$ -> Cualquier caracter que no sea un espacio, mínimo 6 caracteres.
    private static final String REGEX_CLAVE = "^\\S{6,}$";

    // Método para validar las expresiones regulares
    public boolean validarFormato(String username, String password) {
        boolean userValido = Pattern.matches(REGEX_USUARIO, username);
        boolean passValida = Pattern.matches(REGEX_CLAVE, password);
        return userValido && passValida;
    }

    public boolean validarLogin(String username, String password) {
        try {
            List<String[]> lista = dao.cargarTodo();
            
            if (lista != null) {
                for(String[] datos : lista) {
                    // Prevenir errores si hay líneas en blanco en el txt
                    if(datos.length >= 3) { 
                        Usuario u = new Usuario(datos);
                        if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                            return true; // Credenciales correctas
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return false;
    }
}
