package DAO.Estudiante;
import models.Estudiante;

import jakarta.validation.ConstraintViolationException;

public class Utils {

    public void AsignarRegistroEstudiante(Estudiante estudiante, String email, String contrasena,
                                           String matricula) {
        System.out.println(estudiante.getContrasena());
        try {
            estudiante.setContrasena(contrasena);
            estudiante.setEmail(email);
            estudiante.setMatricula( matricula );
            estudiante.validate();
        }catch (ConstraintViolationException e) {
            System.err.println(e.getMessage());
        }
    }
}
