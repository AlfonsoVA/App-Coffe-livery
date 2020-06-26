package com.example.tarea5;

public class Pedido {
    private String direccion;
    private String ciudad;
    private String orden;

    public Pedido(String direccion, String ciudad, String orden){
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.orden = orden;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getCiudad(){
        return ciudad;
    }

    public String getOrden(){
        return orden;
    }

}
