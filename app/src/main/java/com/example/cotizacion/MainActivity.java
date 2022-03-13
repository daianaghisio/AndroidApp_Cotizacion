package com.example.cotizacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daiana Ghisio
 */

public class MainActivity extends AppCompatActivity {

    private Button fetchDataBtn;
    public static TextView fetchDataTxt;
    public static TextView guardadoEnDb;
    public AdminSQLiteOpenHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchDataBtn = (Button) findViewById(R.id.fetchDataBtn);
        fetchDataTxt = (TextView) findViewById(R.id.fetchDataTxt);
        myDB = new AdminSQLiteOpenHelper(this);
        guardadoEnDb = (TextView) findViewById(R.id.guardadoEnDb);

        Intent intent = this.getIntent();


        fetchDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchData process = new FetchData(); //a new FetchData object was created
                process.execute(); //the new object is now being used. Therefore:
                //-data will be shown
                //-dollar official's prices information will be send to MainActivity (with parcelable)

        //MAL  Double compra = intent.getParcelableExtra("compra");
       //MAL   Double venta = intent.getParcelableExtra("venta");

                //Creating current date:
                long dbLong = System.currentTimeMillis();   //Date timestamp = new Date(dbLong)  --> TO RETRIEVE "FECHA"
                //long fecha = dbLong;

                Double compra=0.01; //evidentemente el error esta en el envio de variables de una actividad a la otra
                Double venta=0.02; //datos provisorios para probar el programa
                long fecha=dbLong; //con estas variables provisorias FUNCIONA BIEN




               //Saving the received variables into a new DolarOficial object:
                DolarOficial dolarOficial = new DolarOficial();
                dolarOficial.setCompra(compra); //El ERROR salta aca y ES PORQUE NO LLEGAN LOS DATOS A ESTA CLASE
                dolarOficial.setVenta(venta);
                dolarOficial.setFecha(fecha);



                //Saving the new object into the database:
               Boolean saved = myDB.addDolarOficial(dolarOficial);
               if(saved == true){ //This lets the user know what happened:
                    Toast.makeText(MainActivity.this, "Guardado exitosamente!", Toast.LENGTH_LONG).show();
               } else {
                   Toast.makeText(MainActivity.this, "Error al intentar guardar", Toast.LENGTH_LONG).show();
               }




                //Metodo provisorio para ver si me devuelve los datos buscando por FECHA:3456765
               DolarOficial guardadoEnDb = myDB.getDolarOficial(3456765);
                //NO VA A DEVOLVER NADA PORQUE ESTE METODO NO ESTA TERMINADO
                //PUSE DEVOLVER NULL PARA QUE NO MARCARA EL ERROR ARRIBA PERO HAY QUE VER QUE LO PRODUCE

               //Lo siguiente es para agregarlo a la VISTA
                String texto =" fsdgdsgbsdh ";
               // String texto = "Compra: " + guardadoEnDb.getCompra() + "\n" + "Venta: " + guardadoEnDb.getVenta();

                MainActivity.guardadoEnDb.setText(texto); //Muestra correctamente el texto en la vista


            }
        });
    }
}