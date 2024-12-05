package DAO.Proyecto;

import jakarta.validation.ConstraintViolationException;
import models.Proyecto;

public class Utils {

    public void AsignarRegistro(Proyecto proyecto, String nombre, String descripcion) {
        try {
            proyecto.setNombre(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.validate();
        }catch (ConstraintViolationException e) {
            System.err.println(e.getMessage());
        }
    }
}
