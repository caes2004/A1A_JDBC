package com.escaes.models;

import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.escaes.models.enums.TipoMembresia;
@Table(name="membresias")
public class Membresia {

    @Id
    @Column("id")
    private Long id;
    @Column("cliente_documento_cliente")
    private Long documentoCliente;
    @Column("membresia_activa")
    private Boolean membresiaActiva;
    @Column("fecha_inico")
    private LocalDate fechaInico;
    @Column("fecha_terminacion")
    private LocalDate fechaTerminacion;
    @Column("membresia")
    private TipoMembresia Tipomembresia;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDocumentoCliente() {
        return documentoCliente;
    }
    public void setDocumentoCliente(Long documentoCliente) {
        this.documentoCliente = documentoCliente;
    }
    public Boolean getMembresiaActiva() {
        return membresiaActiva;
    }
    public void setMembresiaActiva(Boolean membresiaActiva) {
        this.membresiaActiva = membresiaActiva;
    }
    public LocalDate getFechaInico() {
        return fechaInico;
    }
    public void setFechaInico(LocalDate fechaInico) {
        this.fechaInico = fechaInico;
    }
    public LocalDate getFechaTerminacion() {
        return fechaTerminacion;
    }
    public void setFechaTerminacion(LocalDate fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }
    public TipoMembresia getTipoMembresia() {
        return Tipomembresia;
    }
    public void setTipoMembresia(TipoMembresia membresia) {
        this.Tipomembresia = membresia;
    }

    

    
    

    //@ManyToMany
    //@JoinTable( name = "membresia_prestacion",joinColumns = @JoinColumn(name = "membresia_id"),
    //inverseJoinColumns = @JoinColumn(name="prestacion_id"))
    //private List<ServicioLavado>prestaciones;


    
}
