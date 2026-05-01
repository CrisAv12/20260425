package com.avalos.examen_kafka.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "terremoto-topic",groupId = "grupoTerremoto2")
    public void consumirMensaje(String mensaje){
        System.out.println("Mensaje recibido desde KafkaProducer: "+mensaje);
    }
}
