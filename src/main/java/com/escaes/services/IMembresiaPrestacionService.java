package com.escaes.services;

import java.util.List;

import com.escaes.models.Membresias_Prestaciones;

public interface IMembresiaPrestacionService {

    void vincularMembresiaConPrestacion(Long membresiaId, Long prestacionId);

    List<Membresias_Prestaciones>obtenerPrestacionesPorMembresia(Long membresiaId);

    List<Membresias_Prestaciones>membresiasPorPrestaciones(Long prestacionId);
}
