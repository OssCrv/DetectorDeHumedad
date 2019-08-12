package ossman.simulaciones.detectordehumedad.instrumento_virtual;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class GaugeCompuesto extends View {


    private float minimo=-0;
    private float  maximo=100f;
    private float medida1=70.0f;
    private float medida2=70.0f;
    private String unidades1="UNIDADES";
    private String unidades2="UNIDADES";
    private int colorPrimerTercio1= Color.rgb(200,200,0);
    private int colorSegundoTercio1= Color.rgb(0,180,0);
    private int colorTercerTercio1= Color.RED;

    private int colorPrimerTercio2= Color.rgb(200,200,0);
    private int colorSegundoTercio2= Color.rgb(0,180,0);
    private int colorTercerTercio2= Color.RED;
    private int colorLineas=Color.BLACK;
    private int colorFondo1=Color.WHITE;
    private int colorFondo2=Color.WHITE;
    private int colorTablerroDespliegue=Color.BLACK;
    private int colorNumerosDesplieggue=Color.WHITE;


    public GaugeCompuesto(Context context){

        super(context);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }

    }
    public void setRango1(float minimo, float maximo){

        this.minimo=minimo;
        this.maximo=maximo;

    }
    public void setRango2(float minimo, float maximo){

        this.minimo=minimo;
        this.maximo=maximo;

    }
    public void setMedida1(float medida){

        this.medida1=medida1;

    }
    public void setMedida2(float medida){

        this.medida2=medida2;

    }
    public float getMedida1(){

        return medida1;
    }
    public float getMedida2(){

        return medida2;
    }
    public void setUnidades1 (String unidades1){

        this.unidades1=unidades1;

    }
    public void setUnidades2 (String unidades2){

        this.unidades2=unidades2;

    }

    public  void setColorSectores1(int colorPrimerTercio1, int colorSegundoTercio1, int colorTercerTercio1){

        this.colorPrimerTercio1=colorPrimerTercio1;
        this.colorSegundoTercio1=colorSegundoTercio1;
        this.colorTercerTercio1=colorTercerTercio1;

    }
    public  void setColorSectores2(int colorPrimerTercio2, int colorSegundoTercio2, int colorTercerTercio2){

        this.colorPrimerTercio2=colorPrimerTercio2;
        this.colorSegundoTercio2=colorSegundoTercio2;
        this.colorTercerTercio2=colorTercerTercio2;

    }
    public void setColorFondoTacometro1(int colorFondo1){

        this.colorFondo1=colorFondo1;


    }
    public void setColorFondoTacometro2(int colorFondo2){

        this.colorFondo2=colorFondo2;


    }
    public void setColorLineasTacometro(int color_lineas){

        this.colorLineas=color_lineas;


    }
    public void setColorTableroDespliegue(int colorTableroDespliegue){

        this.colorTablerroDespliegue=colorTableroDespliegue;

    }
    public void setColorNumeroDespliegue(int colorNumerosDesplieggue){

        this.colorNumerosDesplieggue=colorNumerosDesplieggue;

    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        float ancho=this.getWidth();
        float alto=this.getHeight();
        float largo=0f;

        if(ancho>alto){

            largo=0.8f*alto;

        } else {

            largo=0.8f*ancho;


        }
// dibujar Gauge1 grande
        canvas.translate(0.5f*ancho,0.5f*alto);
        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);
        //tamaño texto
        pincel.setTextSize(0.05f*largo);
        //para mejor manejo de la métrica de texto
        pincel.setLinearText(true);
        //para efectos de buen escalado de bitmaps
        pincel.setFilterBitmap(true);
        //para buen manejo de gradientes de color
        pincel.setDither(true);
        float esquinaSuperiorIzquierdaX=-0.5f*largo;
        float esquinaSuperiorIzquierdaY=-0.5f*largo;
        float esquinaInferiorDerechaX=0.5f*largo;
        float esquinaInferiorDerechaY=0.5f*largo;

        RectF rect = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);

        pincel.setStyle(Paint.Style.FILL);

       /*
        Partamos en tres segmentos de diferente color.
        Sólo este pedacito de código es diferente
        al del método anterior
       */

        pincel.setColor(colorPrimerTercio1);
        canvas.drawArc(rect, 145, 100, true, pincel);
        pincel.setColor(colorSegundoTercio1);
        canvas.drawArc(rect, 245, 100, true, pincel);
        pincel.setColor(colorTercerTercio1);
        canvas.drawArc(rect, 345, 50, true, pincel);

        float radio=(float)(0.45*largo);
        pincel.setColor(colorFondo1);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(colorLineas);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setStrokeWidth(1f);
        canvas.drawCircle(0, 0, 0.5f*largo, pincel);

      /*
        Hacer las divisiones
      */

        pincel.setStrokeWidth(0.005f*largo);

       /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
         Se aprovechará para marcar
       */

        float indent = (float)(0.2*largo);
        float posicionY= (float)(0.5*largo);

        for (int i=0;i<6;i=i+1){
            float anguloRotacion=235+50*i;
            canvas.rotate(anguloRotacion,0,0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);

            //marcas
            // Centrar números con las divisiones grandes
            int valorIncrementoMarcas= (int)((maximo-minimo)/5f);
            int valorMarca= (int)(minimo +valorIncrementoMarcas*i);
            String numero=""+valorMarca;
            float anchoCadenaNumero = pincel.measureText(numero);
            float posicionXNumero=-0.5f*anchoCadenaNumero;
            float posicionYNumero= (float)(-posicionY+1.2*indent);
            //dibujar los números
            pincel.setStyle(Paint.Style.FILL);
            canvas.drawText(numero, posicionXNumero, posicionYNumero, pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

        }


        /*
         Divisiones pequeñas
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 10 grados comenzando
         en 235 grados.
         */
        pincel.setStyle(Paint.Style.STROKE);
        for (int i=0;i<26;i=i+1){

            float anguloRotacion=235+10*i;
            canvas.rotate(anguloRotacion,0,0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + (float)(0.2*indent), pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

        }


        //unidades
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(colorLineas);
        float anchoCadenaUnidades = pincel.measureText(unidades1);
        canvas.drawText(unidades1, -0.5f * anchoCadenaUnidades, -0.15f*largo, pincel);
        //pantalllita de despliegue
        float anchoCadenaNumero = pincel.measureText(""+medida1);
        RectF rect_1 = new RectF(-0.12f*largo, -0.13f*largo,0.12f*largo,-0.04f*largo);
        pincel.setColor(colorTablerroDespliegue);
        canvas.drawRect(rect_1, pincel);
        pincel.setColor(colorNumerosDesplieggue);
        canvas.drawText("" + medida1, -0.5f * anchoCadenaNumero, -0.07f*largo, pincel);

        //Dibujar aguja
        pincel.setStrokeWidth(0.005f*largo);
        pincel.setColor(Color.RED);
        float angulo_rotacion_medida=235+(250f/(maximo-minimo))*(medida1-minimo);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        canvas.drawLine(0, -posicionY, 0, 0, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, (float) (0.1 * indent), pincel);

        canvas.restore();



        invalidate();//para animación

// Gauge pequeño
        canvas.save();
        canvas.translate(0.5f*ancho,0.63f*alto);
//evita efecto sierra
        pincel.setAntiAlias(true);
        //tamaño texto
        pincel.setTextSize(0.04f*largo);
        //para mejor manejo de la métrica de texto
        pincel.setLinearText(true);
        //para efectos de buen escalado de bitmaps
        pincel.setFilterBitmap(true);
        //para buen manejo de gradientes de color
        pincel.setDither(true);

        pincel.setStyle(Paint.Style.FILL);

        esquinaSuperiorIzquierdaX=-0.2f*largo;
        esquinaSuperiorIzquierdaY=-0.2f*largo;
        esquinaInferiorDerechaX=0.2f*largo;
        esquinaInferiorDerechaY=0.2f*largo;

        RectF rect1 = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);

       /*
        Partamos en tres segmentos de diferente color.
        Sólo este pedacito de código es diferente
        al del método anterior
       */

        pincel.setColor(colorPrimerTercio2);
        canvas.drawArc(rect1, 145, 100, true, pincel);
        pincel.setColor(colorSegundoTercio2);
        canvas.drawArc(rect1, 245, 100, true, pincel);
        pincel.setColor(colorTercerTercio2);
        canvas.drawArc(rect1, 345, 50, true, pincel);

        radio=0.17f*largo;
        pincel.setColor(colorFondo1);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(colorLineas);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setStrokeWidth(1f);
        canvas.drawCircle(0, 0, 0.20f*largo, pincel);

/*
        Hacer las divisiones
      */

        pincel.setStrokeWidth(0.003f*largo);

       /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
         Se aprovechará para marcar
       */
        indent = (float)(0.06*largo);
        posicionY= (float)(0.2*largo);

        for (int i=0;i<6;i=i+1){
            float anguloRotacion=235+50*i;
            canvas.rotate(anguloRotacion,0,0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);

            //marcas
            // Centrar números con las divisiones grandes
            int valorIncrementoMarcas= (int)((maximo-minimo)/5f);
            int valorMarca= (int)(minimo +valorIncrementoMarcas*i);
            String numero=""+valorMarca;
            anchoCadenaNumero = pincel.measureText(numero);
            float posicionXNumero=-0.5f*anchoCadenaNumero;
            float posicionYNumero= (float)(-posicionY+1.5*indent);
            //dibujar los números
            pincel.setStyle(Paint.Style.FILL);
            canvas.drawText(numero, posicionXNumero, posicionYNumero, pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

        }


        /*
         Divisiones pequeñas
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 10 grados comenzando
         en 235 grados.
         */
        pincel.setStyle(Paint.Style.STROKE);
        for (int i=0;i<26;i=i+1){

            float anguloRotacion=235+10*i;
            canvas.rotate(anguloRotacion,0,0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + (float)(0.2*indent), pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

        }

        //Dibujar aguja
        pincel.setStrokeWidth(0.005f*largo);
        pincel.setColor(Color.RED);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        canvas.drawLine(0, -posicionY, 0, 0, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, (float) (0.1 * indent), pincel);
        //unidades
        pincel.setColor(colorLineas);
        anchoCadenaUnidades = pincel.measureText(unidades2);
        canvas.drawText(unidades2, -0.5f * anchoCadenaUnidades, 0.05f*largo, pincel);
        //pantalllita de despliegue
        RectF rect_2 = new RectF(-0.06f*largo, 0.06f*largo,0.06f*largo,0.12f*largo);
        anchoCadenaNumero = pincel.measureText(""+medida2);
        pincel.setColor(colorTablerroDespliegue);
        canvas.drawRect(rect_2, pincel);
        pincel.setColor(colorNumerosDesplieggue);
        canvas.drawText("" + medida2, -0.5f * anchoCadenaNumero, 0.11f*largo, pincel);


        canvas.restore();



        invalidate();//para animación



    }
}
