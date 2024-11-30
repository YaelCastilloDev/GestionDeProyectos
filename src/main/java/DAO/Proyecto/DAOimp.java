package DAO.Proyecto;

import DBConeccion.SQLConeccion;
import jakarta.validation.ConstraintViolationException;
import models.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOimp implements DAO {

    Utils utils = new Utils();

    @Override
    public boolean postRegistrar(Proyecto proyecto)  {
        return false;
    }

    @Override
    public List<Proyecto> getVisualizarProyectos(Proyecto proyecto) {
        return List.of();
    }

    @Override
    public boolean postRegistrar(String nombre, String descripcion) {

        models.Proyecto proyecto = new models.Proyecto();

        utils.AsignarRegistro(proyecto, nombre, descripcion);
        String insertProyecto = "INSERT INTO proyecto (nombre, descripcion) VALUES ( ?, ?)";


        Connection conn = null;
        PreparedStatement stmtUsuarioBase = null;

        try {
            conn = SQLConeccion.getConnection();
            conn.setAutoCommit(false); // Se agura que las inserciones se realizan correctamente antes de guardar los cambios.

            // Inserta datos en la tabla usuario_base
            stmtUsuarioBase = conn.prepareStatement(insertProyecto, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUsuarioBase.setString(1, nombre);
            stmtUsuarioBase.setString(2, descripcion);
            stmtUsuarioBase.executeUpdate();

            // Obtiene el ID generado
            ResultSet rs = stmtUsuarioBase.getGeneratedKeys();
            int idUsuario = 0;
            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public List<Proyecto> getVisualizarProyectos() {
        String selectProyecto = "SELECT nombre, descripcion FROM proyecto";
        List<Proyecto> proyectos = new ArrayList<>();

        try (Connection conn = SQLConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectProyecto);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proyecto proyecto = new Proyecto();

                // Asignar valores usando setters
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setDescripcion(rs.getString("descripcion"));

                // Validar el proyecto
                proyecto.validate();

                // Agregar a la lista si es válido
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            System.err.println("Error al visualizar los proyectos: " + e.getMessage());
        } catch (ConstraintViolationException e) {
            System.err.println("Validación fallida: " + e.getMessage());
        }
        return proyectos;
    }
}
