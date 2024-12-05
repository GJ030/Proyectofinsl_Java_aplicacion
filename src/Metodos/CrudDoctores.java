package Metodos;
import Entidades.Doctor;
import java.io.*;
import java.util.List;

public class CrudDoctores {
    public void AgregarDoctor(Doctor d) {
        try {
            String rutaDoctores = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt";
            List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
            listaDoctores.add(d);
            FileOutputStream escribir =
                    new FileOutputStream(rutaDoctores);
            ObjectOutputStream escritorObjetos = new ObjectOutputStream(escribir);
            escritorObjetos.writeObject(listaDoctores);
            escritorObjetos.flush();
            escritorObjetos.close();
            escribir.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Doctor ObtenerDoctorPorId(String ID) {
        String rutaDoctores = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt";
        List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
        Doctor d;
        for (int i = 0; i < listaDoctores.size(); i++) {
            d = listaDoctores.get(i);
            if ((Integer.parseInt(ID) == d.getID())) {
                return d;
            }
        }
        return null;
    }

    public void ActualizarDoctor(Doctor doctorActualizado) {
        try {
            String rutaDoctores = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt";
            List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
            boolean encontrado = false;
            for (int i = 0; i < listaDoctores.size(); i++) {
                if (listaDoctores.get(i).getID() == doctorActualizado.getID()) {
                    listaDoctores.set(i, doctorActualizado);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Doctor con ID " + doctorActualizado.getID() + " no encontrado.");
                return;
            }
            CRUDGeneral.Archivo.escribirArchivo(rutaDoctores, listaDoctores);
            System.out.println("Doctor con ID " + doctorActualizado.getID() + " actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar actualizar el doctor: " + e.getMessage());
        }
    }
    public void EliminarDoctor(String ID) {
        try {
            String rutaDoctores = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt";
            List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
            boolean encontrado = false;
            for (int i = 0; i < listaDoctores.size(); i++) {
                if (listaDoctores.get(i).getID() == Integer.parseInt(ID)) {
                    listaDoctores.remove(i);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Doctor con ID " + ID + " no encontrado.");
                return;
            }
            CRUDGeneral.Archivo.escribirArchivo(rutaDoctores, listaDoctores);

        } catch (NumberFormatException e) {
            System.out.println("Error: El ID proporcionado no es un número válido.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar eliminar el doctor: " + e.getMessage());
        }
    }
}
