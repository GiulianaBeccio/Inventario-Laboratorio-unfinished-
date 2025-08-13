package com.labo.inventarioApi.profesor;

import com.labo.inventarioApi.appUser.AppUser;
import com.labo.inventarioApi.appUser.AppUserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profesor")
@PrimaryKeyJoinColumn(name = "id") // usa la misma PK que AppUser
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor extends AppUser {

    private String materiaDada;
    private String carrera;
    
    public Profesor(String nombre, String apellido, String email, String password, AppUserRole role, String materiaDada, String carrera) {
        super(nombre, apellido, email, password, role); // llama al constructor de AppUser
        this.materiaDada = materiaDada;
        this.carrera = carrera;
    }

    
}

