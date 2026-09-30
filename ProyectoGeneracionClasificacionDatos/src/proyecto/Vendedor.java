package proyecto;

/**
 * Representa un vendedor del sistema.
 *
 * @author Proyecto Generacion y Clasificacion de Datos
 * @version 1.0
 */
public class Vendedor {

    /**
     * Tipo de documento del vendedor.
     */
    private String tipoDocumento;

    /**
     * Número de documento del vendedor.
     */
    private long numeroDocumento;

    /**
     * Nombres del vendedor.
     */
    private String nombres;

    /**
     * Apellidos del vendedor.
     */
    private String apellidos;

    /**
     * Dinero total recaudado por el vendedor.
     */
    private long dineroRecaudado;

    /**
     * Constructor de la clase Vendedor.
     *
     * @param tipoDocumento tipo de documento
     * @param numeroDocumento número de documento
     * @param nombres nombres del vendedor
     * @param apellidos apellidos del vendedor
     */
    public Vendedor(
            String tipoDocumento,
            long numeroDocumento,
            String nombres,
            String apellidos) {

        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dineroRecaudado = 0;
    }

    /**
     * Obtiene el tipo de documento.
     *
     * @return tipo de documento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Obtiene el número de documento.
     *
     * @return número de documento
     */
    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Obtiene los nombres.
     *
     * @return nombres del vendedor
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene los apellidos.
     *
     * @return apellidos del vendedor
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el nombre completo del vendedor.
     *
     * @return nombre completo
     */
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    /**
     * Obtiene el dinero recaudado.
     *
     * @return dinero recaudado
     */
    public long getDineroRecaudado() {
        return dineroRecaudado;
    }

    /**
     * Agrega dinero al total recaudado.
     *
     * @param dinero cantidad de dinero a agregar
     */
    public void agregarRecaudo(long dinero) {
        this.dineroRecaudado += dinero;
    }
}