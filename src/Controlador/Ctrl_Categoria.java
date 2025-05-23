package Controlador;

import Modelo.Categoria;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ctrl_Categoria {
    private final List<Categoria> categorias = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public Ctrl_Categoria() {}

    public void cargarDesdeJson(Path ruta) throws IOException {
        String json = Files.readString(ruta);
        List<Categoria> lista = mapper.readValue(json, new TypeReference<List<Categoria>>() {});
        categorias.clear();
        categorias.addAll(lista);
    }

    public void guardarAJson(Path ruta) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categorias);
        Files.writeString(ruta, json);
    }

    public boolean agregar(Categoria c) {
        validarCategoria(c);
        if (buscarPorId(c.getId()).isPresent()) {
            return false;
        }
        categorias.add(c);
        return true;
    }

    public Optional<Categoria> buscarPorId(String id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public boolean actualizar(Categoria c) {
        Optional<Categoria> opt = buscarPorId(c.getId());
        if (opt.isEmpty()) {
            return false;
        }
        Categoria existente = opt.get();
        if (c.getNombre() != null && !c.getNombre().isBlank()) {
            existente.setNombre(c.getNombre());
        }
        return true;
    }

    public boolean eliminar(String id) {
        Optional<Categoria> opt = buscarPorId(id);
        if (opt.isPresent()) {
            categorias.remove(opt.get());
            return true;
        }
        return false;
    }

    public List<Categoria> listarTodos() {
        return new ArrayList<>(categorias);
    }

    public boolean existeCategoria(String id) {
        return buscarPorId(id).isPresent();
    }

    public boolean guardar(Categoria categoria) {
        return agregar(categoria);
    }

    public boolean actualizar(Categoria categoria, int idCategoria) {
        categoria.setId(String.valueOf(idCategoria));
        return actualizar(categoria);
    }

    public boolean eliminar(int idCategoria) {
        return eliminar(String.valueOf(idCategoria));
    }

    private void validarCategoria(Categoria c) {
        if (c.getId() == null || c.getId().isBlank()) {
            throw new IllegalArgumentException("El ID de la categoría no puede estar vacío.");
        }
        if (c.getNombre() == null || c.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
    }
}