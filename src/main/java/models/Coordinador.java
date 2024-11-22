package models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Coordinador extends Usuario {

    @NotBlank(message = "Número de personal no puede estar vacío")
    @Size(min = 5, message = "El número de personal no debe ser menor a 15 caracteres")
    @Size(max = 15, message = "El número de personal no debe exceder los 15 caracteres")
    private String NoPersonal;

    public String getNoPersonal() {
        return NoPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.NoPersonal = noPersonal;
    }
}

