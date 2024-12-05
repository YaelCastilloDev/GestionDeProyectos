package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.Estudiante.DAO;
import DAO.Estudiante.DAOimp;
import DBConeccion.SQLConeccion;
import jakarta.validation.ConstraintViolationException;
import DAO.Estudiante.Utils;
import models.Estudiante;


public class EstudianteManager extends JFrame {
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JComboBox<String> cmbGenero;
    private JButton btnActualizar;
    private JTextArea txtProyectos;

    public EstudianteManager(String email) {
        setTitle("Gestión de Estudiantes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5)); // Ajustar layout para añadir más componentes

        // Campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();
        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField();
        JLabel lblGenero = new JLabel("Género:");
        cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});


        // Botón de actualizar
        btnActualizar = new JButton("Actualizar");
        txtProyectos = new JTextArea();
        txtProyectos.setEditable(false);

        // Botón para visualizar el proyecto
       // JLabel cuadroVisualizarProyecto = new JLabel(visualizarProyecto());


        // Acción de actualizar perfil
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estudiante estudiante = new Estudiante();
                Utils utils = new Utils();
                String generoSeleccionado = (String) cmbGenero.getSelectedItem();
                
                // Asignar los datos al estudiante
                utils.asignarActualizarEstudiante(estudiante, txtTelefono.getText(), txtNombre.getText(), 
                                                  txtDireccion.getText(), generoSeleccionado);

                // Aquí validamos si el estudiante es válido antes de continuar
                try {
                    estudiante.validate();  
                    SQLConeccion.tryConnection();
                    DAO estudianteDAO = new DAOimp(); // Implementación de DAO

                    boolean actualizado = estudianteDAO.updateActualizarDatosPersonales(
                            estudiante,
                            email, // Usar el correo pasado al constructor
                            txtTelefono.getText(),
                            txtNombre.getText(),
                            txtDireccion.getText(),
                            generoSeleccionado
                    );

                    if (actualizado) {
                        JOptionPane.showMessageDialog(EstudianteManager.this, "Datos actualizados con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(EstudianteManager.this, "Error al actualizar los datos.");
                    }
                } catch (ConstraintViolationException validationException) {
                    // Si la validación falla, no se guarda nada y mostramos el error
                    JOptionPane.showMessageDialog(EstudianteManager.this, 
                                                  "Error en los datos ingresados: " + validationException.getMessage(), 
                                                  "Error de validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar componentes al JFrame
        add(lblNombre);
        add(txtNombre);
        add(lblTelefono);
        add(txtTelefono);
        add(lblDireccion);
        add(txtDireccion);
        add(lblGenero);
        add(cmbGenero);
        add(btnActualizar);
        add(new JScrollPane(txtProyectos));

        // Cargar información del estudiante
    }


}
