package com.escaes.models.daos;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escaes.models.Cliente;

@Repository
public class ClienteJdbcDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ClienteJdbcDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (documento_cliente, contacto_cliente, correo_cliente, genero, activo) " +
                     "VALUES (:doc, :contacto, :correo, :genero, :activo)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("doc", cliente.getDocumentoCliente());
        params.addValue("contacto", cliente.getContactoCliente());
        params.addValue("correo", cliente.getCorreoCliente());
        params.addValue("genero", cliente.getGenero().name()); // enum
        params.addValue("activo", cliente.getActivo());

        jdbcTemplate.update(sql, params);
    }
}
