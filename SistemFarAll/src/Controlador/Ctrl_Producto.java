// Controlador/Ctrl_Producto.java
package Controlador;

import Modelo.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Controlador para gestionar Productos:
 * - CRUD en memoria
 * - Importación/Exportación JSON
 */
public class Ctrl_Producto {
    private final List<Producto> productos = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Producto() {}

    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Producto> lista = mapper.readValue(json, new TypeReference<List<Producto>>() {});
        productos.clear();
        productos.addAll(lista);
    }

    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productos);
        Files.writeString(ruta, json);
    }

    /**
     * Agrega un producto tras validar:
     * @throws IllegalArgumentException si falla la validación.
     */
    public boolean agregar(Producto p) {
        if (p.getCodigo() == null || p.getCodigo().isBlank()) {
            throw new IllegalArgumentException("El código del producto no puede estar vacío.");
        }
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (p.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (p.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        if (buscarPorCodigo(p.getCodigo()).isPresent()) {
            return false;
        }
        productos.add(p);
        return true;
    }

    /** Busca un producto por su código. */
    public Optional<Producto> buscarPorCodigo(String codigo) {
        return productos.stream()
                        .filter(p -> p.getCodigo().equals(codigo))
                        .findFirst();
    }

    /**
     * Actualiza un producto existente.
     * @return true si se actualizó; false si no existe.
     */
    public boolean actualizar(Producto p) {
        Optional<Producto> opt = buscarPorCodigo(p.getCodigo());
        if (opt.isEmpty()) return false;
        Producto ex = opt.get();
        if (p.getNombre() != null && !p.getNombre().isBlank()) {
            ex.setNombre(p.getNombre());
        }
        if (p.getPrecio() >= 0) {
            ex.setPrecio(p.getPrecio());
        }
        if (p.getStock() >= 0) {
            ex.setStock(p.getStock());
        }
        return true;
    }

    public boolean eliminar(String codigo) {
        Optional<Producto> opt = buscarPorCodigo(codigo);
        if (opt.isPresent()) {
            productos.remove(opt.get());
            return true;
        }
        return false;
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }
}
