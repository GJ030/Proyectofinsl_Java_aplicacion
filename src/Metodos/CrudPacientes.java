package Metodos;
import Entidades.Paciente;
import java.io.*;
import java.util.List;

public class CrudPacientes {
    public void AgregarPaciente(Paciente a) {
            try{
                String rutaPacientes ="C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt" ;
                List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);

                //2) Agregar alumno a la lista
                listaPacientes.add(a);

                //3) Escribir lista al archivo
                FileOutputStream escribir =
                        new FileOutputStream("C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt");
                ObjectOutputStream escritorObjetos = new ObjectOutputStream(escribir);
                escritorObjetos.writeObject(listaPacientes);
                escritorObjetos.flush();
                escritorObjetos.close();
                escribir.close();
            }catch(FileNotFoundException e){
                System.out.println("Error al leer el archivo");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public Paciente ObtenerPacientePorId(String ID) {
        String rutaPacientes ="C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt" ;
        List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);
        Paciente k;
        for(int i=0; i<listaPacientes.size();i++)
        {
            k = listaPacientes.get(i);
            if((Integer.parseInt(ID) == k.getID()))
            {
                return k;
            }
        }
        return null;
    }

    public void ActualizarPaciente() {
        //En desarollo
    }
    public void EliminarPaciente(String ID) {
        try {
            String rutaPacientes = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt";
            List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);

            boolean encontrado = false;
            for (int i = 0; i < listaPacientes.size(); i++) {
                if (listaPacientes.get(i).getID() == Integer.parseInt(ID)) {
                    listaPacientes.remove(i);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Paciente con ID " + ID + " no encontrado.");
                return;
            }
            CRUDGeneral.Archivo.escribirArchivo(rutaPacientes, listaPacientes);

            System.out.println("Paciente con ID " + ID + " eliminado correctamente.");

        } catch (NumberFormatException e) {
            System.out.println("Error: El ID proporcionado no es un número válido.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar eliminar el paciente: " + e.getMessage());
        }
    }
}
