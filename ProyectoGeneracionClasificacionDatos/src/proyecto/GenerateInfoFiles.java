package proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Genera los archivos de información que serán utilizados
 * como entrada para el programa principal del proyecto.
 *
 * Los archivos generados contienen información de:
 * - Productos.
 * - Vendedores.
 * - Ventas realizadas por los vendedores.
 *
 * @author Proyecto Generacion y Clasificacion de Datos
 * @version 1.0
 */
public class GenerateInfoFiles {

    /**
     * Generador de números pseudoaleatorios.
     */
    private static final Random RANDOM = new Random();

    /**
     * Carpeta donde se almacenarán los archivos generados.
     */
    private static final String DATA_FOLDER = "data";

    /**
     * Lista de identificadores de productos disponibles.
     *
     * Esta lista permite que los archivos de ventas utilicen
     * únicamente productos que realmente existen.
     */
    private static final List<Integer> PRODUCT_IDS = new ArrayList<Integer>();

    /**
     * Método principal del programa.
     *
     * Genera todos los archivos necesarios para realizar
     * las pruebas del proyecto.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {

        try {

            /*
             * Primero se genera el archivo de productos.
             * En este ejemplo se generarán 20 productos.
             */
            createProductsFile(20);

            /*
             * Después se genera la información de los vendedores.
             * En este ejemplo se generarán 10 vendedores.
             */
            createSalesManInfoFile(10);

            System.out.println(
                    "Los archivos fueron generados correctamente."
            );

        } catch (Exception e) {

            System.out.println(
                    "Se produjo un error durante la generacion de archivos."
            );

            System.out.println(
                    "Detalle del error: " + e.getMessage()
            );
        }
    }

    /**
     * Crea un archivo con información pseudoaleatoria de productos.
     *
     * El archivo generado tendrá el siguiente formato:
     *
     * IDProducto;NombreProducto;PrecioPorUnidadProducto
     *
     * @param productsCount cantidad de productos que se desean generar
     * @throws Exception si ocurre un error al crear o escribir el archivo
     */
    public static void createProductsFile(int productsCount)
            throws Exception {

        /*
         * Comprobamos que la cantidad solicitada sea válida.
         */
        if (productsCount <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de productos debe ser mayor que cero."
            );
        }

        /*
         * Creamos la carpeta data si todavía no existe.
         */
        createDataFolder();

        /*
         * Limpiamos la lista de IDs antes de generar los productos.
         */
        PRODUCT_IDS.clear();

        /*
         * Lista de nombres de productos.
         *
         * Si se solicitan más productos que nombres disponibles,
         * posteriormente se generarán nombres adicionales.
         */
        String[] productNames = {
            "Teclado",
            "Mouse",
            "Monitor",
            "Audifonos",
            "Webcam",
            "Parlantes",
            "Memoria RAM",
            "Disco SSD",
            "Impresora",
            "Microfono",
            "Camara",
            "Router",
            "Switch",
            "Tablet",
            "Portatil",
            "Cargador",
            "Cable HDMI",
            "Memoria USB",
            "Alfombrilla",
            "Control Gamer"
        };

        /*
         * El archivo se almacenará dentro de la carpeta data.
         */
        File file = new File(
                DATA_FOLDER + File.separator + "products.txt"
        );

        PrintWriter writer = new PrintWriter(
                new FileWriter(file)
        );

        /*
         * Generamos cada producto.
         */
        for (int i = 1; i <= productsCount; i++) {

            /*
             * Cada producto tiene un ID único.
             */
            int productId = i;

            PRODUCT_IDS.add(productId);

            /*
             * Seleccionamos un nombre.
             *
             * Para evitar productos con exactamente el mismo nombre,
             * utilizamos los nombres de la lista mientras estén disponibles.
             * Si se solicitan más productos, generamos un nombre adicional.
             */
            String productName;

            if (i <= productNames.length) {

                productName = productNames[i - 1];

            } else {

                productName = "Producto" + i;
            }

            /*
             * Generamos un precio entre $20.000 y $999.999.
             */
            int price = 20000 + RANDOM.nextInt(980000);

            /*
             * Escribimos la información en formato:
             *
             * ID;Nombre;Precio
             */
            writer.println(
                    productId + ";"
                    + productName + ";"
                    + price
            );
        }

        /*
         * Cerramos el archivo.
         */
        writer.close();

        System.out.println(
                "Archivo products.txt creado con "
                + productsCount
                + " productos."
        );
    }

    /**
     * Crea un archivo con información pseudoaleatoria de vendedores.
     *
     * El archivo generado tendrá el siguiente formato:
     *
     * TipoDocumento;NumeroDocumento;Nombres;Apellidos
     *
     * Además, para cada vendedor se genera un archivo individual
     * con sus ventas.
     *
     * @param salesmanCount cantidad de vendedores que se desean generar
     * @throws Exception si ocurre un error al crear o escribir los archivos
     */
    public static void createSalesManInfoFile(int salesmanCount)
            throws Exception {

        /*
         * Comprobamos que la cantidad solicitada sea válida.
         */
        if (salesmanCount <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de vendedores debe ser mayor que cero."
            );
        }

        /*
         * Comprobamos que primero existan productos.
         * Las ventas necesitan productos válidos.
         */
        if (PRODUCT_IDS.isEmpty()) {
            throw new IllegalStateException(
                    "Primero se deben generar los productos."
            );
        }

        /*
         * Creamos la carpeta data.
         */
        createDataFolder();

        /*
         * Lista de nombres.
         */
        String[] names = {
            "Juan",
            "Maria",
            "Carlos",
            "Laura",
            "Alejandro",
            "Andres",
            "Daniel",
            "Sofia",
            "Camila",
            "Sebastian",
            "Valentina",
            "David",
            "Paula",
            "Felipe",
            "Natalia"
        };

        /*
         * Lista de apellidos.
         */
        String[] lastNames = {
            "Perez",
            "Gomez",
            "Rodriguez",
            "Lopez",
            "Martinez",
            "Garcia",
            "Hernandez",
            "Ramirez",
            "Torres",
            "Vargas",
            "Castro",
            "Moreno",
            "Rojas",
            "Jimenez",
            "Gutierrez"
        };

        /*
         * Archivo general de vendedores.
         */
        File file = new File(
                DATA_FOLDER + File.separator + "salesmen.txt"
        );

        PrintWriter writer = new PrintWriter(
                new FileWriter(file)
        );

        /*
         * Generamos cada vendedor.
         */
        for (int i = 1; i <= salesmanCount; i++) {

            /*
             * Tipo de documento.
             */
            String documentType = "CC";

            /*
             * Generamos un número de documento.
             *
             * El número será diferente para cada vendedor.
             */
            long documentNumber = 1000000000L + i;

            /*
             * Seleccionamos un nombre y un apellido.
             */
            String name = names[
                    RANDOM.nextInt(names.length)
            ];

            String lastName = lastNames[
                    RANDOM.nextInt(lastNames.length)
            ];

            /*
             * Escribimos el vendedor en el archivo general.
             */
            writer.println(
                    documentType + ";"
                    + documentNumber + ";"
                    + name + ";"
                    + lastName
            );

            /*
             * Generamos entre 5 y 15 ventas para cada vendedor.
             */
            int randomSalesCount = 5 + RANDOM.nextInt(11);

            /*
             * Creamos el archivo individual del vendedor.
             */
            createSalesMenFile(
                    randomSalesCount,
                    name + " " + lastName,
                    documentNumber
            );
        }

        /*
         * Cerramos el archivo general.
         */
        writer.close();

        System.out.println(
                "Archivo salesmen.txt creado con "
                + salesmanCount
                + " vendedores."
        );
    }

    /**
     * Crea un archivo con las ventas de un vendedor.
     *
     * La primera línea contiene el tipo y número de documento
     * del vendedor.
     *
     * Las siguientes líneas contienen:
     *
     * IDProducto;CantidadProductoVendido
     *
     * @param randomSalesCount cantidad de ventas a generar
     * @param name nombre completo del vendedor
     * @param id número de documento del vendedor
     * @throws Exception si ocurre un error al crear o escribir el archivo
     */
    public static void createSalesMenFile(
            int randomSalesCount,
            String name,
            long id)
            throws Exception {

        /*
         * Validamos la cantidad de ventas.
         */
        if (randomSalesCount <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de ventas debe ser mayor que cero."
            );
        }

        /*
         * Verificamos que existan productos.
         */
        if (PRODUCT_IDS.isEmpty()) {
            throw new IllegalStateException(
                    "No existen productos para generar las ventas."
            );
        }

        /*
         * Creamos una lista temporal con los productos.
         *
         * Esto permite seleccionar productos aleatoriamente.
         */
        List<Integer> availableProducts =
                new ArrayList<Integer>(PRODUCT_IDS);

        /*
         * Mezclamos aleatoriamente la lista.
         */
        Collections.shuffle(
                availableProducts,
                RANDOM
        );

        /*
         * Creamos el nombre del archivo.
         *
         * Ejemplo:
         * sales_1000000001.txt
         */
        File file = new File(
                DATA_FOLDER
                + File.separator
                + "sales_"
                + id
                + ".txt"
        );

        PrintWriter writer = new PrintWriter(
                new FileWriter(file)
        );

        /*
         * Primera línea:
         *
         * TipoDocumento;NumeroDocumento
         */
        writer.println(
                "CC;" + id
        );

        /*
         * No podemos generar más ventas diferentes
         * que productos disponibles.
         */
        int salesToGenerate = Math.min(
                randomSalesCount,
                availableProducts.size()
        );

        /*
         * Generamos las ventas.
         */
        for (int i = 0; i < salesToGenerate; i++) {

            /*
             * Seleccionamos un producto.
             */
            int productId = availableProducts.get(i);

            /*
             * Generamos una cantidad vendida
             * entre 1 y 20 unidades.
             */
            int quantity = 1 + RANDOM.nextInt(20);

            /*
             * Escribimos:
             *
             * IDProducto;Cantidad
             */
            writer.println(
                    productId + ";" + quantity
            );
        }

        /*
         * Cerramos el archivo.
         */
        writer.close();

        System.out.println(
                "Archivo de ventas creado para: "
                + name
        );
    }

    /**
     * Crea la carpeta donde se almacenarán los archivos.
     *
     * @throws Exception si no es posible crear la carpeta
     */
    private static void createDataFolder()
            throws Exception {

        File folder = new File(DATA_FOLDER);

        /*
         * Si la carpeta no existe, intentamos crearla.
         */
        if (!folder.exists()) {

            boolean created = folder.mkdirs();

            if (!created) {

                throw new Exception(
                        "No fue posible crear la carpeta "
                        + DATA_FOLDER
                );
            }
        }
    }
}