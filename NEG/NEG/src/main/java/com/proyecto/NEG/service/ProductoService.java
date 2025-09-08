package com.proyecto.NEG.service;

import com.proyecto.NEG.dto.ProductoDTO;
import com.proyecto.NEG.model.Producto;
import com.proyecto.NEG.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> obtenerTodosProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

  public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
    Producto producto = convertToEntity(productoDTO);

    // Asignar valores por defecto si vienen vacíos
    if (producto.getPrecioCompra() == null) producto.setPrecioCompra(
        producto.getPrecioVenta() != null ? producto.getPrecioVenta() * 0.8 : 0.0
    );

    if (producto.getGananciaUnitaria() == null && producto.getPrecioVenta() != null && producto.getPrecioCompra() != null) {
        producto.setGananciaUnitaria(producto.getPrecioVenta() - producto.getPrecioCompra());
    }

    if (producto.getTotalGanancia() == null && producto.getGananciaUnitaria() != null && producto.getStock() != null) {
        producto.setTotalGanancia(producto.getGananciaUnitaria() * producto.getStock());
    }

    Producto productoSaved = productoRepository.save(producto);
    return convertToDTO(productoSaved);
}

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public List<ProductoDTO> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

private ProductoDTO convertToDTO(Producto producto) {
    ProductoDTO dto = new ProductoDTO();
    dto.setId(producto.getId());
    dto.setNombre(producto.getNombre());
    dto.setMarca(producto.getMarca());
    dto.setDescripcion(producto.getDescripcion());
    dto.setPrecioCompra(producto.getPrecioCompra());
    dto.setPrecioVenta(producto.getPrecioVenta());
    dto.setStock(producto.getStock());
    dto.setCategoria(producto.getCategoria());
    dto.setImagenUrl(producto.getImagenUrl());
    dto.setGananciaUnitaria(producto.getGananciaUnitaria());
    dto.setTotalGanancia(producto.getTotalGanancia());
    return dto;
}

private Producto convertToEntity(ProductoDTO dto) {
    Producto producto = new Producto();
    producto.setId(dto.getId());
    producto.setNombre(dto.getNombre());
    producto.setMarca(dto.getMarca());
    producto.setDescripcion(dto.getDescripcion());
    producto.setPrecioCompra(dto.getPrecioCompra());
    producto.setPrecioVenta(dto.getPrecioVenta());
    producto.setStock(dto.getStock());
    producto.setCategoria(dto.getCategoria());
    producto.setImagenUrl(dto.getImagenUrl());
    producto.setGananciaUnitaria(dto.getGananciaUnitaria());
    producto.setTotalGanancia(dto.getTotalGanancia());
    return producto;
}
}