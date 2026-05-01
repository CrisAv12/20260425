package com.avalos.examen_kafka.Controladores;

import com.avalos.examen_kafka.Kafka.KafkaProducer;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerremotosController {

    private final KafkaProducer kafkaProducer;

    public TerremotosController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("envioSolicitud")
    public String envioSolicitud(){
        kafkaProducer.recibirEnviarDatos();
        return "Datos enviados por solicitud manual";

    }

    //Simulacion de comportamiento automatico cada 2 minutos o 120 segundos
    @Scheduled(fixedRate = 120000)
    public void envioAutomatico(){
        kafkaProducer.recibirEnviarDatos();
    }

}
