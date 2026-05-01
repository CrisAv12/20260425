package com.avalos.examen_kafka.Kafka;

import com.avalos.examen_kafka.Modelos.Terremotos;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.avalos.examen_kafka.Configuraciones.ConversorTiempo.convertirFecha;

@Service
public class KafkaProducer {
    private final String API_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void recibirEnviarDatos(){
        RestTemplate restTemplate=new RestTemplate();

        Terremotos terremotos=restTemplate.getForObject(API_URL, Terremotos.class);

        if (terremotos != null && terremotos.getFeatures() != null){
            //recorre los terremotos y los envia a kafka
            terremotos.getFeatures().forEach(
                    item ->{
                        String mensaje="Terremoto de intensidad "+item.getProperties().getMag()+
                                " localizado a "+item.getProperties().getPlace()+
                                " que tuvo lugar el "+convertirFecha(item.getProperties().getTime())
                                ;
                        kafkaTemplate.send("terremoto-topic",mensaje);
                    });
        }
    }


}
