package ossman.simulaciones.detectordehumedad.actividades_secundarias;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.instruments.archivador.ManejadorArchivosTXT;
import com.curso_simulaciones.instruments.graficador.GraficadorXY;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;
import ossman.simulaciones.detectordehumedad.utilidades.DialogoSalir;

public class ActividadGrafica
        extends Activity
        implements Runnable {


    private int tamanoLetraResolucionIncluida;
    private int COLOR_1= Color.rgb(220, 156, 80);
    private Button botonEmpezar, botonDetener,botonGuardar;
    private GraficadorXY graficaT, graficaH;
    private int periodoMuestreo = 100;//ms
    private float tiempo;
    private float medidaH,medidaT;
    private int numeroDatos;
    // hilo
    private Thread hilo;
    private boolean ON=false;

    //Manejo de archivos
    private ManejadorArchivosTXT manejadorArchivos;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        gestionandoResolucion();

        //para crear elementos de la GUI
        crearElementosGUI();

        eventos();

        manejadorArchivos = new ManejadorArchivosTXT();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametroLayoutPrincipal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //pegar el contenedor con la GUI
        this.setContentView(gui(), parametroLayoutPrincipal);



    }
    private void gestionandoResolucion(){

        //independencia de la resolución de la pantalla
        tamanoLetraResolucionIncluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;

    }
    private void crearElementosGUI(){

        botonEmpezar = new Button(this);
        botonEmpezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonEmpezar.setText("EMPEZAR");
        botonEmpezar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);

        botonDetener = new Button(this);
        botonDetener.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDetener.setText("DETENER");
        botonDetener.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonDetener.setEnabled(false);


        botonGuardar = new Button(this);
        botonGuardar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonGuardar.setText("GUARDAR");
        botonGuardar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonGuardar.setEnabled(false);

        graficaH = new GraficadorXY(this);
        graficaT = new GraficadorXY(this);



    }
    private LinearLayout gui(){

        //LinearLayoutPrincipal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.BLACK);
        linearPrincipal.setWeightSum(10.0f);

        //primera columna
        LinearLayout linearArriba = new LinearLayout(this);
        linearArriba.setOrientation(LinearLayout.VERTICAL);
        linearArriba.setBackgroundColor(Color.WHITE);
        linearArriba.setWeightSum(10.0f);

        //segunda columna
        LinearLayout linearAbajo = new LinearLayout(this);
        linearAbajo.setOrientation(LinearLayout.HORIZONTAL);
        linearAbajo.setBackgroundColor(Color.YELLOW);
        linearAbajo.setWeightSum(3.0f);

        //pegar columnas a linear principal
        //pegar de primera  y segunda fila en el princpal
        LinearLayout.LayoutParams parametrosPegadoArriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadoArriba.weight = 8.5f;
        parametrosPegadoArriba.setMargins(10,10,10,10);
        linearArriba.setLayoutParams(parametrosPegadoArriba);


        LinearLayout.LayoutParams parametrosPegadoAbajo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadoAbajo.weight = 1.5f;
        parametrosPegadoAbajo.setMargins(10,10,10,10);
        linearAbajo.setLayoutParams(parametrosPegadoAbajo);


        linearPrincipal.addView(linearArriba);
        linearPrincipal.addView(linearAbajo);

        LinearLayout.LayoutParams parametrosPegadoGrafica = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadoGrafica.weight = 5.0f;
        //pegar gráfica a columna izquierda
        linearArriba.addView(graficaH,parametrosPegadoGrafica);
        linearArriba.addView(graficaT,parametrosPegadoGrafica);


        //pegar botones a la columna derecha
        //pegado botones tercera fila
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;


        linearAbajo.addView(botonEmpezar,parametrosPegadoBotones);
        linearAbajo.addView(botonDetener,parametrosPegadoBotones);
        linearAbajo.addView(botonGuardar,parametrosPegadoBotones);


        return linearPrincipal;

    }
    private void eventos(){


        botonEmpezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //Datos nuevos
                ON = true;
                numeroDatos=0;
                tiempo=0f;
                graficaH.borrarDatos();
                graficaT.borrarDatos();
                manejadorArchivos.borrarDatos();
                empezar();
                botonEmpezar.setEnabled(false);
                botonDetener.setEnabled(true);
                botonGuardar.setEnabled(false);

            }
        });


        botonDetener.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ON = false;
                botonEmpezar.setEnabled(true);
                botonDetener.setEnabled(false);
                botonGuardar.setEnabled(true);
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                guardar();
            }
        });
    }
    private void guardar() {

        String carpeta="arduino_android/";
        //código para guardar datos
        manejadorArchivos.guardar(this, carpeta) ;

    }
    private void empezar(){

        arrancar(this);

    }
    public void arrancar(Context context) {

        hilo = new Thread(this);
        hilo.start();

    }
    public void detener() {

        hilo = null;

    }
    public void run() {



        while (numeroDatos< AlmacenDatosRAM.nDatos&&ON==true) {
            try {

                Thread.sleep(periodoMuestreo);
                //Cargar datos al almacen de datos
                cargarDatos();


            } catch (InterruptedException e) {
            }
        }



    }
    private void cargarDatos(){

        periodoMuestreo=AlmacenDatosRAM.periodoMuestreo;
        numeroDatos=numeroDatos+1;
        tiempo=tiempo+periodoMuestreo;
        medidaH=(float)AlmacenDatosRAM.datoActualH;
        medidaT=(float)AlmacenDatosRAM.datoActualT;
        graficaH.setEtiquetas("Tiempo (s)", AlmacenDatosRAM.unidades1);
        graficaT.setEtiquetas("Tiempo (s)", AlmacenDatosRAM.unidades2);
        graficaT.enviarDatos(0.001f * tiempo, medidaT);
        graficaH.enviarDatos(0.001f * tiempo, medidaH);
        manejadorArchivos.llenarDatos(0.001f * tiempo, AlmacenDatosRAM.datoActualH);

    }
    protected void onPause() {
        super.onPause();
        detener();

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


