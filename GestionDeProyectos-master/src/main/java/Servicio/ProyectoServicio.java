package Servicio;

import DAO.Proyecto.DAOimp;
import models.Proyecto;
import DAO.Proyecto.Utils;
import DBConeccion.SQLConeccion;

import java.util.List;

/**
 * Servicio que gestiona las operaciones relacionadas con los proyectos,
 * como el registro, obtención de lista de proyectos, y la obtención de un proyecto asignado a un estudiante.
 */
public class ProyectoServicio {

    DAOimp proyectoDAO = new DAOimp();

    Utils utils = new Utils();

    /**
     * Registra un nuevo proyecto en el sistema.
     *
     * @param proyecto Objeto de tipo Proyecto que contiene los detalles del proyecto.
     * @param nombre El nombre del proyecto.
     * @param descripcion La descripción del proyecto.
     * @return true si el proyecto se registró exitosamente, false si hubo un error.
     */
    public boolean registrarProyecto(String nombre, String descripcion) {
        SQLConeccion.tryConnection();
        utils.AsignarRegistro(nombre, descripcion);
        return proyectoDAO.postRegistrar(nombre, descripcion);
    }

    /**
     * Obtiene una lista de todos los proyectos registrados en el sistema.
     *
     * @return Lista de objetos Proyecto que representan los proyectos registrados.
     */
    public List<String> obtenerProyectos() {
        SQLConeccion.tryConnection();

        return List.of("Sin proyecto asignado", "Este estudiante no tiene un proyecto asociado.");
    }

    /**
     * Obtiene el proyecto asociado a un estudiante a través de su correo electrónico.
     *
     * @param email El correo electrónico del estudiante.
     * @return Lista con el nombre y la descripción del proyecto asociado al estudiante.
     *         Si el estudiante no tiene proyecto asignado, devuelve una lista con mensajes informativos.
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
