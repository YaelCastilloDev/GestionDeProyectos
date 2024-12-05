package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstudianteManager extends JFrame {
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JButton btnActualizar;

    public EstudianteManager() {
        setTitle("Gestión de Estudiantes");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        // Campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();
        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();

        // Botón de actualizar
        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateActualizarDatosPersonales();
            }
        });

        // Agregar componentes al JFrame
        add(lblNombre);
        add(txtNombre);
        add(lblEmail);
        add(txtEmail);
        add(lblTelefono);
        add(txtTelefono);
        add(lblDireccion);
        add(txtDireccion);
        add(new JLabel()); // Espaciador
        add(btnActualizar);
    }

    private void updateActualizarDatosPersonales() {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();

        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
        }
    }
}
