package com.escaes.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.escaes.models.enums.TipoVehiculo;

@Table(name="vehiculos")
public class Vehiculo {

    @Id
    private String placa_vehiculo;

    private Boolean con_llaves=false;

    private String color;

    private String observaciones;

    
    private TipoVehiculo tipo_vehiculo;
    //Many to one
    @Column("cliente_documento_cliente")
    private AggregateReference<Cliente, Long> clienteId;

    public AggregateReference<Cliente, Long> getClienteId() {
        return clienteId;
    }

    public void setClienteId(AggregateReference<Cliente, Long> clienteId) {
        this.clienteId = clienteId;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public Boolean getCon_llaves() {
        return con_llaves;
    }

    public void setCon_llaves(Boolean con_llaves) {
        this.con_llaves = con_llaves;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoVehiculo getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(TipoVehiculo tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    

}
