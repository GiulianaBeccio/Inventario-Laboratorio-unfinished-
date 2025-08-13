package com.labo.inventarioApi.profesor;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profesores")
@AllArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorService.saveProfesor(profesor);
        return ResponseEntity.ok(nuevoProfesor);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> listarProfesores() {
        return ResponseEntity.ok(profesorService.getAllProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable Long id) {
        return profesorService.getProfesorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Long id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
