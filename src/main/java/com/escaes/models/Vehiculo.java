package com.escaes.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.escaes.models.enums.TipoVehiculo;

@Table(name="vehiculos")
public class Vehiculo {

    @Id
    @Column("placa_vehiculo")
    private String placaVehiculo;
    @Column("con_llaves")
    private Boolean conllaves=false;
    @Column("color")
    private String color;
    @Column("observaciones")
    private String observaciones;

    @Column("tipo_vehiculo")
    private TipoVehiculo tipoVehiculo;
    //Many to one
    @Column("cliente_documento_cliente")
    private AggregateReference<Cliente, Long> clienteId;
    
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    public Boolean getConllaves() {
        return conllaves;
    }
    public void setConllaves(Boolean conllaves) {
        this.conllaves = conllaves;
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
    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    public AggregateReference<Cliente, Long> getClienteId() {
        return clienteId;
    }
    public void setClienteId(AggregateReference<Cliente, Long> clienteId) {
        this.clienteId = clienteId;
    }

    
    

}
