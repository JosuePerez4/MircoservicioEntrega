package com.taller.entrega;

public class ClienteCreadoEvent {
    private Long idCliente;
    private String nombre;
    private String email;

    public ClienteCreadoEvent() {}

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}