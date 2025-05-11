package com.escaes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.escaes.models.Vehiculo;
import java.util.List;


public interface VehiculoRepository extends CrudRepository<Vehiculo,String> {

    List<Vehiculo> findAllByClienteId(Long clienteId);
    Vehiculo findByPlacaVehiculo(String placaVehiculo);
}
