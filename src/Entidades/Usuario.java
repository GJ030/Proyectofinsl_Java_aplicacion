package Entidades;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Nombre,Genero,ApellidoMaterno,ApellidoPaterno,Direccion,Telefono,Email;
    private int Edad,ID;
    private String FechaNacimiento;
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }
    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }
    public int getEdad() {
        return Edad;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setEdad(int edad) {
        Edad = edad;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public String getDireccion() {
        return Direccion;
    }
    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
    public String getFechaNacimiento() {
        return FechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.FechaNacimiento = fechaNacimiento;
    }
    public String getGenero() {
        return Genero;
    }
    public void setGenero(String genero) {
        this.Genero = genero;
    }

    public Usuario(int id,String ge,String N, String AM, String AP, String D, String T, String E, int Ed,String Fn){
        this.ID =id;
        this.Genero = ge;
        this.Nombre = N;
        this.ApellidoMaterno = AM;
        this.ApellidoPaterno = AP;
        this.Direccion = D;
        this.Telefono = T;
        this.Email = E;
        this.Edad = Ed;
        this.FechaNacimiento = Fn;
    }


}
