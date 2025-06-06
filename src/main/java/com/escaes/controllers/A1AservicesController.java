package com.escaes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.escaes.models.Cliente;
import com.escaes.models.Membresia;
import com.escaes.models.Membresias_Prestaciones;
import com.escaes.models.ServicioLavado;
import com.escaes.repositories.ClienteRepository;
import com.escaes.repositories.MembresiaRepository;
import com.escaes.repositories.ServicioLavadoRepository;
import com.escaes.repositories.VehiculoRepository;
import com.escaes.repositories.membresia_prestaRepo;
import com.escaes.services.IMembresiaPrestacionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/clientes")
public class A1AservicesController {

        private ClienteRepository clRepo;

        private VehiculoRepository vRepository;

        private MembresiaRepository mRepository;

        private ServicioLavadoRepository sLavadoRepository;

        private IMembresiaPrestacionService membresiaService;

        private membresia_prestaRepo memPresRepo;

        @Autowired
        private JdbcTemplate jdbcTemplate;

        public A1AservicesController(ClienteRepository clRepo, VehiculoRepository vRepository,
                        MembresiaRepository mRepository, ServicioLavadoRepository sLavadoRepository,
                        IMembresiaPrestacionService membresiaService, membresia_prestaRepo memPresRepo) {
                this.clRepo = clRepo;
                this.vRepository = vRepository;
                this.mRepository = mRepository;
                this.sLavadoRepository = sLavadoRepository;
                this.membresiaService = membresiaService;
                this.memPresRepo=memPresRepo;
        }
        @GetMapping("/todos-servicios")
        public String getAllservices(Model model) {

                List<ServicioLavado>servicios=(List<ServicioLavado>)sLavadoRepository.findAll();

                model.addAttribute("servicios", servicios);

            return "servicio/todos";
        }
        @GetMapping("/todos-servicios/detalles/{id}")
        public String getMembresiaByService(@PathVariable("id")Long id,Model model) {
                List<Long> membresiasids=membresiaService.membresiasPorPrestaciones(id)
                .stream().map(Membresias_Prestaciones::getMembresiaId).toList();
                List<Membresia>membresia=(List<Membresia>)mRepository.findAllById(membresiasids);
               
                if(membresia!=null&&membresia.size()==1){

                        Membresia membresiaForClient=membresia.get(0);
                        AggregateReference<Membresia, Long> ref = AggregateReference.to(membresiaForClient.getId());
                        Cliente cliente = clRepo.findByMembresiaid(ref); 
                        model.addAttribute("cliente", cliente);
                }else{
                        throw new IllegalStateException("Se encuentran varias membresias asocidadas");
                }
                model.addAttribute("membresias", membresia);
            return"servicio/detalles";
        }
        
        

        @GetMapping("/{id}/membresias/{membresiaId}/servicios")
        public String getMethodName(@PathVariable("id") Long id, @PathVariable("membresiaId") Long membresiaId,
                        Model model) {

                List<Long> prestacionIds = membresiaService.obtenerPrestacionesPorMembresia(membresiaId)
                                .stream()
                                .map(Membresias_Prestaciones::getPrestacionId)
                                .toList();

                List<ServicioLavado> prestaciones = (List<ServicioLavado>) sLavadoRepository.findAllById(prestacionIds);

                model.addAttribute("servicios", prestaciones);
                model.addAttribute("membresiaId", membresiaId);
                model.addAttribute("cliente",
                                clRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

                return "servicio/lista";
        }

        @GetMapping("/{id}/membresias/{membresiaId}/servicios/nuevo")
        public String getAddServiceView(@PathVariable("id") Long id, @PathVariable("membresiaId") Long membresiaId,
                        Model model) {

                model.addAttribute("cliente",
                                clRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

                model.addAttribute("vehiculos", vRepository.findAllByClienteId(id));

                model.addAttribute("membresia",
                                mRepository.findById(membresiaId)
                                                .orElseThrow(() -> new RuntimeException("Membresia no encontrada")));

                model.addAttribute("servicio", new ServicioLavado());
                return "servicio/anadir";
        }

        @PostMapping("/{id}/membresias/{membresiaId}/servicios/nuevo")
        public String postMethodName(@PathVariable("id") Long id, @PathVariable("membresiaId") Long membresiaId,
                        ServicioLavado servicio) {

                servicio.setId(null);
                ServicioLavado saved = sLavadoRepository.save(servicio);
                membresiaService.vincularMembresiaConPrestacion(membresiaId, saved.getId());

                return "redirect:/clientes/" + id + "/membresias/" + membresiaId + "/servicios?success";
        }

        @GetMapping("/{id}/membresias/{membresiaId}/servicios/{servicioId}/eliminar")
        public String eliminarServicio(@PathVariable("servicioId") Long servicioId,
                        @PathVariable("membresiaId") Long membresiaId,
                        @PathVariable("id") Long clienteId) {

                if (!sLavadoRepository.existsById(servicioId)) {
                        throw new RuntimeException("Servicio no encontrado");
                }
                jdbcTemplate.update("DELETE FROM membresia_prestacion WHERE prestacion_id = ?", servicioId);
                sLavadoRepository.deleteById(servicioId); 
                return "redirect:/clientes/" + clienteId + "/membresias/"+membresiaId+"/servicios?deleted";
        }

}
