package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;
import com.tup.programacion3.interfaces.Calculable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Set<DetallePedido> detalles;

    public Pedido(Long id, LocalDate fecha, FormaPago formaPago) {
        super(id);
        this.fecha = fecha;
        this.formaPago = formaPago;
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
        this.detalles = new HashSet<>();
    }

    //Metodos
    // Busca el detalle de un producto. Si no lo encuentra, devuelve null
    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }
        return null;
    }

    // Agrega un producto al pedido. Si ya estaba, le suma la cantidad
    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido existente = findeDetallePedidoByProducto(producto);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
        } else {
            Long idDetalle = (long) (detalles.size() + 1);
            DetallePedido nuevo = new DetallePedido(idDetalle, cantidad, producto);
            detalles.add(nuevo);
        }

        calcularTotal();
    }

    // Borra el detalle de un producto (si existe)
    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalle = findeDetallePedidoByProducto(producto);

        if (detalle != null) {
            detalles.remove(detalle);
            calcularTotal();
        }
    }

    // Método de la interfaz Calculable
    @Override
    public void calcularTotal() {
        double suma = 0.0;
        for (DetallePedido detalle : detalles) {
            suma = suma + detalle.getSubtotal();
        }
        this.total = suma;
    }

    // Getters y setters
    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Set<DetallePedido> getDetalles() {
        return detalles;
    }

    // toString, equals y hashCode
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Pedido{id=").append(getId());
        sb.append(", fecha=").append(fecha);
        sb.append(", estado=").append(estado);
        sb.append(", formaPago=").append(formaPago);
        sb.append(", total=$").append(total).append("}");

        for (DetallePedido detalle : detalles) {
            sb.append("\n    ").append(detalle);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}