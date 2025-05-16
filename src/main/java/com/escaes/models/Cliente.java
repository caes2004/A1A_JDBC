package com.escaes.models;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.escaes.models.enums.GeneroCliente;
@Table(name="clientes")
public class Cliente {
    @Id
    @Column("documento_cliente")
    private Long documentoCliente;
    @Column("contacto_cliente")
    private Long contactoCliente;
    @Column("correo_cliente")
    private String correoCliente;

    //Enum
    @Column("genero")
    private GeneroCliente genero;
    @Column("activo")
    private Boolean activo=true;

    //One to one
    @Column("membresia_id")
    private AggregateReference<Membresia,Long> membresiaid; 

    //One to many
    @Transient
    private Set<Vehiculo>vehiculos=new HashSet<>();

    public Long getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(Long documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public Long getContactoCliente() {
        return contactoCliente;
    }

    public void setContactoCliente(Long contactoCliente) {
        this.contactoCliente = contactoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public GeneroCliente getGenero() {
        return genero;
    }

    public void setGenero(GeneroCliente genero) {
        this.genero = genero;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public AggregateReference<Membresia, Long> getMembresiaid() {
        return membresiaid;
    }

    public void setMembresiaid(AggregateReference<Membresia, Long> membresiaid) {
        this.membresiaid = membresiaid;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }


    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    //Al añadir un vehiculo se debe agregar la referencia del cliente
    public void addVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
        vehiculo.setClienteId(AggregateReference.to(this.documentoCliente));
    }

    @Override
    public String toString() {
        return "Cliente [documentoCliente=" + documentoCliente + ", contactoCliente=" + contactoCliente
                + ", correoCliente=" + correoCliente + ", genero=" + genero + ", activo=" + activo + "]";
    }


   
    
}
