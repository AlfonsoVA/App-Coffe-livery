package com.example.tarea5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActResumen extends Activity {
    private Button abreConfirm;
    private TextView textdir;
    private TextView textciu;
    private TextView textord;
    private final String dir = "dir";
    private final String ciu = "ciu";
    private final String pedido = "pedido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resumen);

        //Obtiene los datos extras del intent a obtener y pasa los datos a las textview correspondientes.
        Intent intent = getIntent();
        String direccion = intent.getStringExtra(dir);
        String ciudad = intent.getStringExtra(ciu);
        String pedidoresumen = intent.getStringExtra(pedido);

        //Obtiene el string de la orden y elimina los datos sobrantes para agregar lo restante a la base de datos.
        String strlistapedido = pedidoresumen.replace("\n", ",").replace("+","").replace(" | Cantidad: ", "-");
        Pedido p = new Pedido(direccion, ciudad, strlistapedido);

        //Usamos la clase para gestionar la base de los pedidos y agregar el que se ha creado despues de pasar los datos necesarios a las textviews correspindientes.
        GestionaPedidoBD gpbd = new GestionaPedidoBD(this);
        textdir = (TextView) findViewById(R.id.Text_dir);
        textdir.setText(p.getDireccion());
        textciu = (TextView) findViewById(R.id.Text_ciu);
        textciu.setText(p.getCiudad());
        textord = (TextView) findViewById(R.id.Text_orden);
        textord.setText(strlistapedido);
        gpbd.openForWrite();
        gpbd.agregaPedido(p);
        abreConfirm = (Button) findViewById(R.id.btn_confirma_pedido);
        abreConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muestraConfirm();
            }
        });

        //Obtenemos el historial de los pedidos realizados antes de dejar de utilizar la clase de gestion para la base.
        ArrayList<String> listaPedido = gpbd.obtenPedidos();
        gpbd.close();
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPedido);
        ListView lv = (ListView) findViewById(R.id.lista_pedidos);
        lv.setAdapter(adaptador);
    }

    public void muestraConfirm(){
        Intent intent = new Intent(ActResumen.this, ActConfirma.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

}
