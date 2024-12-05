package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoordinadorManager extends JFrame {
    private JButton btnRegistrarProyecto;
    private JButton btnVisualizarProyectos;
    private JTextField txtNombreProyecto;
    private JTextArea txtDescripcionProyecto;

    public CoordinadorManager() {
        setTitle("Gestión de Coordinadores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para registrar proyectos
        JPanel panelRegistrar = new JPanel();
        panelRegistrar.setLayout(new GridLayout(3, 2, 5, 5));
        panelRegistrar.setBorder(BorderFactory.createTitledBorder("Registrar Proyecto"));

        JLabel lblNombre = new JLabel("Nombre del Proyecto:");
        txtNombreProyecto = new JTextField();
        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcionProyecto = new JTextArea(3, 20);

        btnRegistrarProyecto = new JButton("Registrar");
        btnRegistrarProyecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postRegistrar();
            }
        });

        panelRegistrar.add(lblNombre);
        panelRegistrar.add(txtNombreProyecto);
        panelRegistrar.add(lblDescripcion);
        panelRegistrar.add(new JScrollPane(txtDescripcionProyecto));
        panelRegistrar.add(btnRegistrarProyecto);

        // Panel para visualizar proyectos
        JPanel panelVisualizar = new JPanel();
        panelVisualizar.setLayout(new BorderLayout());
        panelVisualizar.setBorder(BorderFactory.createTitledBorder("Proyectos Disponibles"));

        btnVisualizarProyectos = new JButton("Visualizar Proyectos");
        btnVisualizarProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getVisualizarProyectos();
            }
        });

        panelVisualizar.add(btnVisualizarProyectos, BorderLayout.CENTER);

        // Agregar paneles al JFrame
        add(panelRegistrar, BorderLayout.NORTH);
        add(panelVisualizar, BorderLayout.SOUTH);
    }

    private void postRegistrar() {
        String nombre = txtNombreProyecto.getText();
        String descripcion = txtDescripcionProyecto.getText();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Proyecto registrado: " + nombre);
            txtNombreProyecto.setText("");
            txtDescripcionProyecto.setText("");
        }
    }

    private void getVisualizarProyectos() {
        JOptionPane.showMessageDialog(this, "Aquí se mostrarán los proyectos disponibles");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoordinadorManager frame = new CoordinadorManager();
            frame.setVisible(true);
        });
    }
}
