package com.taller.entrega;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvioEstadoActualizadoEvent {
    @JsonProperty("envio_id")
    private String envioId;
    @JsonProperty("cliente_id")
    private String clienteId;
    private String estado;
    private String email;
    private String nombre;

    public EnvioEstadoActualizadoEvent() {}

    public EnvioEstadoActualizadoEvent(String envioId, String clienteId, String estado, String email, String nombre) {
        this.envioId = envioId;
        this.clienteId = clienteId;
        this.estado = estado;
        this.email = email;
        this.nombre = nombre;
    }

    public String getEnvioId() { return envioId; }
    public void setEnvioId(String envioId) { this.envioId = envioId; }
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
