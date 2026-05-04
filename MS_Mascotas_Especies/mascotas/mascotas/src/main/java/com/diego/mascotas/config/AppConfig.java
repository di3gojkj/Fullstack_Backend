package com.diego.mascotas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//Configuracion para la comunicacion de microservicios mediante webClient
@Configuration

public class AppConfig {
    @Value("${ms.especies.url}")
    private String especieUrl;

    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl(especieUrl).build();
    }

}
