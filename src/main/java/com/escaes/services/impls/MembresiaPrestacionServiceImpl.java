package com.escaes.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.escaes.models.Membresias_Prestaciones;
import com.escaes.repositories.membresia_prestaRepo;
import com.escaes.services.IMembresiaPrestacionService;

@Service
public class MembresiaPrestacionServiceImpl implements IMembresiaPrestacionService{

    private membresia_prestaRepo repo;

    public MembresiaPrestacionServiceImpl(membresia_prestaRepo repo) {
        this.repo = repo;
    }
    
    @Override
    public void vincularMembresiaConPrestacion(Long membresiaId, Long prestacionId) {
       Membresias_Prestaciones vinculo=new Membresias_Prestaciones(membresiaId,prestacionId);
       repo.save(vinculo);
        
    }

    @Override
    public List<Membresias_Prestaciones> obtenerPrestacionesPorMembresia(Long membresiaId) {
     return repo.findByMembresiaId(membresiaId);
    }

    @Override
    public List<Membresias_Prestaciones> membresiasPorPrestaciones(Long prestacionId) {
        
        return repo.findByPrestacionId(prestacionId);
    }

    

}
