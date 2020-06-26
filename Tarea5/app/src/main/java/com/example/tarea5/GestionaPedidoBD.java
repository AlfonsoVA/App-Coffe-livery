package com.example.tarea5;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GestionaPedidoBD {
    private static final int ver = 1;
    private static final String nombreBD = "pedido.db";
    private static final String tablapedido = "tabla_pedido";
    private static final String colID = "ID";
    private static final int numColID = 0;
    private final String colCiudad = "ciudad";
    private static final int numColCiudad = 1;
    private final String colDireccion = "direccion";
    private static final int numColDireccion = 2;
    private final String colOrden = "orden";
    private static final int numColOrden = 3;
    private SQLiteDatabase bd;
    private BasePedidosSQLite bdp;

    public GestionaPedidoBD(Context context){
        bdp = new BasePedidosSQLite(context, nombreBD, null, ver);
    }

    public void openForWrite(){
        bd = bdp.getWritableDatabase();
    }

    public void close(){
        bdp.close();
    }

    //Agrega un nuevo pedido a la base, adjuntando los datos necesarios.
    public long agregaPedido(Pedido pedido){
        ContentValues content = new ContentValues();
        content.put(colCiudad, pedido.getCiudad());
        content.put(colDireccion, pedido.getDireccion());
        content.put(colOrden, pedido.getOrden());
        return bd.insert(tablapedido, null, content);
    }

    //Obtiene todos los datos de la tabla de pedidos, mostrando su ID, ciudad, direccion y orden, esto por medio de un arraylist<String> con su propio diseño para el texto.
    public ArrayList<String> obtenPedidos(){
        Cursor c = bd.query(tablapedido, new String[]{colID, colCiudad, colDireccion, colOrden}, null, null, null, null, colID + " DESC");
        if(c.getCount() == 0){
            c.close();
            return null;
        }
        ArrayList<String> listaPedidos = new ArrayList<>();
        while(c.moveToNext()){
            String tmp = "";
            tmp = "No. Pedido: " + c.getInt(numColID)
                    + "\nDireccion: " + c.getString(numColDireccion)
                    + "|Ciudad: " + c.getString(numColCiudad)
                    + "\nOrden: " + c.getString(numColOrden);
            listaPedidos.add(tmp);
        }
        c.close();
        return listaPedidos;
    }

}
