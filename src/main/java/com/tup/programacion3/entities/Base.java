package com.tup.programacion3.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Base {

    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    public Base(Long id) {
        this.id = id;
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // toString, equals y hashCode
    @Override
    public String toString() {
        return "id=" + id + ", eliminado=" + eliminado + ", createdAt=" + createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(id, base.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}