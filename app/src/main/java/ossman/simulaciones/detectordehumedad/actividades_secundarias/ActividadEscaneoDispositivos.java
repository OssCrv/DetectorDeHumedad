package ossman.simulaciones.detectordehumedad.actividades_secundarias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.curso_simulaciones.comunicaciones.bluetooth.ScannerBluetooth;

import ossman.simulaciones.detectordehumedad.datos.AlmacenDatosRAM;

public class ActividadEscaneoDispositivos
        extends AppCompatActivity {


    ScannerBluetooth scanear;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scanear = new ScannerBluetooth(this);
        scanear.descubriendoDispositivos();
    }

    @Override public void onPause() {
        super.onPause();
        AlmacenDatosRAM.direccion = scanear.direccion;
    }
}

