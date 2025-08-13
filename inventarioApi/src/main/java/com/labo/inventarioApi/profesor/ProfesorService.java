package com.labo.inventarioApi.profesor;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    // Crear o actualizar profesor
    public Profesor saveProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    // Buscar todos
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    // Buscar por id
    public Optional<Profesor> getProfesorById(Long id) {
        return profesorRepository.findById(id);
    }

    // Eliminar por id
    public void deleteProfesor(Long id) {
        profesorRepository.deleteById(id);
    }
}
