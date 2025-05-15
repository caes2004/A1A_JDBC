package com.escaes.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.escaes.models.Membresias_Prestaciones;
import com.escaes.models.ServicioLavado;
import com.escaes.repositories.ClienteRepository;
import com.escaes.repositories.MembresiaRepository;
import com.escaes.repositories.ServicioLavadoRepository;
import com.escaes.repositories.VehiculoRepository;
import com.escaes.services.IMembresiaPrestacionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clientes")
public class A1AservicesController {

    private ClienteRepository clRepo;

    private VehiculoRepository vRepository;

    private MembresiaRepository mRepository;

    private ServicioLavadoRepository sLavadoRepository;

    private IMembresiaPrestacionService membresiaService;

    public A1AservicesController(ClienteRepository clRepo, VehiculoRepository vRepository,
            MembresiaRepository mRepository, ServicioLavadoRepository sLavadoRepository,
            IMembresiaPrestacionService membresiaService) {
        this.clRepo = clRepo;
        this.vRepository = vRepository;
        this.mRepository = mRepository;
        this.sLavadoRepository = sLavadoRepository;
        this.membresiaService = membresiaService;
    }

    @GetMapping("/{id}/membresias/{membresiaId}/servicios")
    public String getMethodName(@PathVariable("id") Long id, @PathVariable("membresiaId") Long membresiaId,
            Model model) {

        List<Long> prestacionIds = membresiaService.obtenerPrestacionesPorMembresia(membresiaId)
                .stream()
                .map(Membresias_Prestaciones::getPrestacion_id)
                .toList();

        List<ServicioLavado> prestaciones = (List<ServicioLavado>) sLavadoRepository.findAllById(prestacionIds);

        model.addAttribute("servicios", prestaciones);
        model.addAttribute("cliente",
                clRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        return "servicio/lista";
    }

    @GetMapping("/{id}/membresias/{membresiaId}/servicios/nuevo")
    public String getAddServiceView(@PathVariable("id") Long id, @PathVariable("membresiaId") Long membresiaId,
            Model model) {

        model.addAttribute("cliente",
                clRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        model.addAttribute("membresia",
                mRepository.findById(membresiaId).orElseThrow(() -> new RuntimeException("Membresia no encontrada")));

        model.addAttribute("servicio", new ServicioLavado());        
        return "servicio/anadir";
    }

}
