# TP ToString - Colecciones

**Programación III** · Tecnicatura Universitaria en Programación a Distancia (UTN)
**Alumna:** Elisabeth Cordero

## Descripción

Implementación en Java de un sistema de pedidos a partir de un diagrama UML. El objetivo es practicar:

- Herencia a partir de una clase abstracta (`Base`) e implementación de una interfaz (`Calculable`).
- Relaciones entre clases usando colecciones de tipo `Set`.
- Sobrescritura de `toString()`, `equals()` y `hashCode()` en cada clase, respetando el contrato entre `equals` y `hashCode`.

## Estructura del proyecto

```
src/main/java/com/tup/programacion3/
├── entities/     Base, Usuario, Pedido, DetallePedido, Producto, Categoria
├── enums/        Estado, FormaPago, Rol
├── interfaces/   Calculable
└── Main.java
```

## Modelo

| Clase | Descripción |
|---|---|
| `Base` | Clase abstracta con `id`, `eliminado` y `createdAt`. Todas las entidades heredan de ella. |
| `Usuario` | Tiene un `Set<Pedido>` (relación 1..m). |
| `Pedido` | Implementa `Calculable`. Tiene un `Set<DetallePedido>` (composición 1..m) y calcula su total sumando los subtotales. |
| `DetallePedido` | Renglón de un pedido: cantidad, subtotal y el `Producto` al que corresponde. |
| `Producto` | Datos del producto: nombre, precio, descripción, stock, imagen y disponibilidad. |
| `Categoria` | Agrupa productos en un `Set<Producto>` (agregación 1..m). |

### Criterio de igualdad (`equals` / `hashCode`)

| Clase | Se considera igual si tiene el mismo... |
|---|---|
| `Usuario` | mail |
| `Pedido` | id |
| `DetallePedido` | producto |
| `Producto` | nombre |
| `Categoria` | nombre |

Los campos usados en `equals`/`hashCode` no tienen setter, para que un objeto no cambie su hash mientras está guardado en un `Set`.

## Ejecución

- **IntelliJ IDEA:** abrir la carpeta como proyecto Gradle y ejecutar `Main`.
- **Consola:** `gradlew.bat run` (Windows) o `./gradlew run` (Linux/Mac).

## Qué muestra el programa

1. Un producto, usando su `toString()`.
2. El listado de los 10 productos cargados.
3. Los pedidos del usuario que más pedidos tiene (se calcula recorriendo los usuarios).
4. La prueba de `equals`: se crea un producto nuevo con el mismo nombre que uno existente y se compara contra toda la colección. Se muestra además que tiene el mismo `hashCode`, que `==` da `false` (son objetos distintos) y que el `Set` no permite agregarlo porque lo considera repetido.
