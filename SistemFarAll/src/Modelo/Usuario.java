// Modelo/Usuario.java
package Modelo;

/**
 * Representa un usuario del sistema.
 */
public class Usuario {
    
    private String nombre;
    private String usuario;  // clave única (username)
    private String clave;    // password
    private String rol;      // e.g. "ADMIN", "USER"

    public Usuario() {}

    /**
     * Constructor completo.
     * @param nombre  Nombre real
     * @param usuario Nombre de usuario único
     * @param clave   Contraseña (min. 8 caracteres)
     * @param rol      Rol asignado
     */
    public Usuario(String nombre, String usuario, String clave, String rol) {
        this.nombre  = nombre;
        this.usuario = usuario;
        this.clave   = clave;
        this.rol     = rol;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario{" +
               "nombre='" + nombre + '\'' +
               ", usuario='" + usuario + '\'' +
               ", rol='" + rol + '\'' +
               '}';
    }

    public void setPassword(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setApellido(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTelefono(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setEstado(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
