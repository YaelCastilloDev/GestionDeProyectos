package GUI;

import Security.PasswordHasher;
import DBConeccion.SQLConeccion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Security;
import java.sql.*;



    public class LoginForm extends JFrame {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JLabel messageLabel;

        public JTextField getUsernameField() {
            return usernameField;
        }

        public JPasswordField getPasswordField() {
            return passwordField;
        }


        public LoginForm() {
            setTitle("Inicio de Sesion");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Centrar la ventana

            initComponents();
        }

        private void initComponents() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1, 10, 10));

            usernameField = new JTextField();
            passwordField = new JPasswordField();
            messageLabel = new JLabel("", SwingConstants.CENTER);
            messageLabel.setForeground(Color.RED);

            JButton loginButton = new JButton("Iniciar Sesion");
            loginButton.addActionListener(new LoginAction());

            panel.add(new JLabel("User:", SwingConstants.CENTER));
            panel.add(usernameField);
            panel.add(new JLabel("Password:", SwingConstants.CENTER));
            panel.add(passwordField);

            add(panel, BorderLayout.CENTER);
            add(loginButton, BorderLayout.SOUTH);
            add(messageLabel, BorderLayout.NORTH);
        }

        private class LoginAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String role = authenticate(username, password);

                if (role != null) {
                    messageLabel.setText("Inicio de sesión exitoso");
                    JOptionPane.showMessageDialog(LoginForm.this, "Bienvenido " + username);

                    // Abre la ventana correspondiente
                    switch (role) {
                        case "estudiante":
                            new EstudianteManager();
                            break;
                        case "coordinador":
                            new CoordinadorManager();
                            break;
                        default:
                            JOptionPane.showMessageDialog(LoginForm.this, "Rol desconocido");
                    }

                    dispose(); // Cierra el formulario de inicio de sesión
                } else {
                    messageLabel.setText("Usuario o contraseña incorrectos");
                }
            }

            private String authenticate(String username, String password) {
                PasswordHasher passwordHasher = new PasswordHasher();
                String contrasenaHasheada = passwordHasher.encodePassword(password);

                DBConeccion.SQLConeccion.tryConnection();

                String queryUsuario = "SELECT id_usuario, contrasena FROM usuario_base WHERE nombre = ?";
                String queryEstudiante = "SELECT id_usuario FROM estudiante WHERE id_usuario = ?";
                String queryCoordinador = "SELECT id_usuario FROM coordinador WHERE id_usuario = ?";

                try (Connection conn = SQLConeccion.getConnection();
                     PreparedStatement stmtUsuario = conn.prepareStatement(queryUsuario);
                     PreparedStatement stmtEstudiante = conn.prepareStatement(queryEstudiante);
                     PreparedStatement stmtCoordinador = conn.prepareStatement(queryCoordinador)) {

                    // Verifica las credenciales en la tabla usuario_base
                    stmtUsuario.setString(1, username);
                    ResultSet rsUsuario = stmtUsuario.executeQuery();

                    if (rsUsuario.next()) {
                        int idUsuario = rsUsuario.getInt("id_usuario");
                        String contrasenaBD = rsUsuario.getString("contrasena");

                        // Comparar contraseñas
                        if (!contrasenaHasheada.equals(contrasenaBD)) {
                            return null; // Contraseña incorrecta
                        }

                        // Buscar en la tabla estudiante
                        stmtEstudiante.setInt(1, idUsuario);
                        ResultSet rsEstudiante = stmtEstudiante.executeQuery();

                        if (rsEstudiante.next()) {
                            return "estudiante";
                        }

                        // Buscar en la tabla coordinador
                        stmtCoordinador.setInt(1, idUsuario);
                        ResultSet rsCoordinador = stmtCoordinador.executeQuery();

                        if (rsCoordinador.next()) {
                            return "coordinador";
                        }

                        return null;     // Retorna nulo si no está en ninguna tabla
                    } else {
                        return null; // Usuario no encontrado
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Error al verificar el usuario: " + e.getMessage());
                    return null;
                }
            }
        }

    }
