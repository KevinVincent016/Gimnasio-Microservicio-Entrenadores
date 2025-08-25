package co.analisys.gimnasio.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import co.analisys.gimnasio.model.Entrenador;
import co.analisys.gimnasio.service.EntrenadorService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/trainers")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Entrenador agregarEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorService.agregarEntrenador(entrenador);
    }

    @GetMapping
    public List<Entrenador> obtenerTodosEntrenadores() {
        return entrenadorService.obtenerTodosEntrenadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable Long id) {
        Entrenador entrenador = entrenadorService.obtenerEntrenadorPorId(id);
        if (entrenador != null) {
            return ResponseEntity.ok(entrenador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenador) {
        Entrenador actualizado = entrenadorService.actualizarEntrenador(id, entrenador);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarEntrenador(@PathVariable Long id) {
        boolean eliminado = entrenadorService.eliminarEntrenador(id);
        if (eliminado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<Object> obtenerClasesDeEntrenador(@PathVariable Long id) {
        String url = "http://localhost:8082/api/classes?entrenadorId=" + id;
        Object clases = restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok(clases);
    }
}
