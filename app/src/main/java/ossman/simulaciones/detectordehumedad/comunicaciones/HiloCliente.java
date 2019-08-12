package ossman.simulaciones.detectordehumedad.comunicaciones;

import com.curso_simulaciones.comunicaciones.bluetooth.ClienteBluetooth;

public class HiloCliente
        extends ClienteBluetooth
        implements Runnable {


    private  Thread hilo;

    public HiloCliente(String direccion) {
        super(direccion);
        abrirSocketCliente();

    }
    public void empezarComunicacionConServidor(){

        hilo=new Thread(this);
        hilo.start();

    }
    public void terminarComunicacionConServidor(){

        hilo=null;
        cerrarSocketCliente();
        cerrarFlujoEntrada();
        cerrarFlujoSalida();


    }
    private long periodo_muestreo=200;//
    @Override public void run() {

        this.conectarSocketCliente();
        this.abrirFlujoEntrada();

        //bucle infinito
        while (true) {

            hacerProceso();

            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private void hacerProceso() {


        leer();


    }
}


