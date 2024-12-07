package DAO.Proyecto;

import jakarta.validation.ConstraintViolationException;
import models.Proyecto;

public class Utils {

    public void AsignarRegistro(Proyecto proyecto, String nombre, String descripcion) throws ConstraintViolationException {
        try {
            proyecto.setNombre(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.validate();
        } catch (ConstraintViolationException e) {
            // Imprimir el error para depuración (opcional)
            System.err.println("Error de validación: " + e.getMessage());
            // Lanzar la excepción para que se maneje en una capa superior
            throw e;
        }
    }
}
