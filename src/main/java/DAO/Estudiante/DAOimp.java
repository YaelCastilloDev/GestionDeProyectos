package DAO.Estudiante;
import DBConeccion.SQLConeccion;
import models.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Security.PasswordHasher.encodePassword;

public class DAOimp implements DAO {

    Utils utils = new Utils();

    //La funcion es booleana para hacer mas facil los tests
    @Override
    public boolean postRegistrar (String email, String contrasena, String matricula){

        models.Estudiante estudiante = new models.Estudiante();

        utils.asignarRegistroEstudiante(estudiante, email, contrasena, matricula);
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

    @Override
    public boolean updateActualizarDatosPersonales(Estudiante estudiante) {
        return false;
    }

    @Override
    public boolean updateActualizarDatosPersonales(String email, String telefono, String nombre, String direccion, String genero) {

        models.Estudiante estudiante = new models.Estudiante();

        // Asignar y validar datos utilizando AsignarActualizarEstudiante
        utils.asignarActualizarEstudiante(estudiante, telefono, nombre, direccion, genero);

        String updateUsuarioBase = "UPDATE usuario_base SET nombre = ?, telefono = ?, direccion = ? WHERE email = ?";
        String updateEstudiante = "UPDATE estudiante SET genero = ? WHERE id_usuario = (SELECT id_usuario FROM usuario_base WHERE email = ?)";

        Connection conn = null;
        PreparedStatement stmtUsuarioBase = null;
        PreparedStatement stmtEstudiante = null;

        try {
            conn = SQLConeccion.getConnection();
            conn.setAutoCommit(false); // Desactivar auto-commit para transacciones seguras

            stmtUsuarioBase = conn.prepareStatement(updateUsuarioBase);
            stmtUsuarioBase.setString(1, estudiante.getNombre());
            stmtUsuarioBase.setString(2, estudiante.getTelefono());
            stmtUsuarioBase.setString(3, estudiante.getDireccion());
            stmtUsuarioBase.setString(4, email);
            int usuarioBaseRows = stmtUsuarioBase.executeUpdate();

            if (usuarioBaseRows == 0) {
                throw new SQLException("No se encontró un usuario con el email proporcionado.");
            }

            // Actualizar datos en la tabla estudiante
            stmtEstudiante = conn.prepareStatement(updateEstudiante);
            stmtEstudiante.setString(1, estudiante.getGenero());
            stmtEstudiante.setString(2, email);
            int estudianteRows = stmtEstudiante.executeUpdate();

            if (estudianteRows == 0) {
                throw new SQLException("No se encontró un estudiante asociado al usuario con el email proporcionado.");
            }

            // Confirmar cambios
            conn.commit();
            System.out.println("Estudiante actualizado exitosamente.");
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revertir cambios en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            System.err.println("Error al actualizar el estudiante: " + e.getMessage());
            return false;

        } finally {
            try {
                if (stmtUsuarioBase != null) stmtUsuarioBase.close();
                if (stmtEstudiante != null) stmtEstudiante.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Estudiante getEstudiante(String email) {
        return null;
    }
}