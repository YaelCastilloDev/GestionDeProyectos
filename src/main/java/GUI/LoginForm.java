package GUI;

import Security.PasswordHasher;
import DBConeccion.SQLConeccion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginForm() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        emailField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new LoginAction());

        panel.add(new JLabel("Correo:", SwingConstants.CENTER));
        panel.add(emailField);
        panel.add(new JLabel("Contraseña:", SwingConstants.CENTER));
        panel.add(passwordField);

        add(panel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            String role = authenticate(email, password);

            if (role != null) {
                messageLabel.setText("Inicio de sesión exitoso");
                JOptionPane.showMessageDialog(LoginForm.this, "Bienvenido " + email);

                // Abrir la ventana correspondiente según el rol
                switch (role) {
                    case "estudiante":
                        EstudianteManager estudianteManager = new EstudianteManager();
                        estudianteManager.setVisible(true);
                        break;
                    case "coordinador":
                        new CoordinadorManager();
                        CoordinadorManager coordinadorManager = new CoordinadorManager();
                        coordinadorManager.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(LoginForm.this, "Rol desconocido");
                }

                dispose(); // Cierra el formulario de inicio de sesión
            } else {
                messageLabel.setText("Correo o contraseña incorrectos");
            }
        }

        private String authenticate(String email, String password) {

            SQLConeccion.tryConnection();

            String queryUsuario = "SELECT id_usuario, contrasena FROM usuario_base WHERE email = ?";
            String queryEstudiante = "SELECT id_usuario FROM estudiante WHERE id_usuario = ?";
            String queryCoordinador = "SELECT id_usuario FROM coordinador WHERE id_usuario = ?";

            try (Connection conn = SQLConeccion.getConnection();
                 PreparedStatement stmtUsuario = conn.prepareStatement(queryUsuario);
                 PreparedStatement stmtEstudiante = conn.prepareStatement(queryEstudiante);
                 PreparedStatement stmtCoordinador = conn.prepareStatement(queryCoordinador)) {

                // Verificar las credenciales en la tabla usuario_base
                stmtUsuario.setString(1, email);
                ResultSet rsUsuario = stmtUsuario.executeQuery();

                if (rsUsuario.next()) {
                    int idUsuario = rsUsuario.getInt("id_usuario");
                    String contrasenaBD = rsUsuario.getString("contrasena");

                    // Comparar contraseñas
                    if (!PasswordHasher.matches(password, contrasenaBD)) {
                        return null; // Contraseña incorrecta
                    }

                    // Verificar si es estudiante
                    stmtEstudiante.setInt(1, idUsuario);
                    ResultSet rsEstudiante = stmtEstudiante.executeQuery();

                    if (rsEstudiante.next()) {
                        return "estudiante";
                    }

                    // Verificar si es coordinador
                    stmtCoordinador.setInt(1, idUsuario);
                    ResultSet rsCoordinador = stmtCoordinador.executeQuery();

                    if (rsCoordinador.next()) {
                        return "coordinador";
                    }

                    return null; // Rol no encontrado
                } else {
                    return null; // Usuario no encontrado
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(LoginForm.this, "Error al verificar el usuario: " + ex.getMessage());
                return null;
            }
        }
    }
}
