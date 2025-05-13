package com.escaes.controllers;

import java.util.Optional;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.escaes.models.Cliente;
import com.escaes.models.Membresia;
import com.escaes.repositories.ClienteRepository;
import com.escaes.repositories.MembresiaRepository;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/clientes")
public class MembresiaController {

    private MembresiaRepository mRepository;
    private ClienteRepository clRepo;

    
    public MembresiaController(MembresiaRepository mRepository,ClienteRepository clRepo) {
        this.mRepository = mRepository;
        this.clRepo=clRepo;
    }



    @GetMapping("{id}/membresias")
    public String getMembresiaView(@PathVariable("id")Long id,Model model) {

        
        Cliente cliente = clRepo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("cliente", cliente);

        
        Optional<Membresia> membresia = mRepository.findByDocumentoCliente(id);
        if (membresia.isPresent()) {
            model.addAttribute("membresia", membresia.get());
        } else {
            model.addAttribute("membresia", null);
        }
            
        return "membresia/lista";
    }
    @GetMapping("/{id}/membresias/anadir")
    public String getMembresiaAddView(@PathVariable("id")Long id,Model model) {

        model.addAttribute("cliente", clRepo.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado")));
        model.addAttribute("membresia",new Membresia());
        return"membresia/anadir";
    }
    
    @PostMapping("{id}/membresias/anadir")
    public String AddmembresiaPost(@PathVariable("id")Long id,Membresia membresia) {
        membresia.setDocumentoCliente(id);
        membresia.setId(null);
        mRepository.save(membresia);
        return "redirect:/clientes/"+id+"/membresias?success";
    }
    

}
