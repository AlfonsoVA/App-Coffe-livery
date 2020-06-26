package com.example.tarea5;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ActInicio extends Activity {
    private Button inicioPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio);
        inicioPedido = (Button) findViewById(R.id.btn_inicio_pedido);
        inicioPedido.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                muestraMenu();
            }
        });
    }

    public void muestraMenu() {
        //Pasa a la siguiente actividad que es la seleccion de platillos o bebidas.
        Intent intent = new Intent(ActInicio.this, ActMenu.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
