package models;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

public class Proyecto {
    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @Size(max = 100, message = "El nombre del proyecto no debe exceder los 100 caracteres")
    private String Nombre;

    @NotBlank(message = "Se debe anadir una descripcion")
    @Size(max = 1000, message = "El nombre del proyecto no debe exceder los 1000 caracteres")
    private String Descripcion;

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Proyecto>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            // Obtiene el primer error y lanza la excepción
            ConstraintViolation<Proyecto> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}