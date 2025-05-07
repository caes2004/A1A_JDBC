package com.escaes.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import com.escaes.models.enums.EstadoPrestacion;

@Table(name="prestaciones")
public class ServicioLavado {
    
    @Id
    private Long id;

    private String nombre_prestacion;

    private String descripcion_prestacion;

    private Double valor_prestacion;

    private EstadoPrestacion estado;

    
    public String getNombre_prestacion() {
        return nombre_prestacion;
    }

    public void setNombre_prestacion(String nombre_prestacion) {
        this.nombre_prestacion = nombre_prestacion;
    }

    public String getDescripcion_prestacion() {
        return descripcion_prestacion;
    }

    public void setDescripcion_prestacion(String descripcion_prestacion) {
        this.descripcion_prestacion = descripcion_prestacion;
    }

    public Double getValor_prestacion() {
        return valor_prestacion;
    }

    public void setValor_prestacion(Double valor_prestacion) {
        this.valor_prestacion = valor_prestacion;
    }

    public EstadoPrestacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestacion estado) {
        this.estado = estado;
    }


    

}
