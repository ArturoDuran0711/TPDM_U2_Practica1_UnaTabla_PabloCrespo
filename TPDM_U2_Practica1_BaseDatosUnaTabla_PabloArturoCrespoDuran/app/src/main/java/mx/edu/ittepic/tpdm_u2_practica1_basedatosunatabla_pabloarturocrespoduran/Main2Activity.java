package mx.edu.ittepic.tpdm_u2_practica1_basedatosunatabla_pabloarturocrespoduran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText dias,descripcion,calorias;
    Button cancelar,aceptar;
    Rutina r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dias=findViewById(R.id.edtDias);
        descripcion=findViewById(R.id.edtDes);
        calorias=findViewById(R.id.edtCalorias);
        cancelar=findViewById(R.id.cancelar);
        aceptar=findViewById(R.id.aceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               r = new Rutina(Main2Activity.this);

                boolean res = r.insertar(new Rutina(0,dias.getText().toString(),descripcion.getText().toString(),Integer.parseInt(calorias.getText().toString())));
                if(res){
                    Toast.makeText(Main2Activity.this,"Se inserto su nueva rutina",Toast.LENGTH_LONG).show();
                    dias.setText("");
                    descripcion.setText("");
                    calorias.setText("");
                }else {
                    Toast.makeText(Main2Activity.this,"ERROR! no se inserto rutina",Toast.LENGTH_LONG).show();
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
