// Modelo/Cliente.java
package Modelo;

/**
 * Representa un cliente en el sistema.
 */
public class Cliente {

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // --- Enum de estado del cliente ---
    public enum EstadoCliente {
        ACTIVO,
        INACTIVO,
        SUSPENDIDO
    }

    /** Identificador único del cliente (e.g. cédula) */
    private String cedula;
    /** Nombre completo */
    private String nombre;
    /** Apellido */
    private String apellido;
    /** Dirección */
    private String direccion;
    /** Teléfono (solo dígitos) */
    private String telefono;
    /** Estado del cliente */
    private EstadoCliente estado = EstadoCliente.ACTIVO; // Por defecto activo

    /** Constructor vacío necesario para deserialización JSON */
    public Cliente() {}

    /**
     * Constructor completo.
     * @param cedula   Cédula o identificador del cliente
     * @param nombre    Nombre
     * @param apellido  Apellido
     * @param direccion Dirección
     * @param telefono  Teléfono (7–15 dígitos)
     */
    public Cliente(String cedula, String nombre, String apellido, String direccion, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = EstadoCliente.ACTIVO;
    }

    // --- Getters y setters ---
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public EstadoCliente getEstado() { return estado; }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    /**
     * Establece el estado usando un número entero:
     * 0 = INACTIVO, 1 = ACTIVO, 2 = SUSPENDIDO
     */
    public void setEstado(int i) {
        switch (i) {
            case 0:
                this.estado = EstadoCliente.INACTIVO;
                break;
            case 1:
                this.estado = EstadoCliente.ACTIVO;
                break;
            case 2:
                this.estado = EstadoCliente.SUSPENDIDO;
                break;
            default:
                throw new IllegalArgumentException("Estado no válido: " + i);
        }
    }

    /**
     * Limpia los datos del cliente.
     */
    public static void clear() {
        // Si quieres que borre datos de un cliente, no debería ser static.
        // Aquí una versión para instancias:
        throw new UnsupportedOperationException("Este método debe llamarse desde una instancia, no es static.");
    }

    /**
     * Limpia los datos de esta instancia del cliente.
     */
    public void clearData() {
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.telefono = "";
        this.estado = EstadoCliente.ACTIVO;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "cedula='" + cedula + '\'' +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", direccion='" + direccion + '\'' +
               ", telefono='" + telefono + '\'' +
               ", estado=" + estado +
               '}';
    }
}

