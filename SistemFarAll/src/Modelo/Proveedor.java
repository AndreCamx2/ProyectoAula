// Modelo/Proveedor.java
package Modelo;

/**
 * Representa un proveedor.
 */
public class Proveedor {
    private String id;       // NIT o identificador único
    private String nombre;
    private String telefono; // Solo dígitos

    public Proveedor() {}

    public Proveedor(String id, String nombre, String telefono) {
        this.id       = id;
        this.nombre   = nombre;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Proveedor{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }

    public void set_nombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
