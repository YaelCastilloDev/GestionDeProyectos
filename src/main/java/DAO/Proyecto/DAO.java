package DAO.Proyecto;

import models.Proyecto;
import java.util.List;

public interface DAO {
    boolean postRegistrar(Proyecto proyecto);
    List<Proyecto> getVisualizarProyectos(Proyecto proyecto);
    Proyecto getVisualizarProyectoEstudiante(String email); // Método agregado
    boolean postRegistrar(String nombre, String descripcion);
}
