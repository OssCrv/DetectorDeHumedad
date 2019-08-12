package ossman.simulaciones.detectordehumedad.control;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ossman.simulaciones.detectordehumedad.R;
import ossman.simulaciones.detectordehumedad.actividades_secundarias.ActividadEscaneoDispositivos;
import ossman.simulaciones.detectordehumedad.actividades_secundarias.ActividadInstrumentoVirtual;
import ossman.simulaciones.detectordehumedad.comunicaciones.HiloCliente;
import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;

public class ActividadComunicacionComoClienteBluetooth
        extends AppCompatActivity {

    private ImageView imagen;
    private Button botonConectar, botonContinuar,botonBuscar;
    private int tamanoLetraResolucionIncluida;
    private int COLOR_1= Color.rgb(220, 156, 80);

    private StringBuilder recDataString = new StringBuilder();

    private HiloCliente hiloCliente;

    private HiloManejadorAnimacion hilo= new HiloManejadorAnimacion();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        gestionandoResolucion();

        creacionElementosGUI();

        setContentView(crearGUI());

        eventos();

        /*
          Para Android 6.0 y mayores además de solicitar permiso para leer,
          grabar archivos y crear directorios en el archivo manifiesto
           es necesario chuquear el permiso en tiempo de ejecución.
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            chequearExternalStoragePermission();
        }


    }
    private void gestionandoResolucion(){

        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;

    }
    private void creacionElementosGUI() {

        imagen = new ImageView(this);
        imagen.setImageResource(R.drawable.arduino_bluetooth);

        botonBuscar = new Button(this);
        botonBuscar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonBuscar.setText("BUSCAR");
        botonBuscar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);

        botonConectar = new Button(this);
        botonConectar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonConectar.setText("CONECTAR");
        botonConectar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonConectar.setEnabled(false);


        botonContinuar = new Button(this);
        botonContinuar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonContinuar.setText("CONTINUAR");
        botonContinuar.getBackground().setColorFilter(COLOR_1, PorterDuff.Mode.MULTIPLY);
        botonContinuar.setEnabled(false);


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


        //LinearLayout tercera fila
        LinearLayout linearLayoutTerceraFila = new LinearLayout(this);
        linearLayoutTerceraFila.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutTerceraFila.setWeightSum(3f);


        //pegar de primera  y segunda fila en el princpal
        LinearLayout.LayoutParams parametrosPrimeraFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPrimeraFila.weight = 5.0f;
        linearLayoutPrimeraFila.setLayoutParams(parametrosPrimeraFila);

        LinearLayout.LayoutParams parametrosSegundaFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSegundaFila.weight = 4.0f;
        linearLayoutSegundaFila.setLayoutParams(parametrosSegundaFila);

        LinearLayout.LayoutParams parametrosTerceraFila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosTerceraFila.weight = 1.0f;
        linearLayoutTerceraFila.setLayoutParams(parametrosTerceraFila);


        linearLayoutPrincipal.addView(linearLayoutPrimeraFila);
        linearLayoutPrincipal.addView(linearLayoutSegundaFila);
        linearLayoutPrincipal.addView(linearLayoutTerceraFila);


        //Adicionar a la primera fila la imagen
        LinearLayout.LayoutParams parametrosPegadoImagen = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutPrimeraFila.setGravity(Gravity.CENTER);
        imagen.setLayoutParams(parametrosPegadoImagen);
        linearLayoutPrimeraFila.addView(imagen);


        //Aviso
        TextView informacion = new TextView(this);
        informacion.setTextSize(tamanoLetraResolucionIncluida);
        informacion.setTextColor(Color.rgb(100, 100, 100));
        informacion.setGravity(Gravity.CENTER_HORIZONTAL);
        informacion.setText("SI EL BLUETOOTH NO ESTABA ACTIVADO ESTA APP LO ACTIVÓ");

        //Adicionar a la segunda fila la imagen
        LinearLayout.LayoutParams parametrosPegadoAviso = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutSegundaFila.setGravity(Gravity.CENTER);
        informacion.setLayoutParams(parametrosPegadoAviso);
        linearLayoutSegundaFila.addView(informacion);


        //pegado botones tercera fila
        LinearLayout.LayoutParams parametros_pegado_botones = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_botones.weight = 1.0f;

        //Adicionar a la segunda fila los botones
        linearLayoutTerceraFila.addView(botonBuscar,parametros_pegado_botones);
        linearLayoutTerceraFila.addView(botonConectar,parametros_pegado_botones);
        linearLayoutTerceraFila.addView(botonContinuar,parametros_pegado_botones);


        //linear_layout_principal.addView(tabla_layout);
        return linearLayoutPrincipal;
    }
    private void eventos() {


        //evento cliente
        botonBuscar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarBuscandoDispositivos();
                botonConectar.setEnabled(true);
                botonBuscar.setEnabled(false);

            }
        });


        //evento activar
        botonConectar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (botonConectar.getText() == "CONECTAR") {

                    crearCliente();
                    hiloCliente.empezarComunicacionConServidor();
                    botonConectar.setText("EMPEZAR");



                } else {

                    if (hilo == null) {

                        hilo = new HiloManejadorAnimacion();
                    }


                    //crear el hilo
                    hilo.start();
                    lanzarInstrumentacionVirtual();
                    botonConectar.setEnabled(false);
                    botonContinuar.setEnabled(true);

                }


            }
        });


        //evento activar
        botonContinuar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarInstrumentacionVirtual();

            }
        });


    }
    private void lanzarBuscandoDispositivos(){


        Intent intent = new Intent(this, ActividadEscaneoDispositivos.class);
        startActivity(intent);



    }
    private void crearCliente() {

        //dirección del dispositivo elegido para emparejar
        String direccion= AlmacenDatosRAM.direccion;
        hiloCliente = new HiloCliente(direccion);

    }
    private void lanzarInstrumentacionVirtual(){

        Intent intent = new Intent(this, ActividadInstrumentoVirtual.class);
        this.startActivity(intent);

    }

    class HiloManejadorAnimacion extends Thread {
        private boolean corriendo;
        private long periodo_muestreo=50;

        public void detener(){
            corriendo=false;
        }

        @Override
        public void run(){
            corriendo = true;
            while (corriendo){


                try {
                    Thread.sleep(periodo_muestreo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /*
                leer el dato actual que le proporcionó el servidor
                al cliente
                */

                byte[] nuevo_dato_en_byte = hiloCliente.getDato();
                if(nuevo_dato_en_byte!=null) {
                    String cadena_nuevo_dato = new String(nuevo_dato_en_byte, 0, nuevo_dato_en_byte.length);
                    //estructura el dato en el formato requerido por la aplicación
                    seleccionarCadenaDato(cadena_nuevo_dato);//si entra aqui

                }

            }


        }


    }

    private void seleccionarCadenaDato(String cadena_nuevo_dato){


        recDataString.append(cadena_nuevo_dato);

        int endOfLineIndex = recDataString.indexOf(";");
        String dato = "";

        if (endOfLineIndex > 0) {


            dato= recDataString.substring(0, endOfLineIndex);

            almacenarDatoRam(dato);


        }


        recDataString.delete(0, recDataString.length());


    }
    private void almacenarDatoRam(String str) {

        double dato = 0.0;
        boolean esNumeroDato = true;

        String cadena_numero_dato = str;

        //solo si el dato es válido
        try {
            dato = new Double(cadena_numero_dato).doubleValue();

        } catch (NumberFormatException ex) {
            //la cadena recibida no es un número
            esNumeroDato = false;
        }

        //Exportar los datos al almacén y actualizar vista
        if (esNumeroDato == true) {

            AlmacenDatosRAM.datoActual = dato;


        }
    }
    protected void onDestroy() {
        super.onDestroy();
        hilo.detener();
        if(hiloCliente!=null)
            hiloCliente.terminarComunicacionConServidor();
        AlmacenDatosRAM.datoActual=0.0;
        finish();

    }
    private void chequearExternalStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {

        }
    }

}

