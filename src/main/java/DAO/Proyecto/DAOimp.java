package DAO.Proyecto;

import DBConeccion.SQLConeccion;
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
    public boolean postRegistrar(Proyecto proyecto) {
        // Implementación de registrar proyecto (ya existente)
        return false;
    }

    @Override
    public List<Proyecto> getVisualizarProyectos(Proyecto proyecto) {
        // Implementación para visualizar proyectos (ya existente)
        return new ArrayList<>();
    }

    @Override
    public Proyecto getVisualizarProyectoEstudiante(String email) {
        String selectProyecto = "SELECT p.nombre, p.descripcion " +
                                "FROM proyecto p " +
                                "JOIN estudiante e ON e.id_proyecto = p.id_proyecto " +
                                "JOIN usuario_base u ON u.id_usuario = e.id_usuario " +
                                "WHERE u.email = ?";
        
        Proyecto proyecto = null;
    
        try (Connection conn = SQLConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectProyecto)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                proyecto = new Proyecto();
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al visualizar el proyecto del estudiante: " + e.getMessage());
        }
        return proyecto;
    }

    @Override
    public boolean postRegistrar(String nombre, String descripcion) {
        throw new UnsupportedOperationException("Unimplemented method 'postRegistrar'");
    }
}
