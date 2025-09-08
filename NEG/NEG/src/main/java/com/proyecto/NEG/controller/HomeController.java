package com.proyecto.NEG.controller;

import com.proyecto.NEG.dto.ProductoDTO;
import com.proyecto.NEG.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String home(Model model) {
        // Obtener algunos productos destacados para mostrar en el home
        List<ProductoDTO> productosDestacados = productoService.obtenerTodosProductos()
            .stream()
            .limit(4) // Mostrar solo 4 productos en el home
            .collect(java.util.stream.Collectors.toList());
        
        model.addAttribute("productosDestacados", productosDestacados);
        model.addAttribute("titulo", "Inicio - NEG");
        return "index";
    }

    @GetMapping("/acerca-de")
    public String acercaDe(Model model) {
        model.addAttribute("titulo", "Acerca de - NEG");
        return "acerca-de";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("titulo", "Nosotros - NEG");
        return "nosotros";
    }

    @GetMapping("/nuestra-tienda")
    public String nuestraTienda(Model model) {
        model.addAttribute("titulo", "Nuestra Tienda - NEG");
        return "nuestra-tienda";
    }

    @GetMapping("/nuestros-productos")
    public String nuestrosProductos(Model model) {
        List<ProductoDTO> productos = productoService.obtenerTodosProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Nuestros Productos - NEG");
        return "nuestros-productos";
    }
    @GetMapping("/test")
public String testPage(Model model) {
    System.out.println("✅ Accediendo a página de test");
    model.addAttribute("mensaje", "¡Todo funciona correctamente!");
    return "test"; // Esto debe renderizar test.html
}
}