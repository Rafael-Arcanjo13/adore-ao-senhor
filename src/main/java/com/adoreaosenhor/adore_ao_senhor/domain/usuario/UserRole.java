package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

public enum UserRole {
    ADMIN("Admin"),
    USER("User"),
    LIDER("Líder");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
