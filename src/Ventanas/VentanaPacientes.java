package Ventanas;

import Entidades.Paciente;
import Metodos.CRUDGeneral;
import Metodos.CrudPacientes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import static Metodos.CRUDGeneral.validarFecha;

public class VentanaPacientes extends JFrame {
        private JPanel miPanel;
        private JTextField txtNombre;
        private JTextField txtApellidoPaterno;
        private JTextField txtDireccion;
        private JTextField txtTelefono;
        private JTextField txtEmail;
        private JTextField txtEdad;
        private JTextField txtHistorialMedico;
        private JTextField txtApellidoMaterno;
        private JButton buscarAlumnoButton;
        private JButton ModificarAlumnoButton;
        private JButton EliminarAlumnoButton;
        private JButton AgregarAlumnoButton;
        private JTextField txtGenero;
        private JComboBox cmbId;
        private JButton BttnLimpiar;
    private JButton bttnDoctores;
        private JTextField txtFechaNacimiento;
    private JButton bttnPacientes;
    private JButton bttnCitas;


    public VentanaPacientes() {
            String rutaPacientes = "C:\\Users\\gaeli\\Desktop\\BasesDatos\\Pacientes.txt";
            List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);
            cmbId.removeAllItems();
            for (Paciente a : listaPacientes) {
                cmbId.addItem(a.getID());
            }

            buscarAlumnoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CrudPacientes crud = new CrudPacientes();
                    String id = Objects.requireNonNull(cmbId.getSelectedItem()).toString();
                    Paciente k = crud.ObtenerPacientePorId(id);
                    if (k == null) {
                        int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra el paciente con el número de ID: " + id + ". ¿Desea ingresarlo?", "Pacientes", JOptionPane.YES_NO_OPTION);
                        if (respuesta == 0) {
                            AgregarAlumnoButton.setEnabled(true);
                            txtNombre.requestFocus();
                        }
                    } else {
                        txtNombre.setText(k.getNombre());
                        txtApellidoPaterno.setText(k.getApellidoPaterno());
                        txtApellidoMaterno.setText(k.getApellidoMaterno());
                        txtDireccion.setText(k.getDireccion());
                        txtTelefono.setText(k.getTelefono());
                        txtEmail.setText(k.getEmail());
                        txtEdad.setText(String.valueOf(k.getEdad()));
                        txtHistorialMedico.setText(k.getHistorialMedico());
                        txtGenero.setText(k.getGenero());
                        txtFechaNacimiento.setText(k.getFechaNacimiento());
                        EliminarAlumnoButton.setEnabled(true);
                        ModificarAlumnoButton.setEnabled(true);
                        BttnLimpiar.setEnabled(true);
                    }
                }
            });

            AgregarAlumnoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (!CRUDGeneral.validarPaciente(cmbId, txtNombre, txtApellidoPaterno, txtApellidoMaterno, txtGenero,
                                txtDireccion, txtTelefono, txtEmail, txtEdad, txtFechaNacimiento, txtHistorialMedico)) {
                            return;
                        }

                    String fechaNacimiento = txtFechaNacimiento.getText();
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
                        int edad;

                        try {
                            edad = Integer.parseInt(txtEdad.getText());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "La edad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String historialMedico = txtHistorialMedico.getText();

                        Paciente miPaciente = new Paciente(id, genero, nombre, apellidoMaterno, apellidoPaterno, direccion, telefono, email, edad, fechaNacimiento, historialMedico);

                        CrudPacientes crud = new CrudPacientes();
                        crud.AgregarPaciente(miPaciente);

                        cmbId.removeAllItems();
                        List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);
                        for (Paciente p : listaPacientes) {
                            cmbId.addItem(p.getID());
                        }

                        JOptionPane.showMessageDialog(null, "Paciente agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });





            EliminarAlumnoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CrudPacientes crudPacientes = new CrudPacientes();
                    String idPaciente = cmbId.getSelectedItem().toString();

                    if (idPaciente == null || idPaciente.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, selecciona un ID válido.");
                        return;
                    }
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el paciente con ID " + idPaciente + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        crudPacientes.EliminarPaciente(idPaciente);
                        JOptionPane.showMessageDialog(null, "Paciente con ID " + idPaciente + " eliminado correctamente.");
                        cmbId.removeAllItems();
                        List<Paciente> listaPacientes = CRUDGeneral.Archivo.leerArchivo(rutaPacientes);
                        for (Paciente p : listaPacientes) {
                            cmbId.addItem(p.getID());
                        }
                    }
                }
            });

            BttnLimpiar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtNombre.setText("");
                    txtApellidoPaterno.setText("");
                    txtApellidoMaterno.setText("");
                    txtDireccion.setText("");
                    txtTelefono.setText("");
                    txtEmail.setText("");
                    txtEdad.setText("");
                    txtHistorialMedico.setText("");
                    txtGenero.setText("");
                    txtFechaNacimiento.setText("");
                }
            });
            bttnPacientes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    VentanaPacientes ventanaPacientes = new VentanaPacientes();
                    ventanaPacientes.setContentPane(ventanaPacientes.miPanel);
                    ventanaPacientes.setSize(600, 600);
                    ventanaPacientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ventanaPacientes.setVisible(true);
                    ventanaPacientes.setLocationRelativeTo(null);
            }
        });

        bttnDoctores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaDoctores d = new VentanaDoctores();
                d.setContentPane(d.miPanel);
                d.setSize(600, 600);
                d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                d.setVisible(true);
                d.setLocationRelativeTo(null);
            }
        });

        bttnCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCitas ventanaCitas = new VentanaCitas();
                ventanaCitas.setContentPane(ventanaCitas.miPanel);
                ventanaCitas.setSize(600, 600);
                ventanaCitas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaCitas.setVisible(true);
                ventanaCitas.setLocationRelativeTo(null);
            }
        });

        ModificarAlumnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(miPanel,"En desarollo");
            }
        });
    }


        public static void main(String[] args) {
            VentanaPacientes v = new VentanaPacientes();
            v.setContentPane(v.miPanel);
            v.setSize(600, 600);
            v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            v.setVisible(true);
            v.setLocationRelativeTo(null);
        }
    }
