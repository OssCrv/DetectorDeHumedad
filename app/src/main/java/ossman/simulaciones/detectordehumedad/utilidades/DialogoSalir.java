package ossman.simulaciones.detectordehumedad.utilidades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;

public class DialogoSalir extends AlertDialog.Builder {


    private String nombre="OK";
    private ScrollView scroller;
    private Button boton_positivo,boton_negativo;
    private AlertDialog alerta;
    private TextView aviso,titulo;
    private int tamano_letra=12;
    private Activity actividad;

    public DialogoSalir(Activity actividad){
        super(actividad);
        this.actividad=actividad;

        gestionarResolucion();

        scroller = new ScrollView(actividad);
        aviso = new TextView(actividad);
        titulo = new TextView(actividad);


        this.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                finalizar();

            }
        });


        this.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        alerta = this.create();
        alerta.setCancelable(false);

        titulo.setText("ADVERTENCIA");
        titulo.setBackgroundColor(Color.YELLOW);
        titulo.setTextColor(Color.BLACK);
        titulo.setGravity(Gravity.CENTER);
        titulo.setPadding(10, 0, 0, 0);
        titulo.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 1.5f * tamano_letra);
        alerta.setCustomTitle(titulo);

        scroller.addView(aviso);
        aviso.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, tamano_letra);
        aviso.setLineSpacing(8, 1);
        aviso.setPadding(20, 20, 20, 20);

        setMensaje();

        alerta.setView(scroller);

        alerta.show();


        boton_positivo = alerta.getButton(DialogInterface.BUTTON_POSITIVE);
        boton_positivo.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, tamano_letra);

        boton_negativo = alerta.getButton(DialogInterface.BUTTON_NEGATIVE);
        boton_negativo.setTextSize(android.util.TypedValue. COMPLEX_UNIT_SP, tamano_letra);

    }
    private void gestionarResolucion(){

        tamano_letra= AlmacenDatosRAM.tamanoLetraResolucionIncluida;


    }
    private void finalizar(){

        actividad.finish();

    }
    private void setMensaje(){

        aviso.setBackgroundColor(Color.BLACK);
        Spanned spannedValue = Html.fromHtml(advertencia(), getImageHTML(), null);
        aviso.setText(spannedValue);

    }
    private Html.ImageGetter getImageHTML() {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                int resourceId = actividad.getResources().getIdentifier(source, "drawable",actividad.getPackageName());
                Drawable drawable = actividad.getResources().getDrawable(resourceId);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        return imageGetter;
    }
    private String advertencia() {

        String nota = "<body>" +
                "¿Desea Salir?" +
                "<BR>" +
                " Si tiene datos y no se han guardado se perderán"
                +
                "</body>";

        return nota;

    }
}


