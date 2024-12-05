package GUI.Coordinador;

import Servicio.EstudianteServicio;

import javax.swing.*;

import DBConeccion.SQLConeccion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsignarProyecto extends JFrame {

    private JTextField emailField;
    private JTextField proyectoIdField;
    private JLabel messageLabel;

    private EstudianteServicio estudianteServicio;

    public AsignarProyecto() {
        setTitle("Asignar Proyecto a Estudiante");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        initComponents();
        estudianteServicio = new EstudianteServicio();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        emailField = new JTextField();
        proyectoIdField = new JTextField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton asignarButton = new JButton("Asignar Proyecto");
        asignarButton.addActionListener(new AsignarAction());

        panel.add(new JLabel("Correo del Estudiante:", SwingConstants.CENTER));
        panel.add(emailField);
        panel.add(new JLabel("ID del Proyecto:", SwingConstants.CENTER));
        panel.add(proyectoIdField);

        add(panel, BorderLayout.CENTER);
        add(asignarButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    private class AsignarAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String proyectoIdStr = proyectoIdField.getText();
            SQLConeccion.tryConnection();
            try {
                // Validar que el proyectoId sea un número entero
                int proyectoId = Integer.parseInt(proyectoIdStr);
                // Llamar al servicio para asignar el proyecto al estudiante
                boolean result = estudianteServicio.asignarProyectoAEstudiante(email, proyectoId);
                
                if (result) {
                    messageLabel.setText("Proyecto asignado exitosamente.");
                    JOptionPane.showMessageDialog(AsignarProyecto.this, "Proyecto asignado a " + email);
                } else {
                    messageLabel.setText("Error al asignar el proyecto.");
                    JOptionPane.showMessageDialog(AsignarProyecto.this, "Error: No se pudo asignar el proyecto.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: El ID del proyecto debe ser un número.");
                JOptionPane.showMessageDialog(AsignarProyecto.this, "Error: El ID del proyecto debe ser un número.");
            }
        }
    }
}
