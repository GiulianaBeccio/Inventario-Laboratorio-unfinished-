package com.labo.inventarioApi.registration;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    
    private final RegistrationService registrationService;
    
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request, BindingResult result) {
        if (result.hasErrors()) {
            // Devolver errores de validación como JSON
            StringBuilder errores = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                errores.append(error.getField())
                       .append(": ")
                       .append(error.getDefaultMessage())
                       .append(". ")
            );
            return ResponseEntity.badRequest().body(
                java.util.Map.of("error", errores.toString())
            );
        }

        try {
            String respuesta = registrationService.register(request);
            return ResponseEntity.ok(
                java.util.Map.of("message", respuesta)
            );
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of("error", e.getMessage())
            );
        }
    }

    
    @GetMapping(path = "confirm")
    public RedirectView confirm(@RequestParam("token") String token) {
        RedirectView redirectView = new RedirectView();

        try {
            registrationService.confirmToken(token);
            redirectView.setUrl("http://localhost:4200/activado?estado=exito");
        } catch (IllegalStateException e) {
            // Codificamos el mensaje en la URL para que Angular lo interprete
            String errorMessage = e.getMessage().replace(" ", "_").toLowerCase();
            redirectView.setUrl("http://localhost:4200/activado?estado=error&mensaje=" + errorMessage);
        }

        return redirectView;
    }
    
}
