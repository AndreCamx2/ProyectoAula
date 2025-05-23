// Controlador/Ctrl_Venta.java
package Controlador;

import Modelo.Venta;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ctrl_Venta {
    private final List<Venta> ventas = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Venta() {}

    /** Carga la lista de ventas desde un archivo JSON. */
    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Venta> lista = mapper.readValue(json, new TypeReference<List<Venta>>() {});
        ventas.clear();
        ventas.addAll(lista);
    }

    /** Guarda la lista de ventas en un archivo JSON (pretty print). */
    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ventas);
        Files.writeString(ruta, json);
    }

    /**
     * Agrega una nueva venta tras validar sus datos.
     * @return true si se agregó; false si ya existía una con ese ID.
     * @throws IllegalArgumentException si algún campo no cumple la validación.
     */
    public boolean agregar(Venta v) {
        validarVenta(v);
        if (buscarPorId(v.getId()).isPresent()) {
            return false;
        }
        ventas.add(v);
        return true;
    }

    /** Busca una venta por su ID. */
    public Optional<Venta> buscarPorId(String id) {
        return ventas.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    /**
     * Actualiza los datos de una venta existente.
     * @return true si existía y se actualizó; false si no se encontró.
     * @throws IllegalArgumentException si alguna validación falla.
     */
    public boolean actualizar(Venta v) {
        Optional<Venta> opt = buscarPorId(v.getId());
        if (opt.isEmpty()) {
            return false;
        }
        Venta existente = opt.get();
        if (v.getFecha() != null) {
            existente.setFecha(v.getFecha());
        }
        if (v.getTotal() >= 0) {
            existente.setTotal(v.getTotal());
        }
        
        return true;
    }

    /** Elimina una venta por su ID. */
    public boolean eliminar(String id) {
        Optional<Venta> opt = buscarPorId(id);
        if (opt.isPresent()) {
            ventas.remove(opt.get());
            return true;
        }
        return false;
    }

    public boolean eliminar(int idVenta) {
        return eliminar(String.valueOf(idVenta));
    }

    /** Devuelve una copia de la lista de todas las ventas. */
    public List<Venta> listarTodos() {
        return new ArrayList<>(ventas);
    }

    public boolean existeVenta(String id) {
        return buscarPorId(id).isPresent();
    }

    public boolean guardar(Venta venta) {
        return agregar(venta);
    }

    public boolean actualizar(Venta venta, int idVenta) {
        venta.setId(String.valueOf(idVenta));
        return actualizar(venta);
    }

    /** Valida los campos obligatorios de una venta. */
    private void validarVenta(Venta v) {
        if (v.getId() == null || v.getId().isBlank()) {
            throw new IllegalArgumentException("El ID de la venta no puede estar vacío.");
        }
        if (v.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de la venta no puede estar vacía.");
        }
        if (v.getTotal() < 0) {
            throw new IllegalArgumentException("El total de la venta no puede ser negativo.");
        }
        
    }
}
