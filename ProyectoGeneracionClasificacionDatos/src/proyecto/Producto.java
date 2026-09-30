package proyecto;

/**
 * Representa un producto disponible para la venta.
 *
 * Un producto tiene un identificador, un nombre,
 * un precio y una cantidad total vendida.
 *
 * @author Proyecto Generacion y Clasificacion de Datos
 * @version 1.0
 */
public class Producto {

    /**
     * Identificador único del producto.
     */
    private int id;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Precio unitario del producto.
     */
    private int precio;

    /**
     * Cantidad total de unidades vendidas.
     */
    private int cantidadVendida;

    /**
     * Constructor de la clase Producto.
     *
     * @param id identificador del producto
     * @param nombre nombre del producto
     * @param precio precio unitario
     */
    public Producto(int id, String nombre, int precio) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadVendida = 0;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return identificador del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio unitario
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad total vendida.
     *
     * @return cantidad vendida
     */
    public int getCantidadVendida() {
        return cantidadVendida;
    }

    /**
     * Agrega unidades a la cantidad total vendida.
     *
     * @param cantidad cantidad de unidades vendidas
     */
    public void agregarCantidadVendida(int cantidad) {
        this.cantidadVendida += cantidad;
    }
}