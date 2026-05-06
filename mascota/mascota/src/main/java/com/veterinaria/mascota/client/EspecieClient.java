package com.veterinaria.mascota.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-especie", url = "${ms.especies.url}")
public interface EspecieClient {
    //Las anotaciones de comunicacion es como si estuvieramos copiando el controller del ms-especies
    @GetMapping("/api/especies/{id}")
    String obtenerId(@PathVariable Long id);

}
