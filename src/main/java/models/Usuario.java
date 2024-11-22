package models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

abstract public class Usuario {
    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String Nombre;

    @NotBlank(message = "ID no puede estar vacío")
    @Pattern(regexp = "S\\d{8}", message = "El ID debe comenzar con 'S' seguido de 8 dígitos")
    private String id;

    @NotBlank(message = "Email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "Teléfono no puede estar vacío")
    @Size(min = 10, max = 10, message = "Número de teléfono inválido")
    private String Telefono;

    @NotBlank(message = "Dirección no puede estar vacía")
    @Size(max = 255, message = "La dirección debe ser más corta")
    private String Direccion;

    @NotBlank(message = "Género no puede estar vacío")
    @Pattern(regexp = "Masculino|Femenino", message = "El género debe ser 'Masculino' o 'Femenino'")
    private String Genero;

    // Getters and setters without validation annotations

    public String getDireccion() {
        return Direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getGenero() {
        return Genero;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public Usuario() {
        this.Nombre = "Sin nombre";
        this.id = "S00000000";
        this.email = "email@ejemplo.com";
        this.Telefono = "0000000000";
        this.Direccion = "Sin dirección";
        this.Genero = "Masculino"; // Valor predeterminado
    }
}
