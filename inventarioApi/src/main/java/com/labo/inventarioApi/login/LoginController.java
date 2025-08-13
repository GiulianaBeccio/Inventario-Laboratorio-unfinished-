package com.labo.inventarioApi.login;

import com.labo.inventarioApi.appUser.AppUser;
import com.labo.inventarioApi.appUser.AppUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            AppUser user = (AppUser) authentication.getPrincipal();

            return ResponseEntity.ok(Map.of(
                "message", "Login exitoso",
                "email", user.getEmail()
            ));
        } catch (Exception e) {
            System.out.println("💥 Error en login: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return ResponseEntity.status(401).body(Map.of("message", "Credenciales inválidas"));
        }
    }
}
