package Controlador;

import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Ctrl_Usuario {

    private static final String RUTA_JSON = "src/Datos/usuarios.json";
    private ArrayList<Usuario> usuarios;

    public Ctrl_Usuario() {
        this.usuarios = cargarUsuariosDesdeJSON(); // Carga al crear el controlador
    }

    /** Carga los usuarios desde el JSON */
    public static ArrayList<Usuario> cargarUsuariosDesdeJSON() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (Reader reader = new FileReader(RUTA_JSON)) {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            lista = new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error al leer usuarios.json: " + e.getMessage());
        }
        return lista;
    }

    /** Guarda la lista actual al archivo JSON */
    public void guardarUsuariosEnJSON() {
        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            new Gson().toJson(this.usuarios, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios.json: " + e.getMessage());
        }
    }

    public boolean guardar(Usuario u) {
        validarUsuario(u);
        if (buscarPorUsuario(u.getUsuario()).isPresent()) {
            return false;
        }
        usuarios.add(u);
        guardarUsuariosEnJSON();
        return true;
    }

    public boolean actualizar(Usuario u) {
        Optional<Usuario> opt = buscarPorUsuario(u.getUsuario());
        if (opt.isEmpty()) return false;

        Usuario existente = opt.get();
        if (u.getNombre() != null && !u.getNombre().isBlank()) existente.setNombre(u.getNombre());
        if (u.getApellido() != null && !u.getApellido().isBlank()) existente.setApellido(u.getApellido());
        if (u.getTelefono() != null && !u.getTelefono().isBlank()) existente.setTelefono(u.getTelefono());
        if (u.getPassword() != null && !u.getPassword().isBlank()) existente.setPassword(u.getPassword());
        if (u.getEstado() != 0) existente.setEstado(u.getEstado());

        guardarUsuariosEnJSON();
        return true;
    }


    public Optional<Usuario> buscarPorUsuario(String usuario) {
        return usuarios.stream()
                .filter(u -> u.getUsuario().equals(usuario))
                .findFirst();
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public boolean existeUsuario(String usuario) {
        return buscarPorUsuario(usuario).isPresent();
    }

    public Usuario login(String usuario, String password) {
        Optional<Usuario> opt = buscarPorUsuario(usuario);
        return (opt.isPresent() && opt.get().getPassword().equals(password)) ? opt.get() : null;
    }

    private void validarUsuario(Usuario u) {
        if (u.getNombre() == null || u.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        if (u.getApellido() == null || u.getApellido().isBlank())
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        if (u.getUsuario() == null || u.getUsuario().isBlank())
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        if (u.getPassword() == null || u.getPassword().isBlank())
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        if (u.getTelefono() == null || !u.getTelefono().matches("\\d{7,15}"))
            throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
    }

    public boolean eliminar(int idUsuario) {
    Optional<Usuario> opt = usuarios.stream()
            .filter(u -> u.getIdUsuario() == idUsuario)
            .findFirst();
    
    if (opt.isPresent()) {
        usuarios.remove(opt.get());
        guardarUsuariosEnJSON(); // Asegúrate de tener este método implementado
        return true;
    }
    
    return false;
    }

    public boolean actualizar(Usuario usuario, int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
