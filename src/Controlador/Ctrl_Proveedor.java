// Controlador/Ctrl_Proveedor.java
package Controlador;

import Modelo.Proveedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ctrl_Proveedor {
    private final List<Proveedor> proveedores = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Proveedor() {}

    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Proveedor> lista = mapper.readValue(json, new TypeReference<List<Proveedor>>() {});
        proveedores.clear();
        proveedores.addAll(lista);
    }

    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(proveedores);
        Files.writeString(ruta, json);
    }

    public boolean agregar(Proveedor p) {
        validarProveedor(p);
        if (buscarPorId(p.getId()).isPresent()) {
            return false;
        }
        proveedores.add(p);
        return true;
    }

    public Optional<Proveedor> buscarPorId(String id) {
        return proveedores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public boolean actualizar(Proveedor p) {
        Optional<Proveedor> opt = buscarPorId(p.getId());
        if (opt.isEmpty()) {
            return false;
        }
        Proveedor existente = opt.get();
        if (p.getNombre() != null && !p.getNombre().isBlank()) {
            existente.setNombre(p.getNombre());
        }
        if (p.getTelefono() != null && !p.getTelefono().isBlank()) {
            if (!p.getTelefono().matches("\\d{7,15}")) {
                throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
            }
            existente.setTelefono(p.getTelefono());
        }
        return true;
    }

    public boolean eliminar(String id) {
        Optional<Proveedor> opt = buscarPorId(id);
        if (opt.isPresent()) {
            proveedores.remove(opt.get());
            return true;
        }
        return false;
    }

    public boolean eliminar(int idProveedor) {
        return eliminar(String.valueOf(idProveedor));
    }

    public List<Proveedor> listarTodos() {
        return new ArrayList<>(proveedores);
    }

    public boolean existeProveedor(String id) {
        return buscarPorId(id).isPresent();
    }

    public boolean guardar(Proveedor proveedor) {
        return agregar(proveedor);
    }

    public boolean actualizar(Proveedor proveedor, int idProveedor) {
        proveedor.setId(String.valueOf(idProveedor));
        return actualizar(proveedor);
    }

    private void validarProveedor(Proveedor p) {
        if (p.getId() == null || p.getId().isBlank()) {
            throw new IllegalArgumentException("El ID del proveedor no puede estar vacío.");
        }
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del proveedor no puede estar vacío.");
        }
        if (p.getTelefono() == null || !p.getTelefono().matches("\\d{7,15}")) {
            throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
        }
    }
}
