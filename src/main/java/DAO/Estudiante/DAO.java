package DAO.Estudiante;
import models.Estudiante;
import java.util.List;


public interface DAO {
    // Método para registrar un nuevo estudiante
    boolean postRegistrar(Estudiante estudiante) throws Exception;

    // Método para actualizar los datos de un estudiante existente
    boolean updateActualizar(Estudiante estudiante) throws Exception;

    // Métodos específicos para estudiantes, si es necesario
    Estudiante getBuscarPorMatricula(String matricula) throws Exception;
}
