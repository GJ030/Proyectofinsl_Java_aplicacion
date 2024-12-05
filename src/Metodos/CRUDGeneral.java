package Metodos;
import Entidades.Usuario;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CRUDGeneral {
    public static class Archivo {

        public static <T extends Usuario> List<T> leerArchivo(String rutaArchivo) {
            List<T> lista = new ArrayList<>();
            try (ObjectInputStream lectorObjetos = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
                lista = (List<T>) lectorObjetos.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado: " + rutaArchivo);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo leer el archivo");
            }
            return lista;
        }

        public static <T extends Usuario> void escribirArchivo(String rutaArchivo, List<T> lista) {
            try (ObjectOutputStream escritorObjetos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
                escritorObjetos.writeObject(lista);
                escritorObjetos.flush();
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado: " + rutaArchivo);
            } catch (IOException e) {
                System.out.println("No se pudo escribir en el archivo.");
            }
        }
    }


    public static boolean validarFecha(String fecha) {
        SimpleDateFormat formatoFecha =
                new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean validarDoctor(JComboBox cmbId, JTextField txtNombre, JTextField txtApellidoPaterno, JTextField txtApellidoMaterno, JTextField txtGenero, JTextField txtDireccion, JTextField txtTelefono, JTextField txtEmail, JTextField txtEdad, JTextField txtFechaNa, JTextField txtCedula, JTextField txtEspecialidad) {
        if (cmbId.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNombre.getText().isEmpty() ||
                txtApellidoPaterno.getText().isEmpty() ||
                txtApellidoMaterno.getText().isEmpty() ||
                txtGenero.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                txtEdad.getText().isEmpty() ||
                txtFechaNa.getText().isEmpty() ||
                txtCedula.getText().isEmpty() ||
                txtEspecialidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!validarFecha(txtFechaNa.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha ingresada no es válida. Use el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validarPaciente(JComboBox cmbId, JTextField txtNombre, JTextField txtApellidoPaterno,
                                                JTextField txtApellidoMaterno, JTextField txtGenero, JTextField txtDireccion,
                                                JTextField txtTelefono, JTextField txtEmail, JTextField txtEdad,
                                                JTextField txtFechaNacimiento, JTextField txtHistorialMedico) {

        if (cmbId.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNombre.getText().isEmpty() ||
                txtApellidoPaterno.getText().isEmpty() ||
                txtApellidoMaterno.getText().isEmpty() ||
                txtGenero.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                txtEdad.getText().isEmpty() ||
                txtFechaNacimiento.getText().isEmpty() ||
                txtHistorialMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!CRUDGeneral.validarFecha(txtFechaNacimiento.getText())) {
            JOptionPane.showMessageDialog(null, "La fecha ingresada no es válida. Use el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}

