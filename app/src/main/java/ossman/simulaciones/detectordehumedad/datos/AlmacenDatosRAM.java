package ossman.simulaciones.detectordehumedad.datos;

public class AlmacenDatosRAM {

    //tamaño de letra
    public static int tamanoLetraResolucionIncluida=12;

    //dirección bluetooth del servidor
    public static String direccion="";


    //sobre configuración
    public static boolean cofigurar=false;
    public static int periodoMuestreo=200;//en ms
    public static int nDatos=50;
    public static int maximo=100;
    public static int minimo=0;
    public static String unidades1 ="%HR";
    public static String unidades2 ="°C";
    //sobre actualización de datos
    public static double datoActual = 0.0;

}

