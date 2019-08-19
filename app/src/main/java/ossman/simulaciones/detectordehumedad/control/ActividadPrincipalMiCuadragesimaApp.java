package ossman.simulaciones.detectordehumedad.control;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import ossman.simulaciones.detectordehumedad.R;
import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;

public class ActividadPrincipalMiCuadragesimaApp extends Activity {

    private ImageView imagen;
    private Button botonEmpezar;
    private int tamanoLetraResolucionIncluida;
    private int COLOR_1= Color.rgb(220, 156, 80);
    private BluetoothAdapter BA;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionandoResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();

        //crear directorio
        crearDirectorio();


    }
    private void gestionandoResolucion(){

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics= this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        int dimension_referencia;

        //tomar el menor entre alto y ancho de pantalla
        if(alto>ancho){

            dimension_referencia=ancho;
        }

        else {

            dimension_referencia=alto;
        }


        //una estimación de un buen tamaño
        int tamano_letra= dimension_referencia / 30;//

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) ((tamano_letra)/ displayMetrics.scaledDensity);
        //para referenciar en todo el software
        AlmacenDatosRAM.tamanoLetraResolucionIncluida=tamanoLetraResolucionIncluida;;

    }
    private void creacionElementosGUI() {

        imagen = new ImageView(this);
        imagen.setImageResource(R.drawable.arduino_android);


        botonEmpezar = new Button(this);
        botonEmpezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonEmpezar.setText("ENTRAR");
        botonEmpezar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);


    }
    private LinearLayout crearGUI() {

        //LinearLayoutPrincipal
        LinearLayout linearLayoutPrincipal = new LinearLayout(this);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.WHITE);
        linearLayoutPrincipal.setWeightSum(10f);


        //LinearLayout primera fila
        LinearLayout linearLayoutPrimeraFila = new LinearLayout(this);
        linearLayoutPrimeraFila.setOrientation(LinearLayout.VERTICAL);


        //LinearLayout segunda fila
        LinearLayout linearLayoutSegundaFila = new LinearLayout(this);
        linearLayoutSegundaFila.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutSegundaFila.setWeightSum(1f);


        //pegar de primera  y segunda fila en el princpal
        LinearLayout.LayoutParams parametrosPrimeraFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrimeraFila.weight = 9.0f;
        linearLayoutPrimeraFila.setLayoutParams(parametrosPrimeraFila);

        LinearLayout.LayoutParams parametrosSegundaFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSegundaFila.weight = 1.0f;
        linearLayoutSegundaFila.setLayoutParams(parametrosSegundaFila);

        linearLayoutPrincipal.addView(linearLayoutPrimeraFila);
        linearLayoutPrincipal.addView(linearLayoutSegundaFila);


        //Adicionar a la primera fila la imagen
        LinearLayout.LayoutParams parametrosPegadoImagen = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutPrimeraFila.setGravity(Gravity.CENTER);
        imagen.setLayoutParams(parametrosPegadoImagen);
        linearLayoutPrimeraFila.addView(imagen);

        //pegado botones segunda fila
        LinearLayout.LayoutParams parametrosPegadoBotones = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadoBotones.weight = 1.0f;
        linearLayoutSegundaFila.addView(botonEmpezar,parametrosPegadoBotones);

        return linearLayoutPrincipal;
    }
    private void eventos(){

        botonEmpezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                activarBluetooth();

                lanzar_nuevo();
            }
        });


    }
    private void activarBluetooth(){

        BA = BluetoothAdapter.getDefaultAdapter();
        //activar bluetooth si está desactivado
        if (!BA.isEnabled()) {
            BA.enable();
        }

    }
    private void lanzar_nuevo(){
        Intent intent = new Intent(this, ActividadComunicacionComoClienteBluetooth.class);
        startActivity(intent);;

    }
    protected void onPause() {
        super.onPause();

    }
    protected void onDestroy() {
        super.onDestroy();
        AlmacenDatosRAM.periodoMuestreo=100;
        AlmacenDatosRAM.maximo=100;
        AlmacenDatosRAM.minimo=0;
        AlmacenDatosRAM.unidades1 ="unidades";
        AlmacenDatosRAM.unidades2 ="unidades";
        AlmacenDatosRAM.nDatos=50;
        BA.disable();
        finish();
    }
    private void crearDirectorio() {

        File path = new File(Environment.getExternalStorageDirectory(), "arduino_android");
        path.mkdirs();

    }

}