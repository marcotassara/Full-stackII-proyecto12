package com.proyecto.NEG.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String marca;
    private String descripcion;
    private Double precioCompra;
    private Double precioVenta;
    private Integer stock;
    private String categoria;
    private String imagenUrl;
    private Double gananciaUnitaria;
    private Double totalGanancia;
}