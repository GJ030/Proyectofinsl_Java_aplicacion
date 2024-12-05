package Ventanas;

import Entidades.Doctor;
import Entidades.Paciente;
import Metodos.CRUDGeneral;
import Metodos.CrudDoctores;
import Metodos.CrudPacientes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class VentanaCitas extends JFrame {

    JPanel miPanel;
    private JComboBox cmbPaciente;
    private JComboBox cmbDoctor;
    private JTextArea txtObservaciones;
    private JButton confirmarCitaButton;
    private JButton cancelarButton;
    private JTextField textPaciente;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAnio;
    private JTextField textDoctor;

    public VentanaCitas() {
        String rutaPacientes = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt";
        List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);

        for (Paciente a : listaPacientes) {
            cmbPaciente.addItem(a.getID());
        }

        String rutaDoctores = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt";
        List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
        cmbDoctor.removeAllItems();
        for (Doctor doctor : listaDoctores) {
            cmbDoctor.addItem(doctor.getID());
        }

        cmbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrudPacientes crud = new CrudPacientes();
                String id = cmbPaciente.getSelectedItem().toString();
                String nombreCompleto;
                Paciente a = crud.ObtenerPacientePorId(id);
                nombreCompleto = a.getNombre() + " " + a.getApellidoPaterno() + " " + a.getApellidoMaterno();
                textPaciente.setText(nombreCompleto);
            }
        });

        confirmarCitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = cmbDia.getSelectedItem().toString() + "/" + cmbMes.getSelectedItem().toString() + "/" + cmbAnio.getSelectedItem().toString();
                boolean resultado = CRUDGeneral.validarFecha(fecha);
                if (resultado) {
                    String paciente = textPaciente.getText();
                    String doctor = textDoctor.getText();
                    String observaciones = txtObservaciones.getText();

                    CitaArchivo(paciente, doctor, fecha, observaciones);
                    JOptionPane.showMessageDialog(miPanel, "Su cita ha sido agendada para la fecha de: " + fecha);
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Error en el formato de la fecha");
                }
            }
        });

        cmbDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrudDoctores crud = new CrudDoctores();
                String id = cmbDoctor.getSelectedItem().toString();
                String nombreCompleto;
                Doctor a = crud.ObtenerDoctorPorId(id);
                nombreCompleto = a.getNombre() + " " + a.getApellidoPaterno() + " " + a.getApellidoMaterno();
                textDoctor.setText(nombreCompleto);
            }
        });
    }

    private void CitaArchivo(String paciente, String doctor, String fecha, String observaciones) {
        String rutaArchivo = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Citas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write("Paciente: " + paciente);
            writer.newLine();
            writer.write("Doctor: " + doctor);
            writer.newLine();
            writer.write("Fecha: " + fecha);
            writer.newLine();
            writer.write("Observaciones: " + observaciones);
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(miPanel, "Error al guardar la cita en el archivo: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        VentanaCitas t = new VentanaCitas();
        t.setContentPane(t.miPanel);
        t.setSize(500, 500);
        t.setTitle("Citas");
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
        t.setLocationRelativeTo(null);

        /*try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\gaeli\\Desktop\\BasesDatos\\Citas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo: " + ex.getMessage());
        }*/

    }
}
