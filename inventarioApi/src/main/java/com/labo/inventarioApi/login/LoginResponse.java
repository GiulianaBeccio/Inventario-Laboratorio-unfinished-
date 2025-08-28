package com.labo.inventarioApi.login;


public class LoginResponse {
    private String message;
    private String token;
    private String role;
    private String email;
    private String nombre;
    private String apellido;

    public LoginResponse() {}

    public LoginResponse(String message, String token, String role, String email, String nombre, String apellido) {
        this.message = message;
        this.token = token;
        this.role = role;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    

    // Getters y Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNombre() { return nombre;}
    public void setNombre(String nombre){ this.nombre = nombre; };

    public String getApellido() { return apellido;}
    public void setApellido(String apellido){ this.apellido = apellido; };
}
