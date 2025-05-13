package com.escaes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.escaes.models.Membresia;

public interface MembresiaRepository extends CrudRepository<Membresia,Long>{

    Optional<Membresia>findByDocumentoCliente(Long documentoCliente);

}
