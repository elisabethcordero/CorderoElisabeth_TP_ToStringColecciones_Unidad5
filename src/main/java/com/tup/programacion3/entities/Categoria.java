package com.tup.programacion3.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Categoria extends Base {

    private String nombre;
    private String descripcion;
    private Set<Producto> productos;

    public Categoria(Long id, String nombre, String descripcion) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new HashSet<>();
    }

    // Métodos para manejar la colección
    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public void removeProducto(Producto producto) {
        productos.remove(producto);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    // toString, equals y hashCode
    @Override
    public String toString() {
        return "Categoria{id=" + getId() +
                ", nombre='" + nombre + "'" +
                ", descripcion='" + descripcion + "'" +
                ", cantidadProductos=" + productos.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}