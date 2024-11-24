package Controller.Proyecto;

import models.Proyecto;

public class ProyectoController {
    public Proyecto registrarProyecto(String nombre, String descripcion,
                                      String fechaInicio, String fechaFin) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombre);
        proyecto.setDescripcion(descripcion);



        return proyecto;
    }
}
