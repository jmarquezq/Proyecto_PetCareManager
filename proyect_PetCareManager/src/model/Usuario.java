package model;

import Libreria_generica.generic;

public class Usuario {
  
    private generic dt_u;
    public Usuario() {
        this.dt_u = new generic();
    }

    public Usuario(String user, String pws) {
        this.dt_u = new generic(user, pws);
    }

    public Usuario(String... datos) {
        this.dt_u = new generic(datos[0], datos[1]);
    }

    public String getUsername() {
        return (String) dt_u.getAttributeT1(); 
    }

    public String getPassword() {
        return (String) dt_u.getAttributeT2();
    }

    public void setUsername(String user) {
        this.dt_u.setAttributeT1(user);
    }

    public void setPassword(String psw) {
        this.dt_u.setAttributeT2(psw);
    }

    public String info() {
        return getUsername() + "," + getPassword();
    }
}