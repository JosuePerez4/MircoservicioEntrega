package com.taller.entrega;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*") // ¡Súper importante para que React no marque error!
public class EntregaController {

    @Autowired
    private EnvioRepository envioRepository;

    @GetMapping
    public List<Envio> obtenerEnvios() {
        return envioRepository.findAll();
    }
}