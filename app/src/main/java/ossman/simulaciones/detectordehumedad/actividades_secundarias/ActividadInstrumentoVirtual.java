package ossman.simulaciones.detectordehumedad.actividades_secundarias;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;
import ossman.simulaciones.detectordehumedad.instrumento_virtual.GaugeCompuesto;

public class ActividadInstrumentoVirtual extends AppCompatActivity implements Runnable{

    private GaugeCompuesto tacometro;
    private Button botonGrafica,botonTabla,botonConfigurar;
    private int tamanoLetraResolucionIncluida;

    private Thread hilo;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionandoResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();


        arrancar(this);


    }
    private void gestionandoResolucion(){

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;

    }
    private void creacionElementosGUI() {

        tacometro = new GaugeCompuesto(this);
        tacometro.setUnidades1(AlmacenDatosRAM.unidades1);
        tacometro.setUnidades2(AlmacenDatosRAM.unidades2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        botonGrafica = new Button(this);
        botonGrafica.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonGrafica.setTextColor(Color.BLACK);
        botonGrafica.setText("GRÁFICA");
        botonGrafica.getBackground().setColorFilter(Color.rgb(220, 156, 80), PorterDuff.Mode.MULTIPLY);

        botonTabla = new Button(this);
        botonTabla.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonTabla.setTextColor(Color.BLACK);
        botonTabla.setText("TABLA");
        botonTabla.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);

        botonConfigurar = new Button(this);
        botonConfigurar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonConfigurar.setTextColor(Color.BLACK);
        botonConfigurar.setText("CONFIGURAR");
        botonConfigurar.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
    }
    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linearLayoutPrincipal = new LinearLayout(this);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.WHITE);
        linearLayoutPrincipal.setWeightSum(10f);

        //primera fila
        LinearLayout linearLayoutFilaUno = new LinearLayout(this);
        linearLayoutFilaUno.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutFilaUno.setWeightSum(3f);

        //segunda fila
        LinearLayout linearLayoutFilaDos= new LinearLayout(this);
        linearLayoutFilaDos.setOrientation(LinearLayout.VERTICAL);

        //pegar de primera  y segunda fila en el princpal
        LinearLayout.LayoutParams parametrosPrimeraFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrimeraFila.weight = 1.0f;
        linearLayoutFilaUno.setLayoutParams(parametrosPrimeraFila);

        LinearLayout.LayoutParams parametrosSegundaFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSegundaFila.weight = 9.0f;
        linearLayoutFilaDos.setLayoutParams(parametrosSegundaFila);

        linearLayoutPrincipal.addView(linearLayoutFilaUno);
        linearLayoutPrincipal.addView(linearLayoutFilaDos);

        //pegado botones a la primera fila
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;

        //Adicionar a la segunda fila los botones
        linearLayoutFilaUno.addView(botonConfigurar, parametrosPegadoBotones);
        linearLayoutFilaUno.addView(botonGrafica, parametrosPegadoBotones);
        linearLayoutFilaUno.addView(botonTabla, parametrosPegadoBotones);

        //pegar tacometro a segunda fila
        linearLayoutFilaDos.addView(tacometro);

        return linearLayoutPrincipal;
    }
    private void eventos(){

        botonConfigurar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzar_actividad_configuracion();
            }
        });

        botonGrafica.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                lanzar_actividad_grafica();

            }
        });

        botonTabla.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                lanzar_actividad_tabla();

            }
        });

    }
    private void  lanzar_actividad_configuracion(){

        Intent intent = new Intent(this, ActividadConfiguracion.class);
        startActivity(intent);
    }
    private void  lanzar_actividad_grafica(){

        Intent intent = new Intent(this, ActividadGrafica.class);
        startActivity(intent);
    }
    private void  lanzar_actividad_tabla(){

        Intent intent = new Intent(this, ActividadTabla.class);
        startActivity(intent);
    }
    public void arrancar(Context context) {

        hilo = new Thread(this);
        hilo.start();
    }
    public void detener() {

        hilo = null;

    }
    public void run() {

        while (true) {
            try {

                Thread.sleep(AlmacenDatosRAM.periodoMuestreo);
                //Cargar datos al almacen de datos
                tacometro.setMedida1((float)AlmacenDatosRAM.datoActual);


            } catch (InterruptedException e) {
            }
        }


    }
    protected void onResume() {
        super.onResume();
        tacometro.setUnidades1(AlmacenDatosRAM.unidades1);
        tacometro.setUnidades2(AlmacenDatosRAM.unidades2);
        tacometro.setRango1(AlmacenDatosRAM.minimo,AlmacenDatosRAM.maximo);

    }
    protected void onPause() {
        super.onPause();
        detener();
        this.finish();

    }
}

