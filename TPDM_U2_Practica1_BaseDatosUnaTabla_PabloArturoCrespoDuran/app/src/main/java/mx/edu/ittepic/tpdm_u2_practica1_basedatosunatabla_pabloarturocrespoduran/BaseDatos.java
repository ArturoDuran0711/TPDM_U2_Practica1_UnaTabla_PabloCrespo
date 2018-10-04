package mx.edu.ittepic.tpdm_u2_practica1_basedatosunatabla_pabloarturocrespoduran;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RUTINA (ID INTEGER PRIMARY KEY AUTOINCREMENT, DIAS VARCHAR(200),DESCRIPCION VARCHAR(200),CALORIASQUEMADAS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
