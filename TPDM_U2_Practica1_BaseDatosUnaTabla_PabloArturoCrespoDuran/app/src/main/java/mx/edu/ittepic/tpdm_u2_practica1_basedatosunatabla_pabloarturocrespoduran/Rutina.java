package mx.edu.ittepic.tpdm_u2_practica1_basedatosunatabla_pabloarturocrespoduran;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by Usuario on 04/10/2018.
 */

public class Rutina {
    int id,calorias;
    String dias,descripcion;
    BaseDatos bd;

    public Rutina(int id, String dias, String descripcion, int calorias){
        this.id = id;
        this.dias = dias;
        this.descripcion = descripcion;
        this.calorias = calorias;
    }
    public Rutina(Activity activity){
        bd = new BaseDatos(activity,"misRutinas",null,1);
    }

    public boolean insertar(Rutina rutina){
        try{
            SQLiteDatabase tabla = bd.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("DIAS",rutina.dias);
            data.put("DESCRIPCION",rutina.descripcion);
            data.put("CALORIASQUEMADAS",rutina.calorias);
            long res = tabla.insert("RUTINA","ID",data);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            Log.e("ERROR: ",e.getMessage());
            return false;
        }
        return true;
    }

    public Rutina[] consulta(){
        Rutina[] resultado=null;
        try{
            SQLiteDatabase tabla = bd.getReadableDatabase();
            String SQL = "SELECT * FROM RUTINA";

            Cursor c = tabla.rawQuery(SQL,null);
            if(c.moveToFirst()){
                resultado = new Rutina[c.getCount()];
                int i=0;
                do {
                    resultado[i++] = new Rutina(c.getInt(0), c.getString(1), c.getString(2),c.getInt(3));
                }while(c.moveToNext());
            }
            tabla.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean actualizar(Rutina rutina){
        try{
            SQLiteDatabase tabla = bd.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("DIAS",rutina.dias);
            data.put("DESCRIPCION",rutina.descripcion);
            data.put("CALORIASQUEMADAS",rutina.calorias);
            String[] clave = {""+rutina.id};
            long res = tabla.update("RUTINA",data,"ID=?",clave);
            tabla.close();
            if(res<0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    public boolean eliminar(Rutina rutina){
        try{
            SQLiteDatabase tabla = bd.getWritableDatabase();
            String[] data = {""+rutina.id};
            long res = tabla.delete("RUTINA","ID=?",data);
            tabla.close();
            if(res==0){
                return false;
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }


}
