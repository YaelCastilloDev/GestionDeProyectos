package Controller;

import Coordinador.EstudianteController;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class EstudianteControllerTest {

    EstudianteController estudianteController = new EstudianteController();

    @Test
    public void testRegistrarEstudiante() {
        // Test case: Registering a student with empty fields should fail
        boolean result = estudianteController.registrarEstudiante("", "", "");
        assertFalse(result, "Registro no ejecutado con éxito.");
    }
}