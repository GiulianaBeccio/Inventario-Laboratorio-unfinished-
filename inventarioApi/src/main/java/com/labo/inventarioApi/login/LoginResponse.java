package com.labo.inventarioApi.login;


public class LoginResponse {
    private String message;
    private String token;
    private String role;
    private String email;

    public LoginResponse() {}

    public LoginResponse(String message, String token, String role, String email) {
        this.message = message;
        this.token = token;
        this.role = role;
        this.email = email;
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
}
