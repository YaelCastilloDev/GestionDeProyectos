package models;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

public class Coordinador extends Usuario {

    @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 5, message = "El número de personal no debe ser menor a 5 caracteres")
    @Size(max = 15, message = "El número de personal no debe exceder los 15 caracteres")
    private String NoPersonal;

    public String getNoPersonal() {
        return NoPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.NoPersonal = noPersonal;
    }

    public void validate() {
        super.validate(); // Validate parent fields
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Coordinador>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            // Obtiene el primer error y lanza la excepción
            ConstraintViolation<Coordinador> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}

