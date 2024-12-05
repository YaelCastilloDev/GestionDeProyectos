package DAO.Estudiante;
import models.Estudiante;

import java.util.List;


public interface DAO {
    // Método para registrar un nuevo estudiante
    public boolean postRegistrar(String email, String contrasena, String matricula);

    // Método para actualizar los datos de un estudiante existente
    boolean updateActualizarDatosPersonales(Estudiante estudiante);

    boolean updateActualizarDatosPersonales(String email, String telefono, String nombre, String direccion, String genero);

    Estudiante getEstudiante(String email);
}