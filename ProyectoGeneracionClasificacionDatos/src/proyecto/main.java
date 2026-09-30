package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Programa principal encargado de leer los archivos de entrada,
 * procesar la información de vendedores y productos y generar
 * los archivos de reporte.
 *
 * @author Proyecto Generacion y Clasificacion de Datos
 * @version 1.0
 */
public class main {

    /**
     * Carpeta que contiene los archivos de entrada.
     */
    private static final String DATA_FOLDER = "data";

    /**
     * Carpeta donde se almacenarán los reportes.
     */
    private static final String OUTPUT_FOLDER = "output";

    /**
     * Lista de productos cargados desde el archivo.
     */
    private static final List<Producto> productos =
            new ArrayList<Producto>();

    /**
     * Lista de vendedores cargados desde el archivo.
     */
    private static final List<Vendedor> vendedores =
            new ArrayList<Vendedor>();

    /**
     * Método principal.
     *
     * @param args argumentos de ejecución
     */
    public static void main(String[] args) {

        try {

            System.out.println(
                    "Iniciando procesamiento de archivos..."
            );

            /*
             * Creamos la carpeta de salida.
             */
            crearCarpetaSalida();

            /*
             * Leemos la información de productos.
             */
            cargarProductos();

            /*
             * Leemos la información de vendedores.
             */
            cargarVendedores();

            /*
             * Procesamos los archivos individuales
             * de ventas de cada vendedor.
             */
            procesarArchivosVentas();

            /*
             * Generamos el reporte de vendedores.
             */
            generarReporteVendedores();

            /*
             * Generamos el reporte de productos.
             */
            generarReporteProductos();

            System.out.println();
            System.out.println(
                    "El procesamiento termino correctamente."
            );

            System.out.println(
                    "Los reportes fueron generados en la carpeta: "
                    + OUTPUT_FOLDER
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "Se produjo un error durante el procesamiento."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }
    }

    /**
     * Lee el archivo products.txt y crea los objetos Producto.
     *
     * Formato esperado:
     *
     * IDProducto;NombreProducto;PrecioPorUnidad
     *
     * @throws Exception si ocurre un error de lectura
     */
    private static void cargarProductos()
            throws Exception {

        File file = new File(
                DATA_FOLDER
                + File.separator
                + "products.txt"
        );

        if (!file.exists()) {

            throw new Exception(
                    "No existe el archivo products.txt."
            );
        }

        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] data = line.split(";");

            if (data.length != 3) {

                throw new Exception(
                        "Formato incorrecto en products.txt: "
                        + line
                );
            }

            int id = Integer.parseInt(
                    data[0].trim()
            );

            String nombre = data[1].trim();

            int precio = Integer.parseInt(
                    data[2].trim()
            );

            Producto producto = new Producto(
                    id,
                    nombre,
                    precio
            );

            productos.add(producto);
        }

        reader.close();

        System.out.println(
                "Productos cargados: "
                + productos.size()
        );
    }

    /**
     * Lee el archivo salesmen.txt y crea los objetos Vendedor.
     *
     * Formato esperado:
     *
     * TipoDocumento;NumeroDocumento;Nombres;Apellidos
     *
     * @throws Exception si ocurre un error de lectura
     */
    private static void cargarVendedores()
            throws Exception {

        File file = new File(
                DATA_FOLDER
                + File.separator
                + "salesmen.txt"
        );

        if (!file.exists()) {

            throw new Exception(
                    "No existe el archivo salesmen.txt."
            );
        }

        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] data = line.split(";");

            if (data.length != 4) {

                throw new Exception(
                        "Formato incorrecto en salesmen.txt: "
                        + line
                );
            }

            String tipoDocumento = data[0].trim();

            long numeroDocumento = Long.parseLong(
                    data[1].trim()
            );

            String nombres = data[2].trim();

            String apellidos = data[3].trim();

            Vendedor vendedor = new Vendedor(
                    tipoDocumento,
                    numeroDocumento,
                    nombres,
                    apellidos
            );

            vendedores.add(vendedor);
        }

        reader.close();

        System.out.println(
                "Vendedores cargados: "
                + vendedores.size()
        );
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param idProducto identificador buscado
     * @return producto encontrado o null si no existe
     */
    private static Producto buscarProducto(
            int idProducto) {

        for (Producto producto : productos) {

            if (producto.getId() == idProducto) {

                return producto;
            }
        }

        return null;
    }

    /**
     * Busca un vendedor por su número de documento.
     *
     * @param numeroDocumento documento buscado
     * @return vendedor encontrado o null si no existe
     */
    private static Vendedor buscarVendedor(
            long numeroDocumento) {

        for (Vendedor vendedor : vendedores) {

            if (vendedor.getNumeroDocumento()
                    == numeroDocumento) {

                return vendedor;
            }
        }

        return null;
    }

    /**
     * Procesa todos los archivos sales_*.txt de la carpeta data.
     *
     * Cada archivo contiene las ventas realizadas por un vendedor.
     *
     * @throws Exception si ocurre un error durante la lectura
     */
    private static void procesarArchivosVentas()
            throws Exception {

        File folder = new File(DATA_FOLDER);

        File[] files = folder.listFiles();

        if (files == null) {

            throw new Exception(
                    "No fue posible leer la carpeta data."
            );
        }

        int filesProcessed = 0;

        for (File file : files) {

            if (!file.isFile()) {
                continue;
            }

            String fileName = file.getName();

            /*
             * Solamente procesamos los archivos que comienzan
             * con sales_ y terminan en .txt.
             */
            if (!fileName.startsWith("sales_")
                    || !fileName.endsWith(".txt")) {

                continue;
            }

            procesarArchivoVenta(file);

            filesProcessed++;
        }

        System.out.println(
                "Archivos de ventas procesados: "
                + filesProcessed
        );
    }

    /**
     * Procesa un archivo individual de ventas.
     *
     * @param file archivo que contiene las ventas
     * @throws Exception si el formato es incorrecto
     */
    private static void procesarArchivoVenta(
            File file)
            throws Exception {

        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );

        /*
         * La primera línea contiene:
         *
         * CC;NumeroDocumento
         */
        String firstLine = reader.readLine();

        if (firstLine == null) {

            reader.close();

            throw new Exception(
                    "El archivo esta vacio: "
                    + file.getName()
            );
        }

        String[] sellerData = firstLine.split(";");

        if (sellerData.length != 2) {

            reader.close();

            throw new Exception(
                    "Formato incorrecto en: "
                    + file.getName()
            );
        }

        long sellerId = Long.parseLong(
                sellerData[1].trim()
        );

        Vendedor vendedor = buscarVendedor(
                sellerId
        );

        if (vendedor == null) {

            reader.close();

            throw new Exception(
                    "No se encontro el vendedor con documento "
                    + sellerId
            );
        }

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] saleData = line.split(";");

            if (saleData.length != 2) {

                reader.close();

                throw new Exception(
                        "Formato incorrecto en el archivo "
                        + file.getName()
                        + ": "
                        + line
                );
            }

            int productId = Integer.parseInt(
                    saleData[0].trim()
            );

            int quantity = Integer.parseInt(
                    saleData[1].trim()
            );

            /*
             * La cantidad debe ser positiva.
             */
            if (quantity <= 0) {

                reader.close();

                throw new Exception(
                        "Cantidad invalida en "
                        + file.getName()
                );
            }

            /*
             * Buscamos el producto.
             */
            Producto producto = buscarProducto(
                    productId
            );

            if (producto == null) {

                reader.close();

                throw new Exception(
                        "El producto "
                        + productId
                        + " no existe."
                );
            }

            /*
             * Calculamos el valor de la venta.
             */
            long valorVenta =
                    (long) producto.getPrecio()
                    * quantity;

            /*
             * Sumamos el valor al vendedor.
             */
            vendedor.agregarRecaudo(
                    valorVenta
            );

            /*
             * Sumamos la cantidad vendida al producto.
             */
            producto.agregarCantidadVendida(
                    quantity
            );
        }

        reader.close();
    }

    /**
     * Genera el reporte de vendedores.
     *
     * El archivo se ordena de mayor a menor
     * según el dinero recaudado.
     *
     * Formato:
     *
     * NombreCompleto;DineroRecaudado
     *
     * @throws Exception si ocurre un error al crear el reporte
     */
    private static void generarReporteVendedores()
            throws Exception {

        /*
         * Creamos una copia para poder ordenarla
         * sin alterar la lista original.
         */
        List<Vendedor> vendedoresOrdenados =
                new ArrayList<Vendedor>(vendedores);

        /*
         * Ordenamos de mayor a menor recaudo.
         */
        Collections.sort(
                vendedoresOrdenados,
                new Comparator<Vendedor>() {

                    @Override
                    public int compare(
                            Vendedor vendedor1,
                            Vendedor vendedor2) {

                        return Long.compare(
                                vendedor2.getDineroRecaudado(),
                                vendedor1.getDineroRecaudado()
                        );
                    }
                }
        );

        File file = new File(
                OUTPUT_FOLDER
                + File.separator
                + "salesmen_report.csv"
        );

        PrintWriter writer = new PrintWriter(
                new FileWriter(file)
        );

        /*
         * No agregamos encabezado porque la guía
         * solicita un vendedor por línea.
         */
        for (Vendedor vendedor
                : vendedoresOrdenados) {

            writer.println(
                    vendedor.getNombreCompleto()
                    + ";"
                    + vendedor.getDineroRecaudado()
            );
        }

        writer.close();

        System.out.println(
                "Reporte de vendedores generado."
        );
    }

    /**
     * Genera el reporte de productos.
     *
     * Los productos se ordenan de mayor a menor
     * según la cantidad total vendida.
     *
     * Formato:
     *
     * NombreProducto;Precio
     *
     * @throws Exception si ocurre un error al crear el reporte
     */
    private static void generarReporteProductos()
            throws Exception {

        /*
         * Creamos una copia para ordenar.
         */
        List<Producto> productosOrdenados =
                new ArrayList<Producto>(productos);

        /*
         * Ordenamos de mayor a menor cantidad vendida.
         */
        Collections.sort(
                productosOrdenados,
                new Comparator<Producto>() {

                    @Override
                    public int compare(
                            Producto producto1,
                            Producto producto2) {

                        return Integer.compare(
                                producto2.getCantidadVendida(),
                                producto1.getCantidadVendida()
                        );
                    }
                }
        );

        File file = new File(
                OUTPUT_FOLDER
                + File.separator
                + "products_report.csv"
        );

        PrintWriter writer = new PrintWriter(
                new FileWriter(file)
        );

        /*
         * Escribimos:
         *
         * Nombre;Precio
         */
        for (Producto producto
                : productosOrdenados) {

            writer.println(
                    producto.getNombre()
                    + ";"
                    + producto.getPrecio()
            );
        }

        writer.close();

        System.out.println(
                "Reporte de productos generado."
        );
    }

    /**
     * Crea la carpeta de salida si no existe.
     *
     * @throws Exception si no se puede crear la carpeta
     */
    private static void crearCarpetaSalida()
            throws Exception {

        File folder = new File(OUTPUT_FOLDER);

        if (!folder.exists()) {

            boolean created = folder.mkdirs();

            if (!created) {

                throw new Exception(
                        "No fue posible crear la carpeta "
                        + OUTPUT_FOLDER
                );
            }
        }
    }
}