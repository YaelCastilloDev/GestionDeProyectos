package models;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.Set;

public class Estudiante extends Usuario {

    @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
    @Pattern(regexp = "S\\d{8}", message = "El ID debe comenzar con 'S' seguido de 8 dígitos")
    private String Matricula;

    public Estudiante() {
        super(); // Llama al constructor sin parámetros de Usuario
        this.Matricula = "S00000000"; // Valor predeterminado
    }

    public @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
    String getMatricula() {
        return Matricula;
    }

    public void setMatricula(@NotBlank(message = "Número de personal no puede estar vacío")
                             @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
                             String matricula) {
        this.Matricula = matricula;
    }

    public void validate() {
        super.validate(); // Validar campos de la clase padre
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Estudiante>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            // Obtiene el primer error y lanza la excepción
            ConstraintViolation<Estudiante> firstViolation = violations.iterator().next();
            throw new ConstraintViolationException(firstViolation.getMessage(), violations);
        }
    }
}
