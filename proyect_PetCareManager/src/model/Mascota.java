package model;

public class Mascota {
    private String idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private int edad; 
    private String idPropietario;

    public Mascota(String idMascota, String nombre, String especie, String raza, int edad, String idPropietario) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.idPropietario = idPropietario;
    }

    public String getIdMascota() { 
    	return idMascota; 
    }
    public void setIdMascota(String idMascota) { 
    	this.idMascota = idMascota; 
    }
    public String getNombre() { 
    	return nombre; 
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }
    public String getEspecie() { 
    	return especie; 
    }
    public void setEspecie(String especie) { 
    	this.especie = especie; 
    }
    public String getRaza() { 
    	return raza; 
    }
    public void setRaza(String raza) { 
    	this.raza = raza; 
    }
    public int getEdad() { 
    	return edad; 
    }
    public void setEdad(int edad) { 
    	this.edad = edad; 
    }
    public String getIdPropietario() { 
    	return idPropietario; 
    }
    public void setIdPropietario(String idPropietario) { 
    	this.idPropietario = idPropietario; 
    }
}
