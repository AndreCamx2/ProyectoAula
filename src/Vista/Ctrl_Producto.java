
package Controlador;

import Modelo.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

    public boolean agregar(Producto p) {
        validarProducto(p);
        if (buscarPorCodigo(p.getCodigo()).isPresent()) {
            return false;
        }
        productos.add(p);
        return true;
    }

    public Optional<Producto> buscarPorCodigo(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();
    }

    public boolean actualizar(Producto p) {
        Optional<Producto> opt = buscarPorCodigo(p.getCodigo());
        if (opt.isEmpty()) {
            return false;
        }
        Producto existente = opt.get();
        if (p.getNombre() != null && !p.getNombre().isBlank()) {
            existente.setNombre(p.getNombre());
        }
        if (p.getPrecio() >= 0) {
            existente.setPrecio(p.getPrecio());
        }
        if (p.getStock() >= 0) {
            existente.setStock(p.getStock());
        }
        return true;
    }

    public boolean actualizar(Producto producto, int idProducto) {
        producto.setCodigo(String.valueOf(idProducto));
        return actualizar(producto);
    }

    public boolean actualizarStock(Producto producto, int idProducto) {
        Optional<Producto> opt = buscarPorCodigo(String.valueOf(idProducto));
        if (opt.isEmpty()) {
            return false;
        }
        Producto existente = opt.get();
        int nuevoStock = existente.getStock() + producto.getCantidad();
        existente.setStock(nuevoStock);
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

    public boolean eliminar(int idProducto) {
        return eliminar(String.valueOf(idProducto));
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    public boolean existeProducto(String codigo) {
        return buscarPorCodigo(codigo).isPresent();
    }

    public boolean guardar(Producto producto) {
        return agregar(producto);
    }

    private void validarProducto(Producto p) {
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
    }
}
