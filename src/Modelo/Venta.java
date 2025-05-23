// Modelo/Venta.java
package Modelo;

import java.util.Date;

/**
 * Representa una venta realizada.
 */
public class Venta {
    private String id;           // ID único de la venta
    private Date fecha;         // Fecha y hora de la venta
    private double total;       // Total de la venta

    public Venta() {}

    public Venta(String id, Date fecha, double total) {
        this.id    = id;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Venta{" +
               "id='" + id + '\'' +
               ", fecha=" + fecha +
               ", total=" + total +
               '}';
    }
}
