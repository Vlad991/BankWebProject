package com.bank.dto;

public enum Role {
    CLIENT("CLIENT"),
    ADMIN("ADMIN"),
    UNKNOWN("UNKNOWN");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
