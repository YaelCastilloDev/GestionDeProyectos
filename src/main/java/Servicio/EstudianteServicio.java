package Servicio;

import DAO.Estudiante.DAO;
import DAO.Estudiante.DAOimp;
import models.Estudiante;

public class EstudianteServicio {

    private DAO estudianteDAO;

    public EstudianteServicio() {
        this.estudianteDAO = new DAOimp(); // Inyectar la implementación concreta
    }

    public boolean registrarEstudiante(Estudiante estudiante, String email, String contrasena, String matricula) {
        // Aquí podrías añadir lógica adicional, como validaciones previas
        return estudianteDAO.postRegistrar(estudiante, email, contrasena, matricula);
    }

    public boolean actualizarDatosPersonales(Estudiante estudiante, String email, String telefono, String nombre, String direccion, String genero) {
        // Validaciones adicionales o transformaciones si son necesarias
        return estudianteDAO.updateActualizarDatosPersonales(estudiante, email, telefono, nombre, direccion, genero);
    }

    public Estudiante obtenerEstudiantePorEmail(String email) {
        return estudianteDAO.getEstudiante(email);
    }

    public boolean asignarProyectoAEstudiante(String email, int idProyecto) {
        return estudianteDAO.asignarProyectoAEstudiante(email, idProyecto);
    }
}
