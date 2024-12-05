import Entidades.Paciente;
import Metodos.CrudPacientes;

public class Pruebas {
    public static void main(String[] args) {
        CrudPacientes pacrud = new CrudPacientes();
        Paciente resultado = pacrud.ObtenerPacientePorId("1001");
        if (resultado == null) {
            System.out.println("No se encuentra esa matricula");
        } else {
            System.out.println(resultado.getNombre());
            System.out.println(resultado.getApellidoPaterno());
            System.out.println(resultado.getEdad());
        }
    }
}

