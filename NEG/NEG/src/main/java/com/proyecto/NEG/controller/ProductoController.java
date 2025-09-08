package com.proyecto.NEG.controller;

import com.proyecto.NEG.dto.ProductoDTO;
import com.proyecto.NEG.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        List<ProductoDTO> productos = productoService.obtenerTodosProductos();
        model.addAttribute("productos", productos);
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new ProductoDTO());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoDTO producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "productos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }

    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam String nombre, Model model) {
        List<ProductoDTO> productos = productoService.buscarProductosPorNombre(nombre);
        model.addAttribute("productos", productos);
        return "productos/lista";
    }
    
    @GetMapping("/nuestros-productos")
public String nuestrosProductos(Model model) {
    System.out.println("✅ Intentando acceder a nuestros-productos");
    
    try {
        List<ProductoDTO> productos = productoService.obtenerTodosProductos();
        System.out.println("📦 Productos encontrados: " + productos.size());
        model.addAttribute("productos", productos);
        return "nuestros-productos";
    } catch (Exception e) {
        System.out.println("❌ Error: " + e.getMessage());
        e.printStackTrace();
        return "error"; // o redirige a otra página
    }
    
}

}