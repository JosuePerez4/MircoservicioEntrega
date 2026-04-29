package com.taller.entrega;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "envios")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idClienteOriginal;
    private String estado;
    private String direccion;

    public Envio() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdClienteOriginal() { return idClienteOriginal; }
    public void setIdClienteOriginal(Long idClienteOriginal) { this.idClienteOriginal = idClienteOriginal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}