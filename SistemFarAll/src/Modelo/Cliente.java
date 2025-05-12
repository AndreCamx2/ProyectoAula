// Modelo/Cliente.java
package Modelo;

/**
 * Representa un cliente en el sistema.
 */
public class Cliente {
    /** Identificador único del cliente (e.g. cédula) */
    private String id;
    /** Nombre completo */
    private String nombre;
    /** Teléfono (solo dígitos) */
    private String telefono;

    /** Constructor vacío necesario para deserialización JSON */
    public Cliente() {}

    /**
     * Constructor completo.
     * @param id       Cédula o identificador del cliente
     * @param nombre   Nombre completo
     * @param telefono Teléfono (7–15 dígitos)
     */
    public Cliente(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // --- Getters y setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Cliente{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }

    public void setApellido(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setCedula(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDireccion(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setEstado(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
