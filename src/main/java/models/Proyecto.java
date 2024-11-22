package models;

import jakarta.validation.constraints.*;
import java.util.Date;

public class Proyecto {
    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @Size(max = 100, message = "El nombre del proyecto no debe exceder los 100 caracteres")
    private String Nombre;

    @NotBlank(message = "Se debe anadir una descripcion")
    @Size( max = 1000, message = "El nombre del proyecto no debe exceder los 1000 caracteres")
    private String Descripcion;
}
