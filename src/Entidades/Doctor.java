package Entidades;

import java.io.Serializable;

public class Doctor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int Cedula;
    private String Especialidad;

    public String getEspecialidad() {
        return Especialidad;
    }
    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }
    public int getCedula() {
        return Cedula;
    }
    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public Doctor(int id,String ge,String N, String AM, String AP, String D, String T, String E, int Ed,String Fn,int C,String Es) {
        super(id,ge,N, AM, AP, D, T, E,Ed,Fn);
        this.Cedula = C;
        this.Especialidad = Es;
    }


}