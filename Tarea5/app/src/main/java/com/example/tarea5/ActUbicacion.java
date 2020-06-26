package com.example.tarea5;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import androidx.core.content.ContextCompat;
import java.util.List;
import java.util.Locale;

public class ActUbicacion extends Activity implements LocationListener {
    private Button inicioResumen;
    private Button geoLocaliza;
    private EditText editdir;
    private EditText editciu;
    private final String dir = "dir";
    private final String ciu = "ciu";
    private final String pedido = "pedido";
    private String pedidoaresumen;
    protected LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ubicacion);
        inicioResumen = (Button) findViewById(R.id.btn_get_resumen);
        geoLocaliza = (Button) findViewById(R.id.btn_get_ubicacion);
        editdir = (EditText) findViewById(R.id.Editext_dir);
        editciu = (EditText) findViewById(R.id.Editext_ciu);
        Intent intent;
        intent = getIntent();
        pedidoaresumen = intent.getStringExtra(pedido);
        inicioResumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muestraResumen();
            }
        });
        geoLocaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerUbicacion();
            }
        });
    }

    //Checa si los campos no estan vacios, en tal caso avanza a la siguiente actividad, adjuntando como extras dichos campos ademas del pedido hecho anteriormente.
    public void muestraResumen() {
        if ("".equals(editciu.getText().toString()) || "".equals(editdir.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Llene todos los campos para continuar.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(ActUbicacion.this, ActResumen.class);
        intent.putExtra(dir, editdir.getText().toString());
        intent.putExtra(ciu, editciu.getText().toString());
        intent.putExtra(pedido, pedidoaresumen);
        startActivity(intent);
    }

    //Usando LocationManager y sus permiso correspondiente se consigue la ultima actualizacion de la ubicacion del dispositivo.
    //OJO: La ubicacion fue puesta en el emulador anteriormente de forma manual.
    public void obtenerUbicacion() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        } catch (Exception e) {
        }
    }

    //Al tener un cambio de la ubicacion se dan los datos de longitud y latitud de la ubicacion, ademas que se llama al metodo para obtener el nombre de su ciudad.
    @Override
    public void onLocationChanged(Location location) {
        editdir.setText(location.getLatitude() + ", " + location.getLongitude());
        obtenerCiudad(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    //A partir de la latitud y longitud se buscan las posibles locaciones, finalmente a partir de la lista que las tiene se da el primer elemnto y se escribe en su campo correspondiente.
    public void obtenerCiudad(double lat, double lon) {
        Geocoder geo = new Geocoder(this, Locale.getDefault());
        List<Address> direcciones = null;
        try {
            direcciones = geo.getFromLocation(lat, lon, 1);
        } catch (Exception e) {
        }
        if (direcciones != null && direcciones.size() > 0) {
            editciu.setText(direcciones.get(0).getLocality());
        }
    }
}