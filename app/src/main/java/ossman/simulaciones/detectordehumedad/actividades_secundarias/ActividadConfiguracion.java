package ossman.simulaciones.detectordehumedad.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;

public class ActividadConfiguracion extends Activity {

        private EditText editTextMinimo, editTextMaximo, editTextMinimo2, editTextMaximo2, editTextUnidades1,editTextUnidades2, editTextPeriodoMuestreo,
                editTextNumeroDatos;

        private TextView textMinimo, textMaximo, textMinimo2, textMaximo2, textUnidadesH, textUnidadesT, textPeriodoMuestreo, textNumeroDatos,
                espacioUno, espacioDos;

        private int tamanoLetraResolucionIncluida;


        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);

            gestionandoResolucion();

            creacionElementosGUI();

            setContentView(crearGUI());

        }
        private void gestionandoResolucion(){

            //tamano de letra para usar acomodado a la resolución de pantalla
            tamanoLetraResolucionIncluida = AlmacenDatosRAM.tamanoLetraResolucionIncluida;

        }
        private void creacionElementosGUI(){

            textMinimo = new TextView(this);
            textMinimo.setGravity(Gravity.FILL_VERTICAL);
            textMinimo.setBackgroundColor(Color.GREEN);
            textMinimo.setTextSize(tamanoLetraResolucionIncluida);
            textMinimo.setText("  VALOR MÍNIMO HUMEDAD");
            textMinimo.setTextColor(Color.BLACK);

            textMinimo2 = new TextView(this);
            textMinimo2.setGravity(Gravity.FILL_VERTICAL);
            textMinimo2.setBackgroundColor(Color.GREEN);
            textMinimo2.setTextSize(tamanoLetraResolucionIncluida);
            textMinimo2.setText("  VALOR MÍNIMO TEMPERATURA");
            textMinimo2.setTextColor(Color.BLACK);

            espacioUno = new TextView(this);
            espacioUno.setTextSize(tamanoLetraResolucionIncluida);
            espacioUno.setText("    ");

            espacioDos = new TextView(this);
            espacioDos.setTextSize(tamanoLetraResolucionIncluida);
            espacioDos.setText("    ");

            textMaximo = new TextView(this);
            textMaximo.setGravity(Gravity.FILL_VERTICAL);
            textMaximo.setBackgroundColor(Color.YELLOW);
            textMaximo.setTextSize(tamanoLetraResolucionIncluida);
            textMaximo.setText("  VALOR MÁXIMO HUMEDAD");
            textMaximo.setTextColor(Color.BLACK);

            textMaximo2 = new TextView(this);
            textMaximo2.setGravity(Gravity.FILL_VERTICAL);
            textMaximo2.setBackgroundColor(Color.YELLOW);
            textMaximo2.setTextSize(tamanoLetraResolucionIncluida);
            textMaximo2.setText("  VALOR MÁXIMO TEMPERATURA");
            textMaximo2.setTextColor(Color.BLACK);

            textUnidadesH = new TextView(this);
            textUnidadesH.setGravity(Gravity.FILL_VERTICAL);
            textUnidadesH.setBackgroundColor(Color.GREEN);
            textUnidadesH.setTextSize(tamanoLetraResolucionIncluida);
            textUnidadesH.setText("  Magnitud Humedad (unidades)");
            textUnidadesH.setTextColor(Color.BLACK);

            textUnidadesT = new TextView(this);
            textUnidadesT.setGravity(Gravity.FILL_VERTICAL);
            textUnidadesT.setBackgroundColor(Color.GREEN);
            textUnidadesT.setTextSize(tamanoLetraResolucionIncluida);
            textUnidadesT.setText("  Magnitud Temperatura (unidades)");
            textUnidadesT.setTextColor(Color.BLACK);

            textPeriodoMuestreo = new TextView(this);
            textPeriodoMuestreo.setGravity(Gravity.FILL_VERTICAL);
            textPeriodoMuestreo.setBackgroundColor(Color.YELLOW);
            textPeriodoMuestreo.setTextSize(tamanoLetraResolucionIncluida);
            textPeriodoMuestreo.setText("  PERIODO MUESTREO EN ms (Mínimo 200)");
            textPeriodoMuestreo.setTextColor(Color.BLACK);

            textNumeroDatos = new TextView(this);
            textNumeroDatos.setGravity(Gravity.FILL_VERTICAL);
            textNumeroDatos.setBackgroundColor(Color.argb(100, 220, 156, 80));
            textNumeroDatos.setTextSize(tamanoLetraResolucionIncluida);
            textNumeroDatos.setText("  NÚMERO DE DATOS (Maximo 2 000)");
            textNumeroDatos.setTextColor(Color.BLACK);

            editTextMinimo = new EditText(this);
            editTextMinimo.setKeyListener(DigitsKeyListener.getInstance(true, false)); // positive/negative integer numbers.
            editTextMinimo.setTextSize(tamanoLetraResolucionIncluida);
            editTextMinimo.setText("" + AlmacenDatosRAM.minimo);

            editTextMaximo = new EditText(this);
            editTextMaximo.setKeyListener(DigitsKeyListener.getInstance(true, false)); // positive/negative integer numbers.
            editTextMaximo.setTextSize(tamanoLetraResolucionIncluida);
            editTextMaximo.setText("" + AlmacenDatosRAM.maximo);

            editTextMinimo2 = new EditText(this);
            editTextMinimo2.setKeyListener(DigitsKeyListener.getInstance(true, false)); // positive/negative integer numbers.
            editTextMinimo2.setTextSize(tamanoLetraResolucionIncluida);
            editTextMinimo2.setText("" + AlmacenDatosRAM.minimo2);

            editTextMaximo2 = new EditText(this);
            editTextMaximo2.setKeyListener(DigitsKeyListener.getInstance(true, false)); // positive/negative integer numbers.
            editTextMaximo2.setTextSize(tamanoLetraResolucionIncluida);
            editTextMaximo2.setText("" + AlmacenDatosRAM.maximo2);

            //Edita unidades gauge grande
            editTextUnidades1 = new EditText(this);
            editTextUnidades1.setTextSize(tamanoLetraResolucionIncluida);
            editTextUnidades1.setText(AlmacenDatosRAM.unidades1);
            //edit unidades gauge pequeño
            editTextUnidades2 = new EditText(this);
            editTextUnidades2.setTextSize(tamanoLetraResolucionIncluida);
            editTextUnidades2.setText(AlmacenDatosRAM.unidades2);

            editTextNumeroDatos = new EditText(this);
            editTextNumeroDatos.setKeyListener(DigitsKeyListener.getInstance(false, false)); // positive integer numbers.
            editTextNumeroDatos.setTextSize(tamanoLetraResolucionIncluida);
            editTextNumeroDatos.setText("" + AlmacenDatosRAM.nDatos);


            editTextPeriodoMuestreo = new EditText(this);
            editTextPeriodoMuestreo.setKeyListener(DigitsKeyListener.getInstance(false, false)); // positive integer numbers.
            editTextPeriodoMuestreo.setTextSize(tamanoLetraResolucionIncluida);
            editTextPeriodoMuestreo.setText("" + AlmacenDatosRAM.periodoMuestreo);


        }
        private LinearLayout crearGUI() {

            //LinearLayoutPrincipal
            LinearLayout linearLayoutPrincipal = new LinearLayout(this);
            linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
            linearLayoutPrincipal.setBackgroundColor(Color.WHITE);
            linearLayoutPrincipal.setWeightSum(10f);

            //fila uno
            LinearLayout linearFilaUno = new LinearLayout(this);
            linearFilaUno.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaUno.setWeightSum(1.0f);

            //fila dos
            LinearLayout linearFilaDos = new LinearLayout(this);
            linearFilaDos.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaDos.setWeightSum(1.0f);

            //fila tres
            LinearLayout linearFilaTres = new LinearLayout(this);
            linearFilaTres.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaTres.setWeightSum(3.0f);

            //fila cuatro
            LinearLayout linearFilaCuatro = new LinearLayout(this);
            linearFilaCuatro.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaCuatro.setWeightSum(3.0f);

            //fila cinco
            LinearLayout linearFilaCinco = new LinearLayout(this);
            linearFilaCinco.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaCinco.setWeightSum(3.0f);

            //fila seis
            LinearLayout linearFilaSeis = new LinearLayout(this);
            linearFilaSeis.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaSeis.setWeightSum(3.0f);

            //fila siete
            LinearLayout linearFilaSiete = new LinearLayout(this);
            linearFilaSiete.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaSiete.setWeightSum(3.0f);
            //fila ocho
            LinearLayout linearFilaOcho = new LinearLayout(this);
            linearFilaCinco.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaCinco.setWeightSum(3.0f);

            LinearLayout linearFilaNueve = new LinearLayout(this);
            linearFilaNueve.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaNueve.setWeightSum(3.0f);

            LinearLayout linearFilaDiez = new LinearLayout(this);
            linearFilaDiez.setOrientation(LinearLayout.HORIZONTAL);
            //para definir el peso de los elementos que se agregaran a esta fila
            linearFilaDiez.setWeightSum(3.0f);


            linearLayoutPrincipal.addView(linearFilaUno);
            linearLayoutPrincipal.addView(linearFilaDos);
            linearLayoutPrincipal.addView(linearFilaTres);
            linearLayoutPrincipal.addView(linearFilaCuatro);
            linearLayoutPrincipal.addView(linearFilaNueve);
            linearLayoutPrincipal.addView(linearFilaDiez);
            linearLayoutPrincipal.addView(linearFilaCinco);
            linearLayoutPrincipal.addView(linearFilaOcho);
            linearLayoutPrincipal.addView(linearFilaSeis);
            linearLayoutPrincipal.addView(linearFilaSiete);


            //pegado de elementos a fila uno
            LinearLayout.LayoutParams parametrosPegadoElementosFlaUno = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFlaUno.weight = 1.0f;
            linearFilaUno.addView(espacioUno, parametrosPegadoElementosFlaUno);

            //pegado de elementos a fila dos
            LinearLayout.LayoutParams parametrosPegadoElementosFilaDos = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaDos.weight = 1.0f;
            linearFilaDos.addView(espacioDos, parametrosPegadoElementosFilaDos);

            //pegado de elementos a fila tres
            LinearLayout.LayoutParams parametrosPegadoElementosFilaTresIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaTresIzquierda.weight = 2.0f;
            linearFilaTres.addView(textMinimo,parametrosPegadoElementosFilaTresIzquierda);

            LinearLayout.LayoutParams parametrosPegadoElementosFilaTresDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaTresDerecha.weight = 1.0f;
            linearFilaTres.addView(editTextMinimo,parametrosPegadoElementosFilaTresDerecha);

            //pegado de elementos a fila cuatro
            LinearLayout.LayoutParams parametrosPegadoElementosFilaCuatroIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaCuatroIzquierda.weight = 2.0f;
            linearFilaCuatro.addView(textMaximo,parametrosPegadoElementosFilaCuatroIzquierda);

            LinearLayout.LayoutParams parametrosPegadoElementosFilaCuatroDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaCuatroDerecha.weight = 1.0f;
            linearFilaCuatro.addView(editTextMaximo,parametrosPegadoElementosFilaCuatroDerecha);

            linearFilaNueve.addView(textMinimo2,parametrosPegadoElementosFilaTresIzquierda);
            linearFilaNueve.addView(editTextMinimo2,parametrosPegadoElementosFilaTresDerecha);
            linearFilaDiez.addView(textMaximo2,parametrosPegadoElementosFilaCuatroIzquierda);
            linearFilaDiez.addView(editTextMaximo2,parametrosPegadoElementosFilaCuatroDerecha);


            //pegado de elementos a fila cinco
            LinearLayout.LayoutParams parametrosPegadoElementosFilaCincoIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaCincoIzquierda.weight = 2.0f;
            linearFilaCinco.addView(textUnidadesH,parametrosPegadoElementosFilaCincoIzquierda);


            LinearLayout.LayoutParams parametrosPegadoElementosFilaCincoDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaCincoDerecha.weight = 1.0f;
            linearFilaCinco.addView(editTextUnidades1,parametrosPegadoElementosFilaCincoDerecha);
            linearFilaOcho.addView(textUnidadesT,parametrosPegadoElementosFilaCincoIzquierda);
            linearFilaOcho.addView(editTextUnidades2,parametrosPegadoElementosFilaCincoDerecha);


            //pegado de elementos a fila seis
            LinearLayout.LayoutParams parametrosPegadoElementosFilaSeisIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaSeisIzquierda.weight = 2.0f;
            linearFilaSeis.addView( textPeriodoMuestreo,parametrosPegadoElementosFilaSeisIzquierda);

            LinearLayout.LayoutParams parametrosPegadoElementosFilaSeisDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaSeisDerecha.weight = 1.0f;
            linearFilaSeis.addView(editTextPeriodoMuestreo,parametrosPegadoElementosFilaSeisDerecha);

            //pegado de elementos a fila siete
            LinearLayout.LayoutParams parametrosPegadoElementosFilaSieteIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaSieteIzquierda.weight = 2.0f;
            linearFilaSiete.addView(textNumeroDatos,parametrosPegadoElementosFilaSieteIzquierda);

            LinearLayout.LayoutParams parametrosPegadoElementosFilaSieteDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaSieteDerecha.weight = 1.0f;
            linearFilaSiete.addView(editTextNumeroDatos,parametrosPegadoElementosFilaSieteDerecha);



            return linearLayoutPrincipal;

        }
        protected void onPause() {
            super.onPause();

            AlmacenDatosRAM.cofigurar=true;
            AlmacenDatosRAM.unidades1=editTextUnidades1.getText().toString();
            AlmacenDatosRAM.unidades2=editTextUnidades2.getText().toString();
            String valorMinimo = editTextMinimo.getText().toString();
            AlmacenDatosRAM.minimo = Integer.parseInt(valorMinimo);

            String valorMaximo = editTextMaximo.getText().toString();
            AlmacenDatosRAM.maximo = Integer.parseInt(valorMaximo);
            String valorMinimo2 = editTextMinimo2.getText().toString();
            AlmacenDatosRAM.minimo2 = Integer.parseInt(valorMinimo2);

            String valorMaximo2 = editTextMaximo2.getText().toString();
            AlmacenDatosRAM.maximo2 = Integer.parseInt(valorMaximo2);


            String valorMuestreo = editTextPeriodoMuestreo.getText().toString();
            AlmacenDatosRAM.periodoMuestreo = Integer.parseInt(valorMuestreo);

            String valorN = editTextNumeroDatos.getText().toString();
            AlmacenDatosRAM.nDatos= Integer.parseInt(valorN);

        }

}