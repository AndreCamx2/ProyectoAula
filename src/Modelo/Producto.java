// Modelo/Producto.java
package Modelo;

/**
 * Representa un producto en el sistema de ventas.
 */
public class Producto {

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public enum EstadoProducto {
        ACTIVO,
        INACTIVO,
        AGOTADO
    }

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int cantidad;
    private int porcentajeIva;
    private int idCategoria;
    private EstadoProducto estado = EstadoProducto.ACTIVO;

    // --- Constructores ---
    public Producto() {}

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.estado = EstadoProducto.ACTIVO;
        this.descripcion = "";
        this.cantidad = 0;
        this.porcentajeIva = 0;
        this.idCategoria = 0;
    }

    // --- Getters y setters básicos ---
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // --- Métodos adicionales ---
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getPorcentajeIva() { return porcentajeIva; }
    public void setPorcentajeIva(int porcentajeIva) { this.porcentajeIva = porcentajeIva; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public EstadoProducto getEstado() { return estado; }
    public void setEstado(EstadoProducto estado) { this.estado = estado; }

    /**
     * Establece el estado mediante un valor entero:
     * 0 = INACTIVO, 1 = ACTIVO, 2 = AGOTADO
     */
    public void setEstado(int i) {
        switch (i) {
            case 0:
                this.estado = EstadoProducto.INACTIVO;
                break;
            case 1:
                this.estado = EstadoProducto.ACTIVO;
                break;
            case 2:
                this.estado = EstadoProducto.AGOTADO;
                break;
            default:
                throw new IllegalArgumentException("Estado no válido: " + i);
        }
    }

    @Override
    public String toString() {
        return "Producto{" +
               "codigo='" + codigo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", precio=" + precio +
               ", stock=" + stock +
               ", cantidad=" + cantidad +
               ", porcentajeIva=" + porcentajeIva +
               ", idCategoria=" + idCategoria +
               ", estado=" + estado +
               '}';
    }
}
