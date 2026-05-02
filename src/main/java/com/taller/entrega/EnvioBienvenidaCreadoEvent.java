package com.taller.entrega;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvioBienvenidaCreadoEvent {
    @JsonProperty("envio_id")
    private String envioId;
    @JsonProperty("cliente_id")
    private String clienteId;
    private String nombre;
    private String email;
    private String estado;
    private String direccion;

    public EnvioBienvenidaCreadoEvent() {}

    public EnvioBienvenidaCreadoEvent(
            String envioId,
            String clienteId,
            String nombre,
            String email,
            String estado,
            String direccion) {
        this.envioId = envioId;
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.direccion = direccion;
    }

    public String getEnvioId() { return envioId; }
    public void setEnvioId(String envioId) { this.envioId = envioId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
