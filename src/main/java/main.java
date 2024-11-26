import DAO.Estudiante.Estudiante;
import DBConeccion.SQLConeccion;

import jakarta.validation.ConstraintViolationException;
public class main {
    public static void main(String[] args) {
        try {
            SQLConeccion.initializeConnection();

        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());

        }

        Estudiante estudianteController = new Estudiante();

        try {
            estudianteController.registrarEstudiante("  eee ", "", "e");
        } catch (ConstraintViolationException e) {
            System.err.println(e.getMessage());
        }
        //System.out.println(estudianteController.AsignarRegistroEstudiante("", "", ""));
        //boolean d = estudianteController.registrarEstudiante(" ", " ", "S23014087");


/////////////////////////////////////
/////////////////////////////////////
        ////////////////////////////SI VAN A HACER ALGUN CONTROLLER HAGANLO BOOLEANO PARA QUE SEAN MAS FACILES LOS TESTS
        /////////////////////////////////////
        /////////////////////////////////////

    }
}
