package Servicio;

import DAO.Proyecto.DAO;
import DAO.Proyecto.DAOimp;
import models.Proyecto;

import java.util.List;

public class ProyectoServicio {

    private DAO proyectoDAO;

    // Constructor que permite inyección de dependencias
    public ProyectoServicio(DAO proyectoDAO) {
        this.proyectoDAO = proyectoDAO;
    }

    // Constructor por defecto
    public ProyectoServicio() {
        this.proyectoDAO = new DAOimp();
    }

    /**
     * Registra un nuevo proyecto con un objeto Proyecto.
     * @param proyecto Objeto Proyecto a registrar.
     * @return true si se registra exitosamente, false en caso contrario.
     */
    public boolean registrarProyecto(Proyecto proyecto) {
        if (proyecto.getNombre() == null || proyecto.getDescripcion() == null) {
            throw new IllegalArgumentException("El nombre y la descripción del proyecto no pueden ser nulos.");
        }
        return proyectoDAO.postRegistrar(proyecto);
    }

    /**
     * Registra un nuevo proyecto con nombre y descripción.
     * @param nombre Nombre del proyecto.
     * @param descripcion Descripción del proyecto.
     * @return true si se registra exitosamente, false en caso contrario.
     */
    public boolean registrarProyecto(String nombre, String descripcion) {
        if (nombre == null || descripcion == null) {
            throw new IllegalArgumentException("El nombre y la descripción no pueden ser nulos.");
        }
        return proyectoDAO.postRegistrar(nombre, descripcion);
    }

    /**
     * Obtiene una lista de proyectos registrados.
     * @return Lista de proyectos.
     */
    public List<Proyecto> obtenerProyectos() {
        return proyectoDAO.getVisualizarProyectos(null);
    }

    /**
     * Obtiene el proyecto asociado a un estudiante por su email.
     * @param email Email del estudiante.
     * @return Proyecto asociado al estudiante, o null si no existe.
     */
    public Proyecto obtenerProyectoEstudiante(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        return proyectoDAO.getVisualizarProyectoEstudiante(email);
    }
}