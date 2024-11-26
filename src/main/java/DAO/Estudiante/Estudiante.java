package DAO.Estudiante;
import DBConeccion.SQLConeccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Security.PasswordHasher.encodePassword;

public class Estudiante {

    //La funcion es booleana para hacer mas facil los tests
    public  static boolean registrarEstudiante(String email, String contrasena, String matricula) {

        models.Estudiante estudiante = new models.Estudiante();
        Utils utils = new Utils();

        utils.AsignarRegistroEstudiante(estudiante, email, contrasena, matricula);
        String ContrasenaHasheada = encodePassword(estudiante.getContrasena());
        String insertUsuarioBase = "INSERT INTO usuario_base (email, ContrasenaHasheada) VALUES ( ?, ?)";
        String insertEstudiante = "INSERT INTO estudiante (id_usuario, matricula) VALUES (?, ?)";


        Connection conn = null;
        PreparedStatement stmtUsuarioBase = null;
        PreparedStatement stmtEstudiante = null;

        try {
            conn = SQLConeccion.getConnection();
            conn.setAutoCommit(false); // Se agura que las inserciones se realizan correctamente antes de guardar los cambios.

            // Inserta datos en la tabla usuario_base
            stmtUsuarioBase = conn.prepareStatement(insertUsuarioBase, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuarioBase.setString(1, email);
            stmtUsuarioBase.setString(2, ContrasenaHasheada);
            stmtUsuarioBase.executeUpdate();

            // Obtiene el ID generado
            ResultSet rs = stmtUsuarioBase.getGeneratedKeys();
            int idUsuario = 0;
            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }

            // Inserta datos en la tabla estudiante
            stmtEstudiante = conn.prepareStatement(insertEstudiante);
            stmtEstudiante.setInt(1, idUsuario);
            stmtEstudiante.setString(2, matricula);
            stmtEstudiante.executeUpdate();

            // Guarda e imprime el resultado
            conn.commit();
            System.out.println("Estudiante registrado exitosamente.");
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revierte cambios en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.err.println("Error al registrar el estudiante: " + e.getMessage());
            return false;

        } finally {
            try {
                if (stmtUsuarioBase != null) stmtUsuarioBase.close();
                if (stmtEstudiante != null) stmtEstudiante.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}