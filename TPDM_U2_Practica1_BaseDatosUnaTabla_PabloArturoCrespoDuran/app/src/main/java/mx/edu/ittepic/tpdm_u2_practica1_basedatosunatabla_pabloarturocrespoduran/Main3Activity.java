package mx.edu.ittepic.tpdm_u2_practica1_basedatosunatabla_pabloarturocrespoduran;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText dias,descripcion,calorias;
    Button cancelar,eliminar,actualizar;
    Rutina r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dias=findViewById(R.id.edtDias);
        descripcion=findViewById(R.id.edtDes);
        calorias=findViewById(R.id.edtCalorias);
        cancelar=findViewById(R.id.cancelar);
        eliminar=findViewById(R.id.eliminar);
        actualizar=findViewById(R.id.actualizar);

        final int datoId = getIntent().getExtras().getInt("id");
        String datoDias = getIntent().getExtras().getString("dias");
        String datoDes = getIntent().getExtras().getString("descripcion");
        final int datoCalorias = getIntent().getExtras().getInt("calorias");

        r = new Rutina(this);

        dias.setText(datoDias);
        descripcion.setText(datoDes);
        calorias.setText(""+datoCalorias);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(r.eliminar(new Rutina(datoId,dias.getText().toString(),descripcion.getText().toString(),datoCalorias))){
                    Toast.makeText(Main3Activity.this,"SE HA BORRADO LA RUTINA",Toast.LENGTH_LONG).show();
                    dias.setEnabled(false);
                    descripcion.setEnabled(false);
                    calorias.setEnabled(false);
                    actualizar.setEnabled(false);
                    eliminar.setEnabled(false);
                } else {
                    Toast.makeText(Main3Activity.this,"NO ES POSIBLE BORRAR LA RUTINA",Toast.LENGTH_LONG).show();
                }
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respuesta = r.actualizar(new Rutina(datoId,dias.getText().toString(),descripcion.getText().toString(),Integer.parseInt(calorias.getText().toString())));
                if(respuesta){
                    AlertDialog.Builder a = new AlertDialog.Builder(Main3Activity.this);
                    a.setTitle("Rutina Actualizada").setMessage("se actualizo la rutina actual")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(Main3Activity.this);
                    a.setTitle("ERROR!").setMessage("La rutina no se pudo actualizar")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}
