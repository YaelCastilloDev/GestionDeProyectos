package models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Estudiante extends Usuario{

    @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 9, max = 9, message = "La matricula debe tener 9 caracteres")
    private String Matricula;
}
