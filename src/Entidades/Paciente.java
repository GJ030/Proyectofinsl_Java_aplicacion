package Entidades;

import java.io.Serializable;

public class Paciente extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String HistorialMedico;

    public String getHistorialMedico() {
        return HistorialMedico;
    }
    public void setHistorialMedico(String historialMedico) {
        HistorialMedico = historialMedico;
    }

    public Paciente (int id,String ge,String N, String AM, String AP, String D, String T, String E, int Ed,String Fn,String Hm) {
        super(id,ge,N, AM, AP, D, T, E,Ed,Fn);
        this.HistorialMedico = Hm;
    }

}
