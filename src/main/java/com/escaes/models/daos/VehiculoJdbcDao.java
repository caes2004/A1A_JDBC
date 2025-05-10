package com.escaes.models.daos;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escaes.models.Vehiculo;

@Repository
public class VehiculoJdbcDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public VehiculoJdbcDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertarVehiculo(Vehiculo vehiculo){
        String sql = "INSERT INTO vehiculos (cliente_documento_cliente, con_llaves, color, observaciones, placa_vehiculo, tipo_vehiculo) " +
                     "VALUES (:doc, :llaves, :color, :observaciones, :placa, :tipo )";

        MapSqlParameterSource params=new MapSqlParameterSource();      
        params.addValue("doc", vehiculo.getClienteId().getId());
        params.addValue("llaves", vehiculo.getConllaves());
        params.addValue("color", vehiculo.getColor());
        params.addValue("observaciones", vehiculo.getObservaciones());
        params.addValue("placa", vehiculo.getPlacaVehiculo());
        params.addValue("tipo", vehiculo.getTipoVehiculo().name());
        
        jdbcTemplate.update(sql, params);
    }
}
