package mx.edu.ittepic.tpdm_u2_practica1_basedatosunatabla_pabloarturocrespoduran;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                Rutina rut = new Rutina(MainActivity.this);
                final Rutina rutinas[] = rut.consulta();
                final int dato = i;

                alerta.setTitle("Detalles de rutina: "+rutinas[dato].id)
                        .setMessage("Dias: "+rutinas[dato].dias+"\nDescripción: "+rutinas[dato].descripcion+"\nCalorias a quemar: "+rutinas[dato].calorias+"\n\n¿Deseas eliminar o modificar?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ift) {

                                Intent nuevo = new Intent(MainActivity.this, Main3Activity.class);
                                nuevo.putExtra("id",rutinas[dato].id);
                                nuevo.putExtra("dias",rutinas[dato].dias);
                                nuevo.putExtra("descripcion",rutinas[dato].descripcion);
                                nuevo.putExtra("calorias",rutinas[dato].calorias);

                                startActivity(nuevo);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acercaDe) {
            AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
            a.setTitle("Acerca de...")
                    .setMessage("Esta aplicación almacena cada una de tus rutinas\n\nPor: Pablo Arturo Crespo Durán\n13400397\nInstituto Tecnologico de Tepic")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }
        if (id == R.id.salir) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onStart(){
        super.onStart();
        Rutina rut = new Rutina(this);
        Rutina rutinas[] = rut.consulta();
        if(rutinas==null){
            Toast.makeText(this,"NO HAY RUTINAS",Toast.LENGTH_LONG).show();
        } else {
            String NoRut[] = new String[rutinas.length];
            for (int i = 0; i < NoRut.length; i++) {
                NoRut[i] = "No. Dias: "+rutinas[i].dias + "\nDescripcion: " + rutinas[i].descripcion+"\nCalorias quemadas: "+rutinas[i].calorias;
            }
            ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NoRut);
            lista.setAdapter(adap);
        }
    }
}
