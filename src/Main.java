import Entidades.Doctor;
import Entidades.Paciente;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Main {
    public static void main(String[] args) {

        try {
            ArrayList<Paciente> ListaPacientes = new ArrayList<>();
            Paciente p1 = new Paciente(1001,"Masculino","Gael","Jurado","Romero","Granados","5546356226","Gael.isaac@outlook.com",20,"28/06/2004","Si");
            Paciente p2 = new Paciente(1002,"Femenino","Valentina","Orduña","Vieytez","Buentono","5511953956","Valentina.Vieytez@outloo.com",21,"01/01/2003","No");
            Paciente p3 = new Paciente(1003,"Femenino","Paola","Flores","Guerrero","Cali","5512316742","Pao.flores@outlook.com",20,"07/07/2004","Si");

            ListaPacientes.add(p1);
            ListaPacientes.add(p2);
            ListaPacientes.add(p3);

            FileOutputStream escribir = new FileOutputStream("C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(ListaPacientes);
            miStream.flush();
            miStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {

            ArrayList<Doctor> ListaDoctores = new ArrayList<>();
            Doctor d1 = new Doctor(2001, "Masculino", "Carlos", "López", "Hernández", "Puebla", "5523456789", "carlos.lopez@hospital.com", 40, "01/01/1984", 12345, "Cardiología");
            Doctor d2 = new Doctor(2002, "Femenino", "Ana", "Martínez", "Gómez", "CDMX", "5519876543", "ana.martinez@hospital.com", 35, "12/12/1987", 67890, "Pediatría");
            Doctor d3 = new Doctor(2003, "Masculino", "Luis", "Fernández", "Torres", "Monterrey", "5587654321", "luis.fernandez@hospital.com", 50, "15/08/1973", 11223, "Neurología");

            ListaDoctores.add(d1);
            ListaDoctores.add(d2);
            ListaDoctores.add(d3);


            FileOutputStream escribir = new FileOutputStream("C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(ListaDoctores);
            miStream.flush();
            miStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}