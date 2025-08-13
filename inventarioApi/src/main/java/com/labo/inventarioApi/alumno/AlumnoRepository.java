package com.labo.inventarioApi.alumno;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional(readOnly = true)
public interface AlumnoRepository extends JpaRepository<Alumno,Long>{
    
    Optional <Alumno> findAlumnoByEmail(String email);
    /*Se traduce como SELECT * FROM Alumno WHERE email = ?
        Otra forma de hacerlo seria:
        @Query("SELECT s FROM Alumno s WHERE s.email = ?1")
    
        Se utiliza luego para saber si existe el email en la base de datos
    */
    
    
}
