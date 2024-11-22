package Controller;
import models.Estudiante;


public class EstudianteController {
    public Estudiante registrarEstudiante(String nombre, String id, String email, String telefono,
                                          String direccion, String genero, String matricula) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setId(id);
        estudiante.setEmail(email);

        return estudiante;
    }
}
