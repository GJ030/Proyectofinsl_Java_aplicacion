package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JPanel miPanel;
    private JTextField txtUsuario;
    private JPasswordField txtpassword;
    private JButton bttnLogin;
    private JLabel icono;
    private JButton BttnAgregarNuevoUser;
    private JButton ButtonCancelar;

    public VentanaLogin() {
        setContentPane(miPanel);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon imagenIcono = new ImageIcon("C:\\Users\\gaeli\\Downloads\\loginbuenio.png");
        Image imagen = imagenIcono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        icono.setIcon(iconoEscalado);
        bttnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

             String password = String.valueOf(txtpassword.getPassword());
             if(txtUsuario.getText().equals("admin") && password.equals("1234")){
                 JOptionPane.showMessageDialog(miPanel,"Bienvenido Admin");
                 dispose();
                 String[] tipoUsuario={"admin"};
                 VentanaPacientes.main(tipoUsuario);
             }else if(txtUsuario.getText().equals("usuario") && password.equals("280604")){
                 JOptionPane.showMessageDialog(miPanel,"Bienvenido Paciente "+ txtUsuario.getText());
                 String[] tipoUsuario={"admin"};
                 VentanaPacientes.main(tipoUsuario);
             }else{
                 JOptionPane.showMessageDialog(miPanel,"Usuario o contraseña incorrectos","Login",JOptionPane.ERROR_MESSAGE);
             }
            }
        });
        ButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        BttnAgregarNuevoUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin v = new VentanaLogin();
            v.setVisible(true);
        });
    }
}

