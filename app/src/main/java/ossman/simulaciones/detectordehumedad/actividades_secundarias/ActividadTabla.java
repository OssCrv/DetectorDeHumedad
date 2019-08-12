package ossman.simulaciones.detectordehumedad.actividades_secundarias;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.instruments.archivador.ManejadorArchivosTXT;
import com.curso_simulaciones.instruments.tabla.TablaSimple;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;
import ossman.simulaciones.detectordehumedad.utilidades.DialogoSalir;

public class ActividadTabla extends AppCompatActivity {


    private Button botonEmpezar, botonGuardar;
    private int tamano_letra_resolucion_incluida;
    private int COLOR_1= Color.rgb(220, 156, 80);
    private HiloManejadorAnimacion hilo;
    private TablaSimple tabla;
    private ManejadorArchivosTXT manejador_archivos;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        gestionandoResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        eventos();

        manejador_archivos = new ManejadorArchivosTXT();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametroLayoutPrincipal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametroLayoutPrincipal);

        //hilo para crear tabla de datos
        hilo= new HiloManejadorAnimacion();



    }
    private void gestionandoResolucion(){

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamano_letra_resolucion_incluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;

    }
    private void crearElementosGUI(){


        botonEmpezar = new Button(this);
        botonEmpezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamano_letra_resolucion_incluida);
        botonEmpezar.setText("EMPEZAR");
        botonEmpezar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);

        botonGuardar = new Button(this);
        botonGuardar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamano_letra_resolucion_incluida);
        botonGuardar.setText("GUARDAR");
        botonGuardar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonGuardar.setEnabled(false);

        tabla = new TablaSimple(this);



    }
    private LinearLayout crearGUI(){

        //LinearLayoutPrincipal
        LinearLayout linearLayoutPrincipal = new LinearLayout(this);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.WHITE);



        //LinearLayout primera fila
        LinearLayout linearLayoutPrimeraFila = new LinearLayout(this);
        linearLayoutPrimeraFila.setOrientation(LinearLayout.VERTICAL);


        //LinearLayout tercera fila
        LinearLayout linearLayoutSegundaFila = new LinearLayout(this);
        linearLayoutSegundaFila.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutSegundaFila.setWeightSum(2f);


        //pegar de primera  y segunda fila en el princpal
        LinearLayout.LayoutParams parametrosPrimeraFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrimeraFila.weight = 9.0f;
        linearLayoutPrimeraFila.setLayoutParams(parametrosPrimeraFila);

        LinearLayout.LayoutParams parametrosSegundaFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSegundaFila.weight = 1.0f;
        linearLayoutSegundaFila.setLayoutParams(parametrosSegundaFila);


        linearLayoutPrincipal.addView(linearLayoutPrimeraFila);
        linearLayoutPrincipal.addView(linearLayoutSegundaFila);



        //pegado tabla a primera fila
        linearLayoutPrimeraFila.addView(tabla);


        //pegado botones segunda fila
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;
        //Adicionar a la segunda fila los botones
        linearLayoutSegundaFila.addView(botonEmpezar,parametrosPegadoBotones);
        linearLayoutSegundaFila.addView(botonGuardar,parametrosPegadoBotones);

        return linearLayoutPrincipal;



    }
    private void eventos(){

        botonEmpezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (botonEmpezar.getText() == "DETENER") {

                    botonEmpezar.setText("EMPEZAR");
                    botonGuardar.setEnabled(true);
                    detener();


                } else {

                    tabla.borrar();
                    manejador_archivos.borrarDatos();
                    botonEmpezar.setText("DETENER");
                    botonGuardar.setEnabled(false);
                    empezar();

                }

            }

        });


        botonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                guardar();

            }
        });
    }

    private boolean ON=false;

    private void empezar(){

        arrancar(this);

    }
    public void arrancar(Context context) {


        if(ON==false) {
            hilo.start();
        } else {

            hilo.reanudar();
        }

        ON=true;

    }
    public void detener() {

        hilo.detener();


    }
    private void guardar() {

        String carpeta="arduino_android/";
        //código para guardar datos
        manejador_archivos.guardar(this, carpeta) ;

    }
    protected void onPause() {
        super.onPause();

        detener();

        hilo=null;

    }
    class HiloManejadorAnimacion extends Thread {
        private int numero_datos;
        private float tiempo;
        private int periodoMuestreo = 100;//ms
        private float medida;
        private boolean pausa;
        //create an handler
        private final Handler myHandler = new Handler();


        public synchronized void pausar(){
            pausa=false;
        }

        public synchronized void reanudar(){

            numero_datos=0;
            tiempo=0.0f;
            pausa=false;
            notify();

        }

        public void detener(){

            pausa=true;

        }

        @Override
        public void run(){

            while (numero_datos< AlmacenDatosRAM.nDatos&pausa==false){

                try {
                    Thread.sleep(periodoMuestreo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //cargarDatos();
                hacerTrabajoDuro();



                synchronized (this){
                    while(pausa) {
                        try {
                            wait();
                        } catch (Exception e) {

                        }

                    }
                }
            }



        }


        private void hacerTrabajoDuro() {
            /*
               .... realizar el trabajo duro
              Actualiza la UI usando el handler
              y el runnable
            */

            myHandler.post(updateRunnable);

        }

        final Runnable updateRunnable = new Runnable() {
            public void run() {

                cargarDatos();

                if(numero_datos== AlmacenDatosRAM.nDatos){
                    botonEmpezar.setEnabled(false);
                    botonGuardar.setEnabled(true);
                }
            }
        };


        private void cargarDatos(){

            periodoMuestreo=AlmacenDatosRAM.periodoMuestreo;
            numero_datos=numero_datos+1;
            medida=(float)AlmacenDatosRAM.datoActual;
            //desplegar con máximo dos cifras decimales el tiempo
            float valor_tiempo=(float)(Math.round(0.001f * tiempo * 100.0) / 100.0d);
            tabla.setEtiquetaColumnas("Tiempo (s)", AlmacenDatosRAM.unidades1);
            tabla.setEtiquetaColumnas("Tiempo (s)", AlmacenDatosRAM.unidades2);
            tabla.enviarDatos(valor_tiempo, medida);
            manejador_archivos.llenarDatos(valor_tiempo, AlmacenDatosRAM.datoActual);
            tiempo=tiempo+periodoMuestreo;

        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {



        if (keyCode == KeyEvent.KEYCODE_BACK) {


            // Esto es lo que hace mi botón al pulsar ir a atrás
            DialogoSalir dialogo_salir = new DialogoSalir(this);


            return true;
        }



        return super.onKeyDown(keyCode, event);
    }
}

