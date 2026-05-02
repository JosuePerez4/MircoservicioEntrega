package com.taller.entrega;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteCreadoEvent {
    @JsonProperty("cliente_id")
    private String clienteId;
    private String nombre;
    private String email;

    public ClienteCreadoEvent() {}

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}