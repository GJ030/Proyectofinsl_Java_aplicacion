package Ventanas;

import Entidades.Doctor;
import Metodos.CRUDGeneral;
import Metodos.CrudDoctores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import static Metodos.CRUDGeneral.validarFecha;

public class VentanaDoctores extends JFrame {
    public JPanel miPanel;
    private JComboBox cmbId;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JTextField txtGenero;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtEdad;
    private JTextField txtFechaNa;
    private JTextField txtCedula;
    private JTextField txtEspecialidad;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton limpiarButton;

    public VentanaDoctores() {
        String rutaDoctores = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Doctores.txt";
        List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
        cmbId.removeAllItems();
        for (Doctor doctor : listaDoctores) {
            cmbId.addItem(doctor.getID());
        }

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrudDoctores crud = new CrudDoctores();
                String id = Objects.requireNonNull(cmbId.getSelectedItem()).toString();
                Doctor doctor = crud.ObtenerDoctorPorId(id);
                if (doctor == null) {
                    int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra el doctor con el ID: " + id + ". ¿Desea ingresarlo?", "Doctores", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        agregarButton.setEnabled(true);
                        txtNombre.requestFocus();
                    }
                } else {
                    txtNombre.setText(doctor.getNombre());
                    txtApellidoPaterno.setText(doctor.getApellidoPaterno());
                    txtApellidoMaterno.setText(doctor.getApellidoMaterno());
                    txtGenero.setText(doctor.getGenero());
                    txtDireccion.setText(doctor.getDireccion());
                    txtTelefono.setText(doctor.getTelefono());
                    txtEmail.setText(doctor.getEmail());
                    txtEdad.setText(String.valueOf(doctor.getEdad()));
                    txtFechaNa.setText(doctor.getFechaNacimiento());
                    txtCedula.setText(String.valueOf(doctor.getCedula()));
                    txtEspecialidad.setText(doctor.getEspecialidad());
                    eliminarButton.setEnabled(true);
                    modificarButton.setEnabled(true);
                    limpiarButton.setEnabled(true);
                }
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (!CRUDGeneral.validarDoctor(cmbId, txtNombre, txtApellidoPaterno, txtApellidoMaterno, txtGenero,
                            txtDireccion, txtTelefono, txtEmail, txtEdad, txtFechaNa, txtCedula, txtEspecialidad)) {
                        return;
                    }

                    String fechaNacimiento = txtFechaNa.getText();
                    if (!validarFecha(fechaNacimiento)) {
                        JOptionPane.showMessageDialog(null, "La fecha ingresada no es válida. Use el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int id = Integer.parseInt(Objects.requireNonNull(cmbId.getSelectedItem()).toString());
                    String genero = txtGenero.getText();
                    String nombre = txtNombre.getText();
                    String apellidoMaterno = txtApellidoMaterno.getText();
                    String apellidoPaterno = txtApellidoPaterno.getText();
                    String direccion = txtDireccion.getText();
                    String telefono = txtTelefono.getText();
                    String email = txtEmail.getText();
                    int edad = Integer.parseInt(txtEdad.getText());
                    int cedula = Integer.parseInt(txtCedula.getText());
                    String especialidad = txtEspecialidad.getText();

                    Doctor doctor = new Doctor(id, genero, nombre, apellidoMaterno, apellidoPaterno, direccion, telefono, email, edad, fechaNacimiento, cedula, especialidad);

                    CrudDoctores crud = new CrudDoctores();
                    crud.AgregarDoctor(doctor);

                    cmbId.removeAllItems();
                    List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
                    for (Doctor doc : listaDoctores) {
                        cmbId.addItem(doc.getID());
                    }

                    JOptionPane.showMessageDialog(null, "Doctor agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrudDoctores crud = new CrudDoctores();
                String idDoctor = cmbId.getSelectedItem().toString();

                if (idDoctor == null || idDoctor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un ID válido.");
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar al doctor con ID " + idDoctor + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    crud.EliminarDoctor(idDoctor);
                    JOptionPane.showMessageDialog(null, "Doctor con ID " + idDoctor + " eliminado correctamente.");
                    cmbId.removeAllItems();
                    List<Doctor> listaDoctores = CRUDGeneral.Archivo.leerArchivo(rutaDoctores);
                    for (Doctor doc : listaDoctores) {
                        cmbId.addItem(doc.getID());
                    }
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText("");
                txtApellidoPaterno.setText("");
                txtApellidoMaterno.setText("");
                txtGenero.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtEmail.setText("");
                txtEdad.setText("");
                txtFechaNa.setText("");
                txtCedula.setText("");
                txtEspecialidad.setText("");
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!CRUDGeneral.validarDoctor(cmbId, txtNombre, txtApellidoPaterno, txtApellidoMaterno, txtGenero,
                        txtDireccion, txtTelefono, txtEmail, txtEdad, txtFechaNa, txtCedula, txtEspecialidad)) {
                    return;
                }

                try {
                    int id = Integer.parseInt(Objects.requireNonNull(cmbId.getSelectedItem()).toString());
                    String genero = txtGenero.getText();
                    String nombre = txtNombre.getText();
                    String apellidoPaterno = txtApellidoPaterno.getText();
                    String apellidoMaterno = txtApellidoMaterno.getText();
                    String direccion = txtDireccion.getText();
                    String telefono = txtTelefono.getText();
                    String email = txtEmail.getText();
                    int edad = Integer.parseInt(txtEdad.getText());
                    String fechaNacimiento = txtFechaNa.getText();
                    int cedula = Integer.parseInt(txtCedula.getText());
                    String especialidad = txtEspecialidad.getText();

                    Doctor doctorActualizado = new Doctor(id, genero, nombre, apellidoMaterno, apellidoPaterno, direccion, telefono, email, edad, fechaNacimiento, cedula, especialidad);

                    CrudDoctores crud = new CrudDoctores();
                    crud.ActualizarDoctor(doctorActualizado);

                    JOptionPane.showMessageDialog(null, "Doctor actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public static void main(String[] args) {
        VentanaDoctores ventanaDoctores = new VentanaDoctores();
        ventanaDoctores.setContentPane(ventanaDoctores.miPanel);
        ventanaDoctores.setSize(600, 600);
        ventanaDoctores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaDoctores.setVisible(true);
        ventanaDoctores.setLocationRelativeTo(null);
    }
}
