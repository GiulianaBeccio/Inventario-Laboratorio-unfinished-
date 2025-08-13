package com.labo.inventarioApi.alumno;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//Construye el path (link) con el que se accede
//Ejemplo: http://localhost:8080/api/v1/alumno
@RestController
@RequestMapping(path = "api/v1/alumno")
public class AlumnoController {
    
    //Define un Servicio
    private final AlumnoService alumnoService;
    
    
    //Constructor de Alumno Service (inyeccion de dependencias)
    @Autowired
    public AlumnoController(AlumnoService alumnoService){
        this.alumnoService = alumnoService;
    }
    
    //Getter de Alumnos (devuelve lista de alumnos)
    @GetMapping
    public List<Alumno> getAlumno(){
        return alumnoService.getListaAlumnos();
    }
    
    //Add new resources (PostMapping)
    //Metodo Agregar nuevos alumnos
    @PostMapping
    public void registernewAlumno(@RequestBody Alumno alumno){
        alumnoService.addNewAlumno(alumno);
    }
    
    //Delete resources (DeleteMapping)
    //Metodo eliminar alumno
    @DeleteMapping(path = "{alumnoId}")
    public void eliminarAlumno(@PathVariable("alumnoId") Long alumnoId){
        alumnoService.deleteAlumno(alumnoId);
    }
    
    @PutMapping(path = "{AlumnoId}")
    public void updateAlumno(@PathVariable("alumnoId") Long alumnoId, 
                             @RequestParam(required = false) String nombre,
                             @RequestParam(required = false) String email)
    {
        alumnoService.updateAlumno(alumnoId,nombre,email);
    }
    
}

