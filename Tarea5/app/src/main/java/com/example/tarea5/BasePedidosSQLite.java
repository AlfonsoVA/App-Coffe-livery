package com.example.tarea5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BasePedidosSQLite extends SQLiteOpenHelper {
    private final String tablaPedidos = "tabla_pedido";
    private final String colID = "ID";
    private final String orden = "orden";
    private final String ciudad = "ciudad";
    private final String direccion = "direccion";
    //Se crea la tabla de pedidos, la cual contendra su ID, ciudad, direccion y la orden en general.
    private final String creaBD = "CREATE TABLE " + tablaPedidos + " (" + colID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  ciudad + " TEXT NOT NULL, " + direccion + " TEXT NOT NULL, " + orden + " TEXT NOT NULL);";

    public BasePedidosSQLite(Context context, String name, CursorFactory cf, int version){
        super(context, name, cf, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd){
        bd.execSQL(creaBD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion){
        bd.execSQL("DROP TABLE " + tablaPedidos);
        onCreate(bd);
    }
}
