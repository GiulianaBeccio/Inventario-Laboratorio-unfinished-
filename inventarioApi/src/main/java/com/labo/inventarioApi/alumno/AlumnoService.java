package com.labo.inventarioApi.alumno;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {
    
//    private final AppUser appUser;
    //Define un Repositorio
    private final AlumnoRepository alumnoRepository;
    
    //Constructor de Alumno Repository
    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;
    }
    
    //Para el Metodo de Lista de todos los alumnos
    public List<Alumno> getListaAlumnos(){
        return alumnoRepository.findAll();
    }
    
    
    //Nuevo alumno
    public void addNewAlumno(Alumno alumno){
        Optional <Alumno> studentOptional = alumnoRepository.findAlumnoByEmail(alumno.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("La direccion de correo electronico ya existe");
        }
        alumnoRepository.save(alumno);
    }

    
    //Eliminar Alumno
    public void deleteAlumno(Long alumnoId) {
        boolean existe = alumnoRepository.existsById(alumnoId);
        if(!existe){
            throw new IllegalStateException("Alumno con Id " + alumnoId + "no existe.");
        }
        alumnoRepository.deleteById(alumnoId);
    }
    
    @Transactional
    public void updateAlumno(Long alumnoId, String nombre, String email){
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow(() -> new IllegalStateException
                                                            ("Alumno con id " + alumnoId + " no existe."));
        
        if(nombre != null && nombre.length() > 0 && !Objects.equals(alumno.getNombre(),nombre)){
            alumno.setNombre(nombre);
        }
        
        if(email != null && email.length() > 0 && !Objects.equals(alumno.getEmail(),email)){
            Optional <Alumno> alumnoOptional = alumnoRepository.findAlumnoByEmail(email);
            if(alumnoOptional.isPresent()){
                throw new IllegalStateException("Email usado");
            }
            alumno.setEmail(email);
        }
    }     
    

}
