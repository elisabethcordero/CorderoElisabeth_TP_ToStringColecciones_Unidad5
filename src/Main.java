import entidades.*;
import enums.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // ===== 1. CATEGORÍAS =====
        Categoria bebidas = new Categoria(1L, "Bebidas", "Gaseosas, aguas y jugos");
        Categoria golosinas = new Categoria(2L, "Golosinas", "Alfajores, chocolates y caramelos");
        Categoria almacen = new Categoria(3L, "Almacen", "Productos de despensa");

        // ===== 2. PRODUCTOS =====
        Producto cocaCola = new Producto(1L, "Coca Cola 500ml", 1500.0, "Gaseosa sabor cola", 50, "coca.jpg", true);
        Producto agua = new Producto(2L, "Agua Mineral 1L", 900.0, "Agua sin gas", 40, "agua.jpg", true);
        Producto jugo = new Producto(3L, "Jugo de Naranja 1L", 1800.0, "Jugo exprimido", 20, "jugo.jpg", true);
        Producto alfajor = new Producto(4L, "Alfajor Triple", 1200.0, "Alfajor de chocolate", 60, "alfajor.jpg", true);
        Producto chocolate = new Producto(5L, "Chocolate con Leche", 2500.0, "Tableta de 100g", 30, "chocolate.jpg", true);
        Producto caramelos = new Producto(6L, "Caramelos Surtidos", 800.0, "Bolsa de 150g", 25, "caramelos.jpg", true);
        Producto arroz = new Producto(7L, "Arroz 1kg", 1300.0, "Arroz largo fino", 35, "arroz.jpg", true);
        Producto fideos = new Producto(8L, "Fideos Spaghetti", 1100.0, "Paquete de 500g", 45, "fideos.jpg", true);
        Producto aceite = new Producto(9L, "Aceite de Girasol", 3200.0, "Botella de 1.5L", 15, "aceite.jpg", true);
        Producto yerba = new Producto(10L, "Yerba Mate 1kg", 4500.0, "Yerba con palo", 0, "yerba.jpg", false);

        // Asignar productos a sus categorías
        bebidas.addProducto(cocaCola);
        bebidas.addProducto(agua);
        bebidas.addProducto(jugo);

        golosinas.addProducto(alfajor);
        golosinas.addProducto(chocolate);
        golosinas.addProducto(caramelos);

        almacen.addProducto(arroz);
        almacen.addProducto(fideos);
        almacen.addProducto(aceite);
        almacen.addProducto(yerba);

        // Colección con todos los productos cargados
        Set<Producto> productos = new LinkedHashSet<>();
        productos.add(cocaCola);
        productos.add(agua);
        productos.add(jugo);
        productos.add(alfajor);
        productos.add(chocolate);
        productos.add(caramelos);
        productos.add(arroz);
        productos.add(fideos);
        productos.add(aceite);
        productos.add(yerba);

        // ===== 3. USUARIOS =====
        Usuario lucia = new Usuario(1L, "Lucia", "Fernandez", "lucia@mail.com", "1155551234", "clave123", Rol.USUARIO);
        Usuario martin = new Usuario(2L, "Martin", "Lopez", "martin@mail.com", "1155555678", "admin456", Rol.ADMIN);

        // ===== 4. PEDIDOS  =====
        Pedido pedido1 = new Pedido(1L, LocalDate.of(2026, 9, 20), FormaPago.TARJETA);
        pedido1.addDetallePedido(2, cocaCola);
        pedido1.addDetallePedido(3, alfajor);

        Pedido pedido2 = new Pedido(2L, LocalDate.of(2026, 9, 22), FormaPago.EFECTIVO);
        pedido2.addDetallePedido(1, arroz);
        pedido2.addDetallePedido(2, fideos);
        pedido2.addDetallePedido(1, aceite);

        Pedido pedido3 = new Pedido(3L, LocalDate.of(2026, 9, 23), FormaPago.TRANSFERENCIA);
        pedido3.addDetallePedido(4, agua);
        pedido3.addDetallePedido(1, chocolate);
        pedido3.setEstado(Estado.CONFIRMADO);

        // Asignar pedidos a los usuarios
        lucia.addPedido(pedido1);
        lucia.addPedido(pedido3);
        martin.addPedido(pedido2);


        // ===== PUNTO 4: MOSTRAR POR CONSOLA =====

        // a) Un producto
        System.out.println("===== UN PRODUCTO =====");
        System.out.println(cocaCola);

        // b) Listado de productos cargados
        System.out.println();
        System.out.println("===== LISTADO DE PRODUCTOS =====");
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // c) Pedidos del usuario con más pedidos
        Set<Usuario> usuarios = new LinkedHashSet<>();
        usuarios.add(lucia);
        usuarios.add(martin);

        Usuario usuarioConMasPedidos = null;
        for (Usuario usuario : usuarios) {
            if (usuarioConMasPedidos == null
                    || usuario.getPedidos().size() > usuarioConMasPedidos.getPedidos().size()) {
                usuarioConMasPedidos = usuario;
            }
        }

        System.out.println();
        System.out.println("===== PEDIDOS DEL USUARIO CON MAS PEDIDOS =====");
        System.out.println(usuarioConMasPedidos);
        for (Pedido pedido : usuarioConMasPedidos.getPedidos()) {
            System.out.println(pedido);
        }

        // ===== PUNTO 5: PRUEBA DE EQUALS =====
        Producto cocaColaNueva = new Producto(11L, "Coca Cola 500ml", 1800.0, "Otra descripcion", 10, "otra.jpg", true);

        System.out.println();
        System.out.println("===== PRUEBA DE EQUALS =====");
        System.out.println("Producto nuevo: " + cocaColaNueva);
        System.out.println();

        for (Producto producto : productos) {
            boolean sonIguales = cocaColaNueva.equals(producto);
            System.out.println("Comparado con '" + producto.getNombre() + "' -> equals: " + sonIguales);
        }

        System.out.println();
        System.out.println("Mismo hashCode que la Coca Cola original? " + (cocaColaNueva.hashCode() == cocaCola.hashCode()));
        System.out.println("Es el mismo objeto en memoria (==)? " + (cocaColaNueva == cocaCola));
        System.out.println("La coleccion lo contiene (contains)? " + productos.contains(cocaColaNueva));

        boolean seAgrego = productos.add(cocaColaNueva);
        System.out.println("Se pudo agregar al Set? " + seAgrego);
        System.out.println("Cantidad de productos en el Set: " + productos.size());
    }
}