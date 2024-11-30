package DAO;

import DAO.Estudiante.DAOimp;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class EstudianteControllerTest {

    DAOimp estudianteController = new DAOimp();

    @Test
    public void testRegistrarEstudiante() {
        // Test case: Registering a student with empty fields should fail
        boolean result = estudianteController.Registrar("", "", "");
        assertFalse(result, "Registro no ejecutado con éxito.");
    }
}