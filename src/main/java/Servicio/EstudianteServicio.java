package Servicio;

import DAO.Estudiante.DAOimp;
import DAO.Estudiante.Utils;
import DBConeccion.SQLConeccion;
import models.Estudiante;

public class EstudianteServicio {

    DAOimp estudianteDAO = new DAOimp();
    Utils utils = new Utils();
    Estudiante estudiante = new Estudiante();

    public boolean registrarEstudiante(String email, String contrasena, String matricula) {
        // Aquí podrías añadir lógica adicional, como validaciones previas
        utils.asignarRegistroEstudiante(estudiante, email, contrasena, matricula);
        SQLConeccion.tryConnection();
        return estudianteDAO.postRegistrar(estudiante, email, contrasena, matricula);
    }

    public boolean actualizarDatosPersonales(String email, String telefono, String nombre, String direccion, String genero) {
        // Validaciones adicionales o transformaciones si son necesarias
        utils.asignarActualizarEstudiante(estudiante, telefono, nombre, direccion, genero);
        SQLConeccion.tryConnection();
        return estudianteDAO.updateActualizarDatosPersonales(estudiante, email, telefono, nombre, direccion, genero);
    }

    public Estudiante obtenerEstudiantePorEmail(String email) {
        SQLConeccion.tryConnection();
        return estudianteDAO.getEstudiante(email);
    }

    public boolean asignarProyectoAEstudiante(String email, int idProyecto) {
        SQLConeccion.tryConnection();
        return estudianteDAO.asignarProyectoAEstudiante(email, idProyecto);
    }
}
