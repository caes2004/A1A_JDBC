package com.escaes.models;

import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.escaes.models.enums.TipoMembresia;
@Table(name="membresias")
public class Membresia {

    @Id
    private Long id;

    
    private Boolean membresia_activa;

    private LocalDate fecha_inico;

    private LocalDate fecha_terminacion;

    private TipoMembresia membresia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMembresia_activa() {
        return membresia_activa;
    }

    public void setMembresia_activa(Boolean membresia_activa) {
        this.membresia_activa = membresia_activa;
    }

    public LocalDate getFecha_inico() {
        return fecha_inico;
    }

    public void setFecha_inico(LocalDate fecha_inico) {
        this.fecha_inico = fecha_inico;
    }

    public LocalDate getFecha_terminacion() {
        return fecha_terminacion;
    }

    public void setFecha_terminacion(LocalDate fecha_terminacion) {
        this.fecha_terminacion = fecha_terminacion;
    }

    public TipoMembresia getMembresia() {
        return membresia;
    }

    public void setMembresia(TipoMembresia membresia) {
        this.membresia = membresia;
    }

    
    

    //@ManyToMany
    //@JoinTable( name = "membresia_prestacion",joinColumns = @JoinColumn(name = "membresia_id"),
    //inverseJoinColumns = @JoinColumn(name="prestacion_id"))
    //private List<ServicioLavado>prestaciones;


    
}
