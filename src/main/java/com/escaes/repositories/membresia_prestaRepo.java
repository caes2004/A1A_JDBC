package com.escaes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.escaes.models.Membresias_Prestaciones;

public interface membresia_prestaRepo extends CrudRepository<Membresias_Prestaciones,Void>{

    List<Membresias_Prestaciones>findByMembresiaId(Long membresia_id);
    List<Membresias_Prestaciones> findByPrestacionId(Long prestacion_id);

}
