package DAO.Proyecto;

import models.Proyecto;

import java.util.List;

public interface DAO {
    boolean postRegistrar(Proyecto proyecto) ;
    List<Proyecto> getVisualizarProyectos(Proyecto proyecto) ;

    boolean postRegistrar(String nombre, String descripcion);

    List<Proyecto> getVisualizarProyectos();
}