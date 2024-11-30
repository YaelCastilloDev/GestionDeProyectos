package DAO.Estudiante;
import models.Estudiante;

import jakarta.validation.ConstraintViolationException;

public class Utils {

    public void AsignarRegistroEstudiante(Estudiante estudiante, String email, String contrasena,
                                           String matricula) {
        try {
            estudiante.setContrasena(contrasena);
            estudiante.setEmail(email);
            estudiante.setMatricula( matricula );
            estudiante.validate();
        }catch (ConstraintViolationException e) {
            System.err.println(e.getMessage());
        }
    }

    public void AsignarActualizarEstudiante(Estudiante estudiante, String telefono, String nombre, String direccion, String genero) {
        try {
            estudiante.setTelefono(telefono);
            estudiante.setNombre(nombre);
            estudiante.setDireccion(direccion);
            estudiante.setGenero(genero);
            estudiante.validate();
        } catch (ConstraintViolationException e) {
            System.err.println(e.getMessage());
        }
    }
}
