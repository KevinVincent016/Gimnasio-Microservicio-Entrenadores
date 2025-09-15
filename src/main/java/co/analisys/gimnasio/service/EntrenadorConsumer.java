package co.analisys.gimnasio.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import co.analisys.gimnasio.dto.ClaseDTO;

public class EntrenadorConsumer {
    
    /*
    @Autowired
    private EntrenadorService entrenadorService;
    */

    @RabbitListener(queues = "clases.events.queue")
    public void handleClaseCreated(ClaseDTO clase) {
        // Lógica para manejar el evento de clase creada
        System.out.println("Nueva clase creada: " + clase);
    }
}
