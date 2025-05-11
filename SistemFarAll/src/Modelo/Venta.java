// Modelo/Venta.java
package Modelo;

import java.time.LocalDateTime;

/**
 * Representa una venta realizada.
 */
public class Venta {
    private String id;           // ID único de la venta
    private String clienteId;    // ID del cliente
    private String productoCodigo; // Código del producto
    private int cantidad;        // Cantidad vendida (>0)
    private LocalDateTime fecha; // Fecha y hora de la venta

    public Venta() {}

    public Venta(String id, String clienteId, String productoCodigo, int cantidad, LocalDateTime fecha) {
        this.id             = id;
        this.clienteId      = clienteId;
        this.productoCodigo = productoCodigo;
        this.cantidad       = cantidad;
        this.fecha          = fecha;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getProductoCodigo() { return productoCodigo; }
    public void setProductoCodigo(String productoCodigo) { this.productoCodigo = productoCodigo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return "Venta{" +
               "id='" + id + '\'' +
               ", clienteId='" + clienteId + '\'' +
               ", productoCodigo='" + productoCodigo + '\'' +
               ", cantidad=" + cantidad +
               ", fecha=" + fecha +
               '}';
    }
}
