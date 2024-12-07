package Servicio;

import DAO.Proyecto.DAOimp;
import models.Proyecto;
import DAO.Proyecto.Utils;
import DBConeccion.SQLConeccion;

import java.util.List;

public class ProyectoServicio {

    DAOimp proyectoDAO = new DAOimp();
    Utils utils = new Utils();
    Proyecto proyecto = new Proyecto();
    /**
     * Registra un nuevo proyecto con nombre y descripción.
     * @param nombre Nombre del proyecto.
     * @param descripcion Descripción del proyecto.
     * @return true si se registra exitosamente, false en caso contrario.
     */
    public boolean registrarProyecto(Proyecto proyecto, String nombre, String descripcion) {
        SQLConeccion.tryConnection();
        utils.AsignarRegistro(proyecto, nombre, descripcion);
        return proyectoDAO.postRegistrar(nombre, descripcion);
    }

    /**
     * Obtiene una lista de proyectos registrados.
     * @return Lista de proyectos.
     */
    public List<Proyecto> obtenerProyectos() {
        SQLConeccion.tryConnection();
        return proyectoDAO.getVisualizarProyectos(null);
    }

    /**
     * Obtiene el proyecto asociado a un estudiante por su email.
     * @param email Email del estudiante.
     * @return Proyecto asociado al estudiante, o null si no existe.
     */
    public List<String> obtenerProyectoEstudiante(String email) {
        SQLConeccion.tryConnection();
        Proyecto proyecto = proyectoDAO.getVisualizarProyectoEstudiante(email);
    
        if (proyecto != null) {
            return List.of(proyecto.getNombre(), proyecto.getDescripcion());
        } else {
            return List.of("Sin proyecto asignado", "Este estudiante no tiene un proyecto asociado.");
        }
    }
}