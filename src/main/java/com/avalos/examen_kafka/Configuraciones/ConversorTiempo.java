package com.avalos.examen_kafka.Configuraciones;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ConversorTiempo {

    //metodo para convertir en fecha legible el time obtenido

    public static String convertirFecha(Long tiempos){
        //Long tiempo=Long.parseLong(tiempos);
        return ZonedDateTime
                .ofInstant(Instant.ofEpochMilli(tiempos), ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM 'de' yyyy ' a horas ' HH:mm:ss 'UTC'", new Locale("es", "ES")));
    }

}
