// Archivo: src/main/resources/static/assets/js/main.js

document.addEventListener('DOMContentLoaded', () => {
    const precioVentaInput = document.getElementById('precioVenta');
    const precioCompraInput = document.getElementById('precioCompra');
    const stockInput = document.getElementById('stock');

    if (precioVentaInput && precioCompraInput && stockInput) {
        precioVentaInput.addEventListener('input', calcularGanancias);
        precioCompraInput.addEventListener('input', calcularGanancias);
        stockInput.addEventListener('input', calcularGanancias);
    }

    // Ejecutar el cálculo inicial en caso de que los campos ya tengan valores (por ejemplo, al editar un producto)
    calcularGanancias();
});

function calcularGanancias() {
    // Obtener los valores de los campos y convertirlos a números.
    // Usamos '|| 0' para manejar casos en los que el campo está vacío.
    const precioVenta = parseFloat(document.getElementById('precioVenta').value) || 0;
    const precioCompra = parseFloat(document.getElementById('precioCompra').value) || 0;
    const stock = parseInt(document.getElementById('stock').value) || 0;

    // Calcular la ganancia unitaria
    const gananciaUnitaria = precioVenta - precioCompra;

    // Calcular la ganancia total
    const totalGanancia = gananciaUnitaria * stock;

    // Mostrar los resultados en los campos de solo lectura.
    // .toFixed(2) asegura que siempre haya dos decimales.
    document.getElementById('gananciaUnitaria').value = gananciaUnitaria.toFixed(2);
    document.getElementById('totalGanancia').value = totalGanancia.toFixed(2);
}