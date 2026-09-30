package proyecto;

/**
 * Representa una venta de un producto realizada por un vendedor.
 *
 * @author Proyecto Generacion y Clasificacion de Datos
 * @version 1.0
 */
public class Venta {

    /**
     * Identificador del producto vendido.
     */
    private int idProducto;

    /**
     * Cantidad de unidades vendidas.
     */
    private int cantidad;

    /**
     * Constructor de la clase Venta.
     *
     * @param idProducto identificador del producto
     * @param cantidad cantidad vendida
     */
    public Venta(int idProducto, int cantidad) {

        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return ID del producto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Obtiene la cantidad vendida.
     *
     * @return cantidad vendida
     */
    public int getCantidad() {
        return cantidad;
    }
}