package com.escaes.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.escaes.models.Cliente;
import com.escaes.models.daos.ClienteJdbcDao;
import com.escaes.repositories.ClienteRepository;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;









@Controller
@RequestMapping("/clientes")
public class ClienteController {


    private ClienteRepository clrepo;
    private ClienteJdbcDao clDao;

    public ClienteController(ClienteRepository clrepo, ClienteJdbcDao clDao) {
        this.clrepo = clrepo;
        this.clDao=clDao;
    }

    @GetMapping()
    public String listClients(Model model) {

        model.addAttribute( "clients",clrepo.findAll());

        return "clientes/lista" ;
    }
    
    @GetMapping("/anadir")
    public String getAddClientView(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/anadir";
    }

    @PostMapping("/anadir")
    public String addClientPost(Cliente client) {
        
        if (!clrepo.existsById(client.getDocumentoCliente())) {
            clDao.insertarCliente(client);// INSERT
        } else {
            clrepo.save(client); // UPDATE
        }
        
        return "redirect:/clientes?success";
    }
    
    @GetMapping("/{id}")
    public String getEditUserView(@PathVariable("id")Long id,Model model) {

       Cliente cliente=clrepo.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/editar";
    }
    
    @PostMapping("/{id}")
    public String editClientPost(Cliente client) {
    
        clrepo.save(client);
        
        return "redirect:/clientes/" + client.getDocumentoCliente() + "?updated";
    }
    
}
