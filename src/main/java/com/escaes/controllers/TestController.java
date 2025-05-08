package com.escaes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.escaes.repositories.ClienteRepository;
import com.escaes.repositories.MembresiaRepository;
import com.escaes.repositories.ServicioLavadoRepository;
import com.escaes.repositories.VehiculoRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private MembresiaRepository membreRepo;

    @Autowired
    private VehiculoRepository vRepository;

    @Autowired
    private ServicioLavadoRepository sLavadoRepository;


    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> clienteGet( @PathVariable Long id) {
        
        if(!clienteRepo.findById(id).isEmpty()){
            return ResponseEntity.ok(clienteRepo.findById(id));
        }
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Usuario no Existe")) ;
    }
    
    @GetMapping("/membresia/{id}")
    public ResponseEntity<?> membresiaGet(@PathVariable Long id) {
        if(!membreRepo.findById(id).isEmpty()){

            return ResponseEntity.ok(membreRepo.findById(id));
        }
        
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Mebresia no Existe")) ;
        
    }
    
    @GetMapping("/vehiculo/{placa}")
    public ResponseEntity<?> vehiculoGet(@PathVariable String placa) {
        if(!vRepository.findById(placa).isEmpty()){

            return ResponseEntity.ok(vRepository.findById(placa));
        }
        
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Vehiculo no Existe")) ;
        
    }
    @GetMapping("/servicios/{id}")
    public ResponseEntity<?> lavadoGet(@PathVariable Long id) {
        if(!sLavadoRepository.findById(id).isEmpty()){

            return ResponseEntity.ok(sLavadoRepository.findById(id));
        }
        
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Servicio no Existe")) ;
        
    }
    
}
