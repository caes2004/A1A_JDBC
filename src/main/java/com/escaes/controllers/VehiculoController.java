package com.escaes.controllers;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.escaes.models.Cliente;
import com.escaes.models.Vehiculo;
import com.escaes.models.daos.VehiculoJdbcDao;
import com.escaes.repositories.ClienteRepository;
import com.escaes.repositories.VehiculoRepository;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
@RequestMapping("/clientes")
public class VehiculoController {

    private VehiculoRepository vRepository;
    private ClienteRepository clRepository;
    private VehiculoJdbcDao vJdbcDao;

   

    public VehiculoController(VehiculoRepository vRepository, ClienteRepository clRepository,VehiculoJdbcDao vJdbcDao) {
        this.vRepository = vRepository;
        this.clRepository = clRepository;
        this.vJdbcDao=vJdbcDao;
    }



    @GetMapping("/{id}/vehiculos")
    public String getAllVehiculosView(@PathVariable("id")Long id, Model model) {
        Cliente cliente=clRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        model.addAttribute("cliente", cliente);
        model.addAttribute("vehiculos", vRepository.findAllByClienteId(id));

        return "vehiculos/lista";
    }
    
    @GetMapping("/{id}/vehiculos/anadir")
    public String getAddVehiculeView(@PathVariable("id")Long id ,Model model) {
        Cliente cliente=clRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("cliente",cliente);
        return "vehiculos/anadir";
    }
    @PostMapping("/{id}/vehiculos/anadir")
    public String postAddVehicule(@PathVariable("id")Long id, Vehiculo vehiculo) {
       
        vehiculo.setClienteId(AggregateReference.to(id));
        vJdbcDao.insertarVehiculo(vehiculo);
        return "redirect:/clientes/{id}/vehiculos";
    }
    
    @GetMapping("{id}/{placa}/eliminar")
    public String getDeleteMethod(@PathVariable("id")Long id,@PathVariable("placa")String placa) {
            Vehiculo v = vRepository.findByPlacaVehiculo(placa);
            if (v != null) {
                vRepository.delete(v);
            }
        return "redirect:/clientes/"+id+"/vehiculos";
    }

    @GetMapping("{id}/{placa}/editar")
    public String getEditVehiculeView(@PathVariable("id")Long id, @PathVariable("placa")String placa, Model model) {

        model.addAttribute("cliente", clRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado")));
        model.addAttribute("placa", vRepository.findByPlacaVehiculo(placa));

        return "vehiculos/editar";
    }

    @PostMapping("{id}/{placa}/editar")
    public String postEditVehicule(@PathVariable("id")Long id,Vehiculo vehiculo) {
       
        
        vehiculo.setClienteId(AggregateReference.to(id));//Para mantener la realcion ya que el id no es autoincremental
        vRepository.save(vehiculo);
        
        return "redirect:/clientes/"+id+"/vehiculos?editSuccess=true";
    }
    
    

    

}
