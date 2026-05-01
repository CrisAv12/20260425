# Prueba Quarkus

La aplicación envía y consume datos con el siguiente endpoint (considerando que el servicio se ejecuta en el puerto 8080 y Kafka se ejecuta en 9092):

http://localhost:8080/envioSolicitud

La aplicación hace un request automático cada 2 minutos, gracias al siguiente código:

```java
//Simulacion de comportamiento automatico cada 2 minutos o 120 segundos
@Scheduled(fixedRate = 120000)
public void envioAutomatico(){
    kafkaProducer.recibirEnviarDatos();
}
