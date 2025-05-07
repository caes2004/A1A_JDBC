package com.escaes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.escaes.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {

}
