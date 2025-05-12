// Controlador/Ctrl_Cliente.java
package Controlador;

import Modelo.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Controlador para gestionar Clientes:
 * - CRUD en memoria
 * - Importación/Exportación JSON
 */
public class Ctrl_Cliente {
    private final List<Cliente> clientes = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Cliente() { /* Mapper ya inicializado */ }

    /** Carga la lista de clientes desde un archivo JSON. */
    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Cliente> lista = mapper.readValue(json, new TypeReference<List<Cliente>>() {});
        clientes.clear();
        clientes.addAll(lista);
    }

    /** Guarda la lista de clientes en un archivo JSON (pretty print). */
    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientes);
        Files.writeString(ruta, json);
    }

    /**
     * Agrega un nuevo cliente tras validar sus datos.
     * @return true si se agregó; false si ya existía uno con ese ID.
     * @throws IllegalArgumentException si algún campo no cumple la validación.
     */
    public boolean agregar(Cliente c) {
        // Validaciones básicas
        if (c.getId() == null || c.getId().isBlank()) {
            throw new IllegalArgumentException("El ID del cliente no puede estar vacío.");
        }
        if (c.getNombre() == null || c.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (c.getTelefono() == null || !c.getTelefono().matches("\\d{7,15}")) {
            throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
        }
        // Verificar duplicado
        if (buscarPorId(c.getId()).isPresent()) {
            return false;
        }
        clientes.add(c);
        return true;
    }

    /** Busca un cliente por su ID. */
    public Optional<Cliente> buscarPorId(String id) {
        return clientes.stream()
                       .filter(c -> c.getId().equals(id))
                       .findFirst();
    }

    /**
     * Actualiza los datos de un cliente existente.
     * @return true si existía y se actualizó; false si no se encontró.
     * @throws IllegalArgumentException si alguna validación falla.
     */
    public boolean actualizar(Cliente c) {
        Optional<Cliente> opt = buscarPorId(c.getId());
        if (opt.isEmpty()) {
            return false;
        }
        Cliente existente = opt.get();
        // Solo actualizamos los campos no nulos
        if (c.getNombre() != null && !c.getNombre().isBlank()) {
            existente.setNombre(c.getNombre());
        }
        if (c.getTelefono() != null) {
            if (!c.getTelefono().matches("\\d{7,15}")) {
                throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
            }
            existente.setTelefono(c.getTelefono());
        }
        return true;
    }

    /** Elimina un cliente por su ID. */
    public boolean eliminar(String id) {
        Optional<Cliente> opt = buscarPorId(id);
        if (opt.isPresent()) {
            clientes.remove(opt.get());
            return true;
        }
        return false;
    }

    /** Devuelve una copia de la lista de todos los clientes. */
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public boolean existeCliente(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean guardar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean actualizar(Cliente cliente, int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean eliminar(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
