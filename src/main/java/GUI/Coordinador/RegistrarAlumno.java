package GUI.Coordinador;

import Servicio.EstudianteServicio;
import models.Estudiante;

import javax.swing.*;

import DAO.Estudiante.Utils;
import DBConeccion.SQLConeccion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarAlumno extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField matriculaField;
    private JLabel messageLabel;

    private EstudianteServicio estudianteServicio;

    public RegistrarAlumno() {
        setTitle("Gestión de Estudiantes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        initComponents();
        estudianteServicio = new EstudianteServicio();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        matriculaField = new JTextField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton registrarButton = new JButton("Registrar Estudiante");
        registrarButton.addActionListener(new RegisterAction());

        panel.add(new JLabel("Correo:", SwingConstants.CENTER));
        panel.add(emailField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(passwordField);
        panel.add(new JLabel("Matrícula:", SwingConstants.CENTER));
        panel.add(matriculaField);

        add(panel, BorderLayout.CENTER);
        add(registrarButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Estudiante estudiante = new Estudiante();
            Utils utils = new Utils();
            utils.asignarRegistroEstudiante(estudiante, emailField.getText(), new String(passwordField.getPassword()), matriculaField.getText());


            // Llamar a la función para asignar registro y registrar al estudiante
            try {
                SQLConeccion.initializeConnection();
                estudiante.validate();
                estudianteServicio.registrarEstudiante(estudiante, estudiante.getEmail(), estudiante.getContrasena(), estudiante.getMatricula());
                messageLabel.setText("Estudiante registrado exitosamente");
                JOptionPane.showMessageDialog(RegistrarAlumno.this, "Estudiante " + estudiante.getEmail() + " registrado.");
            } catch (Exception ex) {
                messageLabel.setText("Error al registrar el estudiante");
                JOptionPane.showMessageDialog(RegistrarAlumno.this, "Error: " + ex.getMessage());
            }
        }
    }
}
