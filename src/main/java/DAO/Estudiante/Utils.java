package DAO.Estudiante;

import models.Estudiante;
import jakarta.validation.ConstraintViolationException;

public class Utils {

    public void asignarRegistroEstudiante(Estudiante estudiante, String email, String contrasena, 
                                           String matricula) throws ConstraintViolationException {
        estudiante.setContrasena(contrasena);
        estudiante.setEmail(email);
        estudiante.setMatricula(matricula);
        estudiante.validate(); // Puede lanzar ConstraintViolationException
    }

    public void asignarActualizarEstudiante(Estudiante estudiante, String telefono, String nombre, 
                                             String direccion, String genero) throws ConstraintViolationException {
        estudiante.setTelefono(telefono);
        estudiante.setNombre(nombre);
        estudiante.setDireccion(direccion);
        estudiante.setGenero(genero);
        estudiante.validate(); // Puede lanzar ConstraintViolationException
    }
}
