package GUI.Estudiante;

import Servicio.ProyectoServicio;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VisualizarProyecto extends JFrame {

    private ProyectoServicio proyectoServicio;

    public VisualizarProyecto(String email) {
        // Inicializa la instancia del servicio
        proyectoServicio = new ProyectoServicio();

        setTitle("Visualizar Proyecto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Etiquetas para el proyecto
        JLabel lblTitulo = new JLabel("Proyecto Asignado", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false); // Solo lectura
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));

        List<String> proyectoData = obtenerProyecto(email);
     
        if (proyectoData != null && !proyectoData.isEmpty()) {
            String titulo = proyectoData.get(0);
            String descripcion = proyectoData.get(1);
            txtDescripcion.setText("Nombre del Proyecto: " + titulo + "\n\nDescripción:\n" + descripcion);
        } else {
            txtDescripcion.setText("No tienes un proyecto asignado.");
        }

        // Scroll para el texto
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);

        // Agregar componentes al panel
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Agregar panel a la ventana
        add(panel);
    }

    // Método para obtener el proyecto del estudiante desde el servicio
    private List<String> obtenerProyecto(String email) {
        try {
            return proyectoServicio.obtenerProyectoEstudiante(email);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Ocurrió un error al recuperar el proyecto: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}