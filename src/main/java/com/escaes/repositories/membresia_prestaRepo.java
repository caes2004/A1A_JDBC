package com.escaes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.escaes.models.Membresias_Prestaciones;

public interface membresia_prestaRepo extends CrudRepository<Membresias_Prestaciones,Long>{

    List<Membresias_Prestaciones>findByMembresiaId(Long membresiaId);
    List<Membresias_Prestaciones> findByPrestacionId(Long prestacionId);

    void deleteByPrestacionId(Long prestacionId);

}
