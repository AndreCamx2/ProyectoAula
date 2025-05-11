// Controlador/Ctrl_Venta.java
package Controlador;

import Modelo.Venta;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Controlador para gestionar Ventas:
 * - CRUD en memoria
 * - Importación/Exportación JSON con LocalDateTime
 */
public class Ctrl_Venta {
    private final List<Venta> ventas = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule()); // Soporte para LocalDateTime

    public Ctrl_Venta() {}

    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Venta> lista = mapper.readValue(json, new TypeReference<List<Venta>>() {});
        ventas.clear();
        ventas.addAll(lista);
    }

    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ventas);
        Files.writeString(ruta, json);
    }

    /**
     * Agrega una venta tras validar:
     * - ID único
     * - Cantidad > 0
     */
    public boolean agregar(Venta v) {
        if (v.getId() == null || v.getId().isBlank()) {
            throw new IllegalArgumentException("El ID de la venta no puede estar vacío.");
        }
        if (buscarPorId(v.getId()).isPresent()) {
            return false;
        }
        if (v.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad de la venta debe ser mayor que 0.");
        }
        if (v.getFecha() == null) {
            throw new IllegalArgumentException("Debe proporcionarse la fecha de la venta.");
        }
        ventas.add(v);
        return true;
    }

    public Optional<Venta> buscarPorId(String id) {
        return ventas.stream()
                     .filter(v -> v.getId().equals(id))
                     .findFirst();
    }

    public boolean actualizar(Venta v) {
        Optional<Venta> opt = buscarPorId(v.getId());
        if (opt.isEmpty()) return false;
        Venta ex = opt.get();
        if (v.getCantidad() > 0) {
            ex.setCantidad(v.getCantidad());
        }
        if (v.getFecha() != null) {
            ex.setFecha(v.getFecha());
        }
        return true;
    }

    public boolean eliminar(String id) {
        Optional<Venta> opt = buscarPorId(id);
        if (opt.isPresent()) {
            ventas.remove(opt.get());
            return true;
        }
        return false;
    }

    public List<Venta> listarTodos() {
        return new ArrayList<>(ventas);
    }
}
