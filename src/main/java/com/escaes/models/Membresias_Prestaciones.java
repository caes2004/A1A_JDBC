package com.escaes.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name="membresia_prestacion")
public class Membresias_Prestaciones {

    @Id
    @Column("id")
    private Long id;
    @Column("membresia_id")
    private Long membresiaId;
    @Column("prestacion_id")
    private Long prestacionId;
    
    public Membresias_Prestaciones(Long membresia_id, Long prestacion_id) {
        this.membresiaId = membresia_id;
        this.prestacionId = prestacion_id;
    }

    public Long getMembresia_id() {
        return membresiaId;
    }

    public void setMembresia_id(Long membresia_id) {
        this.membresiaId = membresia_id;
    }

    public Long getPrestacion_id() {
        return prestacionId;
    }

    public void setPrestacion_id(Long prestacion_id) {
        this.prestacionId = prestacion_id;
    }

   

    

}
