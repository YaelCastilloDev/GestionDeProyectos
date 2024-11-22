package models;

import jakarta.validation.constraints.*;
import java.util.Date;

public class Proyecto {
    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @Size(max = 100, message = "El nombre del proyecto no debe exceder los 100 caracteres")
    private String Nombre;

    @NotNull(message = "La fecha límite no puede estar vacía")
    @Future(message = "La fecha límite debe ser una fecha futura")
    private Date FechaLimite;

    @NotNull(message = "La fecha de inicio no puede estar vacía")
    @PastOrPresent(message = "La fecha de inicio debe ser hoy o en el pasado")
    private Date FechaInicio;

    @NotNull(message = "La fecha de entrega no puede estar vacía")
    @PastOrPresent(message = "La fecha de entrega debe ser hoy o en el pasado")
    private Date FechaEntregado;
}
