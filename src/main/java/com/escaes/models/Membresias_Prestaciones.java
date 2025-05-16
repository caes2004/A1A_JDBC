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
    
    public Long getMembresiaId() {
        return membresiaId;
    }

    public void setMembresiaId(Long membresia_id) {
        this.membresiaId = membresia_id;
    }

    public Membresias_Prestaciones(Long membresiaId, Long prestacionId) {
        this.membresiaId = membresiaId;
        this.prestacionId = prestacionId;
    }

    public Long getPrestacionId() {
        return prestacionId;
    }

    public void setPrestacionId(Long prestacionId) {
        this.prestacionId = prestacionId;
    }

   

    

}
