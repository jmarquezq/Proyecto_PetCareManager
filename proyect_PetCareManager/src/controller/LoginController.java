package controller;

import java.util.List;

import model.Usuario;
import util.GenericDAO;

public class LoginController {

    private GenericDAO<Usuario> dao =
            new GenericDAO<>("src/doc/usuarios.txt");

    public boolean validarLogin(String username, String password) {

        try {

            List<String[]> lista = dao.cargarTodo();

            for(String[] datos : lista) {

                Usuario u = new Usuario(datos);

                if(u.getUsername().equals(username)
                        && u.getPassword().equals(password)) {

                    return true;
                }
            }

        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }
}
