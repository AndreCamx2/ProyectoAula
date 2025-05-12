// Controlador/Ctrl_Usuario.java
package Controlador;

import Modelo.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Controlador para gestionar Usuarios.
 */
public class Ctrl_Usuario {
    private final List<Usuario> usuarios = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Usuario() {}

    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Usuario> lista = mapper.readValue(json, new TypeReference<List<Usuario>>() {});
        usuarios.clear();
        usuarios.addAll(lista);
    }

    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usuarios);
        Files.writeString(ruta, json);
    }

    /**
     * Agrega un usuario tras validar:
     * - username único
     * - password ≥ 8 caracteres
     */
    public boolean agregar(Usuario u) {
        if (u.getNombre() == null || u.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (u.getUsuario() == null || u.getUsuario().isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (u.getClave() == null || u.getClave().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        if (u.getRol() == null || u.getRol().isBlank()) {
            throw new IllegalArgumentException("El rol no puede estar vacío.");
        }
        if (buscarPorUsername(u.getUsuario()).isPresent()) {
            return false;
        }
        usuarios.add(u);
        return true;
    }

    public Optional<Usuario> buscarPorUsername(String usuario) {
        return usuarios.stream()
                       .filter(u -> u.getUsuario().equals(usuario))
                       .findFirst();
    }

    public boolean actualizar(Usuario u) {
        Optional<Usuario> opt = buscarPorUsername(u.getUsuario());
        if (opt.isEmpty()) return false;
        Usuario ex = opt.get();
        if (u.getNombre() != null && !u.getNombre().isBlank()) {
            ex.setNombre(u.getNombre());
        }
        if (u.getClave() != null && u.getClave().length() >= 8) {
            ex.setClave(u.getClave());
        }
        if (u.getRol() != null && !u.getRol().isBlank()) {
            ex.setRol(u.getRol());
        }
        return true;
    }

    public boolean eliminar(String usuario) {
        Optional<Usuario> opt =buscarPorUsername(usuario);
        if (opt.isPresent()) {
            usuarios.remove(opt.get());
            return true;
        }
        return false;
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Autentica al usuario. Si usuario+clave coinciden, devuelve
     * el objeto Usuario completo (con rol), o null si falla.
     */
    public Usuario login(Usuario u) {
        for (Usuario x : usuarios) {
            if (x.getUsuario().equals(u.getUsuario()) &&
                x.getClave().equals(u.getClave())) {
                return x;
            }
        }
        return null;
    }

}