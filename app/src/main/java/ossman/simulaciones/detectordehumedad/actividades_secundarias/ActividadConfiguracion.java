package ossman.simulaciones.detectordehumedad.actividades_secundarias;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;

public class ActividadConfiguracion
        extends AppCompatActivity{

        private EditText editTextMinimo, editTextMaximo, editTextUnidades1,editTextUnidades2, editTextPeriodoMuestreo,
                editTextNumeroDatos;

        private TextView textMinimo, textMaximo, textUnidades, textPeriodoMuestreo, textNumeroDatos,
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
            textMinimo.setText("  VALOR MÍNIMO");
            textMinimo.setTextColor(Color.BLACK);

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
            textMaximo.setText("  VALOR MÁXIMO");
            textMaximo.setTextColor(Color.BLACK);

            textUnidades = new TextView(this);
            textUnidades.setGravity(Gravity.FILL_VERTICAL);
            textUnidades.setBackgroundColor(Color.GREEN);
            textUnidades.setTextSize(tamanoLetraResolucionIncluida);
            textUnidades.setText("  Magnitud (unidades)");
            textUnidades.setTextColor(Color.BLACK);

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


            linearLayoutPrincipal.addView(linearFilaUno);
            linearLayoutPrincipal.addView(linearFilaDos);
            linearLayoutPrincipal.addView(linearFilaTres);
            linearLayoutPrincipal.addView(linearFilaCuatro);
            linearLayoutPrincipal.addView(linearFilaCinco);
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


            //pegado de elementos a fila cinco
            LinearLayout.LayoutParams parametrosPegadoElementosFilaCincoIzquierda = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaCincoIzquierda.weight = 2.0f;
            linearFilaCinco.addView(textUnidades,parametrosPegadoElementosFilaCincoIzquierda);

            LinearLayout.LayoutParams parametrosPegadoElementosFilaCincoDerecha = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
            parametrosPegadoElementosFilaCincoDerecha.weight = 1.0f;
            linearFilaCinco.addView(editTextUnidades1,parametrosPegadoElementosFilaCincoDerecha);


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
            AlmacenDatosRAM.unidades2=editTextUnidades1.getText().toString();
            String valorMinimo = editTextMinimo.getText().toString();
            AlmacenDatosRAM.minimo = Integer.parseInt(valorMinimo);

            String valorMaximo = editTextMaximo.getText().toString();
            AlmacenDatosRAM.maximo = Integer.parseInt(valorMaximo);

            String valorMuestreo = editTextPeriodoMuestreo.getText().toString();
            AlmacenDatosRAM.periodoMuestreo = Integer.parseInt(valorMuestreo);

            String valorN = editTextNumeroDatos.getText().toString();
            AlmacenDatosRAM.nDatos= Integer.parseInt(valorN);

        }

}