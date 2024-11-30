package DAO.Proyecto;

import models.Estudiante;
import models.Proyecto;

public interface DAO {
    boolean Registrar(Proyecto proyecto) throws Exception;
    boolean Visualizar(Proyecto proyecto) throws Exception;
}