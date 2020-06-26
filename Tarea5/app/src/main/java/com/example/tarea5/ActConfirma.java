package com.example.tarea5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActConfirma extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button finalizaPedido, verHistorial;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirma);
        finalizaPedido = (Button) findViewById(R.id.btn_terminar_pedido);
        finalizaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cierraApp();
            }
        });
    }

    //Cierra la aplicacion, para volver a ejecutarla se tiene que abrir nuevamente.
    public void cierraApp(){
        Intent intent = new Intent(ActConfirma.this, ActInicio.class);
        startActivity(intent);
    }

}

