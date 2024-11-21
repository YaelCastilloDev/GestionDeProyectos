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
    @Pattern(regexp = "\\+?\\d{10,15}", message = "Número de teléfono inválido")
    private String Telefono;

    @NotBlank(message = "Dirección no puede estar vacía")
    @Size(max = 255, message = "La dirección debe ser más corta")
    private String Direccion;

    @NotBlank(message = "Género no puede estar vacío")
    @Pattern(regexp = "Masculino|Femenino|Otro", message = "El género debe ser 'Masculino', 'Femenino' u 'Otro'")
    private String Genero;

// Getters y setters

    public @NotBlank(message = "Dirección no puede estar vacía") @Size(max = 255, message = "La dirección debe ser más corta") String getDireccion() {
        return Direccion;
    }

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^[^\\s]+$", message = "La contraseña no puede contener espacios en blanco")
    private String contrasena;

    public @NotBlank(message = "Email no puede estar vacío") @Email(message = "Formato de email inválido") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Género no puede estar vacío") @Pattern(regexp = "Masculino|Femenino|Otro", message = "El género debe ser 'Masculino', 'Femenino' u 'Otro'") String getGenero() {
        return Genero;
    }

    public @NotBlank(message = "ID no puede estar vacío") @Pattern(regexp = "S\\d{8}", message = "El ID debe comenzar con 'S' seguido de 8 dígitos") String getId() {
        return id;
    }

    public @NotBlank(message = "Nombre no puede estar vacío") @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres") String getNombre() {
        return Nombre;
    }

    public @NotBlank(message = "Teléfono no puede estar vacío") @Pattern(regexp = "\\+?\\d{10,15}", message = "Número de teléfono inválido") String getTelefono() {
        return Telefono;
    }

    public void setDireccion(@NotBlank(message = "Dirección no puede estar vacía") @Size(max = 255, message = "La dirección debe ser más corta") String direccion) {
        Direccion = direccion;
    }

    public void setEmail(@NotBlank(message = "Email no puede estar vacío") @Email(message = "Formato de email inválido") String email) {
        this.email = email;
    }

    public void setGenero(@NotBlank(message = "Género no puede estar vacío") @Pattern(regexp = "Masculino|Femenino|Otro", message = "El género debe ser 'Masculino', 'Femenino' u 'Otro'") String genero) {
        Genero = genero;
    }

    public void setId(@NotBlank(message = "ID no puede estar vacío") @Pattern(regexp = "S\\d{8}", message = "El ID debe comenzar con 'S' seguido de 8 dígitos") String id) {
        this.id = id;
    }

    public void setNombre(@NotBlank(message = "Nombre no puede estar vacío") @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres") String nombre) {
        Nombre = nombre;
    }

    public void setTelefono(@NotBlank(message = "Teléfono no puede estar vacío") @Pattern(regexp = "\\+?\\d{10,15}", message = "Número de teléfono inválido") String telefono) {
        Telefono = telefono;
    }
}
