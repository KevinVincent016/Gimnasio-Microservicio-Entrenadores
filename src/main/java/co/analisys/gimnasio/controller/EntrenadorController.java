package co.analisys.gimnasio.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import co.analisys.gimnasio.model.Entrenador;
import co.analisys.gimnasio.service.EntrenadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/trainers")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @Operation(
        summary = "Agregar un nuevo entrenador",
        description = "Este endpoint permite registrar un nuevo entrenador en el sistema. " +
        "Debes proporcionar el nombre y la especialidad del entrenador. " +
        "Retorna la información del entrenador creado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador agregado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Entrenador agregarEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorService.agregarEntrenador(entrenador);
    }

    @Operation(
        summary = "Obtener todos los entrenadores",
        description = "Este endpoint recupera una lista completa de todos los entrenadores registrados en el sistema. " +
        "No requiere parámetros y retorna los detalles de cada entrenador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de entrenadores recuperada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public List<Entrenador> obtenerTodosEntrenadores() {
        return entrenadorService.obtenerTodosEntrenadores();
    }

    @Operation(
        summary = "Obtener un entrenador por ID",
        description = "Este endpoint permite recuperar la información de un entrenador específico utilizando su ID. " +
        "Debes proporcionar el ID del entrenador en la URL. Retorna los detalles del entrenador si se encuentra."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador recuperado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable Long id) {
        Entrenador entrenador = entrenadorService.obtenerEntrenadorPorId(id);
        if (entrenador != null) {
            return ResponseEntity.ok(entrenador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Actualizar un entrenador",
        description = "Este endpoint permite actualizar la información de un entrenador existente. " +
        "Debes proporcionar el ID del entrenador en la URL y los nuevos datos en el cuerpo de la solicitud. " +
        "Retorna la información actualizada del entrenador si se encuentra."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenador) {
        Entrenador actualizado = entrenadorService.actualizarEntrenador(id, entrenador);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Eliminar un entrenador",
        description = "Este endpoint permite eliminar un entrenador del sistema utilizando su ID. " +
        "Debes proporcionar el ID del entrenador en la URL. Retorna true si la eliminación fue exitosa."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrenador eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> eliminarEntrenador(@PathVariable Long id) {
        boolean eliminado = entrenadorService.eliminarEntrenador(id);
        if (eliminado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}