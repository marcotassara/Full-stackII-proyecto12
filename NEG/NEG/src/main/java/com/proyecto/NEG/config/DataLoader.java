package com.proyecto.NEG.config;

import com.proyecto.NEG.model.Producto;
import com.proyecto.NEG.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existen productos
        if (productoRepository.count() == 0) {
            cargarProductosIniciales();
        }
    }

    private void cargarProductosIniciales() {
        List<Producto> productos = Arrays.asList(
            // BEBESTIBLES
            crearProducto("Coca-Cola 2lts", "Coca-Cola", "Gaseosa 2 litros", 1450.0, 1885.0, 4, "Bebestibles", 435.0, 1740.0),
            crearProducto("Coca-Cola 3lts", "Coca-Cola", "Gaseosa 3 litros", 2150.0, 2795.0, 6, "Bebestibles", 645.0, 3870.0),
            crearProducto("Coca-Cola 1.1/4", "Coca-Cola", "Gaseosa 1.25 litros", 1210.0, 1452.0, 4, "Bebestibles", 242.0, 1452.0),
            crearProducto("Score 591ml", "Score", "Bebida 591ml", 870.0, 1131.0, 6, "Bebestibles", 261.0, 1566.0),
            crearProducto("Kem 2.5lts", "Kem", "Gaseosa 2.5 litros", 1800.0, 2340.0, 3, "Bebestibles", 540.0, 1620.0),
            crearProducto("Pepsi 2.5lts", "Pepsi", "Gaseosa 2.5 litros", 1496.0, 1944.8, 3, "Bebestibles", 448.8, 1346.4),
            crearProducto("PIRI", "Piri", "Bebida energética", 990.0, 1287.0, 9, "Bebestibles", 297.0, 2673.0),
            crearProducto("Polvo Zuko", "Zuko", "Polvo para preparar bebidas", 170.0, 221.0, 15, "Bebestibles", 51.0, 765.0),

            // VIVERES
            crearProducto("Tallarines", "Merkat", "Tallarines 500g", 400.0, 520.0, 6, "Víveres", 120.0, 720.0),
            crearProducto("Aceite", "Merkat", "Aceite vegetal", 1490.0, 1937.0, 6, "Víveres", 447.0, 2682.0),
            crearProducto("Arroz G2 LARG", null, "Arroz grano largo", 1590.0, 2067.0, 3, "Víveres", 477.0, 1431.0),
            crearProducto("Sal", "Lobos", "Sal de mesa", 480.0, 624.0, 3, "Víveres", 144.0, 432.0),
            crearProducto("Azúcar", "Iansa", "Azúcar refinada", 1290.0, 1677.0, 3, "Víveres", 387.0, 1161.0)
        );

        productoRepository.saveAll(productos);
        System.out.println("✅ " + productos.size() + " productos cargados inicialmente");
    }

    private Producto crearProducto(String nombre, String marca, String descripcion, 
                                  Double precioCompra, Double precioVenta, Integer stock,
                                  String categoria, Double gananciaUnitaria, Double totalGanancia) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setDescripcion(descripcion);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setStock(stock);
        producto.setCategoria(categoria);
        producto.setGananciaUnitaria(gananciaUnitaria);
        producto.setTotalGanancia(totalGanancia);
        
        // Asignar imagen por categoría
        if ("Bebestibles".equals(categoria)) {
            producto.setImagenUrl("/assets/img/Snorlax.webp");
        } else {
            producto.setImagenUrl("/assets/img/descarga.jpeg");
        }
        
        return producto;
    }
}