package co.analisys.gimnasio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.analisys.gimnasio.repository.EntrenadorRepository;
import co.analisys.gimnasio.model.Entrenador;
import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public Entrenador agregarEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public List<Entrenador> obtenerTodosEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public Entrenador obtenerEntrenadorPorId(Long id) {
        return entrenadorRepository.findById(id).orElse(null);
    }

    public Entrenador actualizarEntrenador(Long id, Entrenador entrenadorActualizado) {
        return entrenadorRepository.findById(id).map(entrenador -> {
            entrenador.setNombre(entrenadorActualizado.getNombre());
            entrenador.setEspecialidad(entrenadorActualizado.getEspecialidad());
            return entrenadorRepository.save(entrenador);
        }).orElse(null);
    }

    public boolean eliminarEntrenador(Long id) {
        if (entrenadorRepository.existsById(id)) {
            entrenadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
