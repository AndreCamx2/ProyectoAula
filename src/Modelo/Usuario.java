// Modelo/Usuario.java
package Modelo;

/**
 * Representa un usuario del sistema.
 */
public class Usuario {
    private int idUsuario;              // ID único del usuario
    private String usuario;             // Nombre de usuario
    private String nombre;              // Nombre real
    private String apellido;            // Apellido del usuario
    private String telefono;            // Teléfono de contacto
    private String password;            // Contraseña
    private int estado;                 // 1 = activo, 0 = inactivo
    private String rol;                 // "ADMIN", "VENDEDOR", etc.

    public Usuario() {}

    /**
     * Constructor completo.
     */
    public Usuario(String usuario, String nombre, String apellido, String telefono, String password, int estado, String rol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                ", rol='" + rol + '\'' +
                '}';
    }
}

