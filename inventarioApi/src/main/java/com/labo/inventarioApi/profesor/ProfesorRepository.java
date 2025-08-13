package com.labo.inventarioApi.profesor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    // métodos personalizados si los necesitás
}
