package entidades;

import java.util.Objects;

public class DetallePedido extends Base {

    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido(Long id, int cantidad, Producto producto) {
        super(id);
        this.cantidad = cantidad;
        this.producto = producto;
        calcularSubtotal();
    }

    // Calcula el subtotal: cantidad x precio del producto
    private void calcularSubtotal() {
        this.subtotal = cantidad * producto.getPrecio();
    }

    // Getters y setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    // toString, equals y hashCode
    @Override
    public String toString() {
        return "DetallePedido{producto='" + producto.getNombre() + "'" +
                ", cantidad=" + cantidad +
                ", precioUnitario=$" + producto.getPrecio() +
                ", subtotal=$" + subtotal + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedido that = (DetallePedido) o;
        return Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }
}