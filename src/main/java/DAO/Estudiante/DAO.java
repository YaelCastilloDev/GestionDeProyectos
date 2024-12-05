package DAO.Estudiante;
import models.Estudiante;

public interface DAO {
    String Estudiante = null;

    // Método para registrar un nuevo estudiante
    public boolean postRegistrar(Estudiante estudiante, String email, String contrasena, String matricula);

    boolean updateActualizarDatosPersonales(Estudiante estudiante, String email, String telefono, String nombre, String direccion, String genero);

    Estudiante getEstudiante(String email);

    boolean asignarProyectoAEstudiante(String email, int idProyecto);


}