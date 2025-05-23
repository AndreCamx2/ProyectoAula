// Controlador/Ctrl_Cliente.java
package Controlador;

import Modelo.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ctrl_Cliente {
    private final List<Cliente> clientes = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Cliente() {}

    public void cargarDesdeJson(Path ruta) throws IOException {
        if (!Files.exists(ruta)) {
            clientes.clear();
            return;
        }
        String json = Files.readString(ruta);
        List<Cliente> lista = mapper.readValue(json, new TypeReference<List<Cliente>>() {});
        clientes.clear();
        clientes.addAll(lista);
    }

    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientes);
        Files.writeString(ruta, json);
    }

    public boolean agregar(Cliente c) {
        validarCliente(c);
        if (buscarPorId(c.getCedula()).isPresent()) {
            return false;
        }
        clientes.add(c);
        return true;
    }

    public Optional<Cliente> buscarPorId(String cedula) {
        return clientes.stream()
                .filter(c -> c.getCedula().equals(cedula))
                .findFirst();
    }

    public boolean actualizar(Cliente c) {
        Optional<Cliente> opt = buscarPorId(c.getCedula());
        if (opt.isEmpty()) {
            return false;
        }
        Cliente existente = opt.get();
        if (c.getNombre() != null && !c.getNombre().isBlank()) {
            existente.setNombre(c.getNombre());
        }
        if (c.getApellido() != null && !c.getApellido().isBlank()) {
            existente.setApellido(c.getApellido());
        }
        if (c.getDireccion() != null && !c.getDireccion().isBlank()) {
            existente.setDireccion(c.getDireccion());
        }
        if (c.getTelefono() != null) {
            if (!c.getTelefono().matches("\\d{7,15}")) {
                throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
            }
            existente.setTelefono(c.getTelefono());
        }
        return true;
    }

    public boolean eliminar(String cedula) {
        Optional<Cliente> opt = buscarPorId(cedula);
        if (opt.isPresent()) {
            clientes.remove(opt.get());
            return true;
        }
        return false;
    }

    public boolean eliminar(int idCliente) {
        return eliminar(String.valueOf(idCliente));
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public boolean existeCliente(String cedula) {
        return buscarPorId(cedula).isPresent();
    }

    public boolean guardar(Cliente cliente) {
        return agregar(cliente);
    }

    public boolean actualizar(Cliente cliente, int idCliente) {
        cliente.setCedula(String.valueOf(idCliente));
        return actualizar(cliente);
    }

    private void validarCliente(Cliente c) {
        if (c.getCedula() == null || c.getCedula().isBlank()) {
            throw new IllegalArgumentException("La cédula del cliente no puede estar vacía.");
        }
        if (c.getNombre() == null || c.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (c.getApellido() == null || c.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido del cliente no puede estar vacío.");
        }
        if (c.getDireccion() == null || c.getDireccion().isBlank()) {
            throw new IllegalArgumentException("La dirección del cliente no puede estar vacía.");
        }
        if (c.getTelefono() == null || !c.getTelefono().matches("\\d{7,15}")) {
            throw new IllegalArgumentException("El teléfono debe tener solo dígitos (7–15).");
        }
    }

    public Cliente obtenerClientePorId(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Cliente> listarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
