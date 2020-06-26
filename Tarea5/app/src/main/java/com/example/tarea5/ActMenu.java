package com.example.tarea5;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class ActMenu extends Activity {
    private Button pasaMenu;
    private CheckBox cbsand, cbens, cbpas, cbcafe, cbref, cbte;
    private final String pedido = "pedido";
    private String pedidostr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        cbsand = (CheckBox) findViewById(R.id.cb_sand);
        cbens = (CheckBox) findViewById(R.id.cb_ens);
        cbpas = (CheckBox) findViewById(R.id.cb_pas);
        cbcafe = (CheckBox) findViewById(R.id.cb_cafe);
        cbref = (CheckBox) findViewById(R.id.cb_ref);
        cbte = (CheckBox) findViewById(R.id.cb_te);
        pasaMenu = (Button) findViewById(R.id.btn_dar_dir);
        pasaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muestraVUbicacion();
            }
        });
    }

    //Obtiene los valores de los items seleccionados, esto para cada checkbox asociado, avanza a la siguiente actividad si al menos hay un item seleccionado en platillos o bebidas.
    public void muestraVUbicacion() {
        if (cbens.isChecked()) {
            Spinner spensname = (Spinner) findViewById(R.id.sp_ens_name);
            Spinner spenscount = (Spinner) findViewById(R.id.sp_ens_count);
            pedidostr = "+Ensalada: " + spensname.getSelectedItem().toString() + " | Cantidad: " + spenscount.getSelectedItem().toString() + "\n";
        }
        if (cbsand.isChecked()) {
            Spinner spsandname = (Spinner) findViewById(R.id.sp_sand_name);
            Spinner spsandcount = (Spinner) findViewById(R.id.sp_sand_count);
            if (!"".equals(pedidostr)) {
                pedidostr = pedidostr + "+Sandwich: " + spsandname.getSelectedItem().toString() + " | Cantidad: " + spsandcount.getSelectedItem().toString() + "\n";
            } else {
                pedidostr = "+Sandwich: " + spsandname.getSelectedItem().toString() + " | Cantidad: " + spsandcount.getSelectedItem().toString() + "\n";
            }

        }
        if (cbpas.isChecked()) {
            Spinner sppasname = (Spinner) findViewById(R.id.sp_pas_name);
            Spinner sppascount = (Spinner) findViewById(R.id.sp_pas_count);
            if (!"".equals(pedidostr)) {
                pedidostr = pedidostr + "+Pastel: " + sppasname.getSelectedItem().toString() + " | Cantidad: " + sppascount.getSelectedItem().toString() + "\n";
            } else {
                pedidostr = "+Pastel: " + sppasname.getSelectedItem().toString() + " | Cantidad: " + sppascount.getSelectedItem().toString() + "\n";
            }
        }
        if (cbcafe.isChecked()) {
            Spinner spncafcount = (Spinner) findViewById(R.id.sp_caf_count);
            Spinner spncaf = (Spinner) findViewById(R.id.sp_caf_name);
            if (!"".equals(pedidostr)) {
                pedidostr = pedidostr + "+Cafe: " + spncaf.getSelectedItem().toString() + " | Cantidad: " + spncafcount.getSelectedItem().toString() + "\n";
            } else {
                pedidostr = "+Cafe: " + spncaf.getSelectedItem().toString() + " | Cantidad: " + spncafcount.getSelectedItem().toString() + "\n";
            }
        }
        if (cbref.isChecked()) {
            Spinner spnref = (Spinner) findViewById(R.id.sp_ref_name);
            Spinner spnrefcount = (Spinner) findViewById(R.id.sp_ref_count);
            if (!"".equals(pedidostr)) {
                pedidostr = pedidostr + "+Refresco: " + spnref.getSelectedItem().toString() + " | Cantidad: " + spnrefcount.getSelectedItem().toString() + "\n";
            } else {
                pedidostr = "+Refresco: " + spnref.getSelectedItem().toString() + " | Cantidad: " + spnrefcount.getSelectedItem().toString() + "\n";
            }
        }
        if (cbte.isChecked()) {
            Spinner spnte = (Spinner) findViewById(R.id.sp_te_name);
            Spinner spntecount = (Spinner) findViewById(R.id.sp_te_count);
            if (!"".equals(pedidostr)) {
                pedidostr = pedidostr + "+Te: " + spnte.getSelectedItem().toString() + " | Cantidad: " + spntecount.getSelectedItem().toString() + "\n";
            } else {
                pedidostr = "+Te: " + spnte.getSelectedItem().toString() + " | Cantidad: " + spntecount.getSelectedItem().toString() + "\n";
            }
        }

        //Mantiene en la vista actual si no se ha seleccionado ningun elemento.
        if ("".equals(pedidostr)) {
            Toast.makeText(getApplicationContext(), "Seleccione al menos un elemento del menu para continuar.", Toast.LENGTH_LONG).show();
            return;
        }
        //Finalmente pasa el pedidostr al intent para la actividad de obtener ubicacion.
        Intent intent = new Intent(ActMenu.this, ActUbicacion.class);
        intent.putExtra(pedido, pedidostr);
        startActivity(intent);
        //Elimina posibles animaciones de transiciones entre actividades.
        overridePendingTransition(0, 0);
    }
}