package com.escaes.repositories;

import org.springframework.data.repository.CrudRepository;


import com.escaes.models.Cliente;
import com.escaes.models.Membresia;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    
    Cliente findByMembresiaid(AggregateReference<Membresia,Long> membresiaid);

}
