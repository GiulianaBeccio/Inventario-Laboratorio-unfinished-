package com.labo.inventarioApi.alumno;

import com.labo.inventarioApi.appUser.AppUser;
import com.labo.inventarioApi.appUser.AppUserRole;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Alumno extends AppUser {

    private int dni;

    public Alumno(String nombre, String apellido, String email, String password, AppUserRole role, int dni) {
        super(nombre, apellido, email, password, role);
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + getId() +
                ", dni=" + dni +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
    
    
    
}
