package com.example.cotizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Daiana Ghisio
 */

public class MainActivity extends AppCompatActivity {

    private Button fetchDataBtn;
    private Button searchBtn;
    private EditText editTextComp;
    public static TextView fetchDataTxt;
    public static TextView savedOnDb;
    public AdminSQLiteOpenHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchDataBtn = (Button) findViewById(R.id.fetchDataBtn);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        fetchDataTxt = (TextView) findViewById(R.id.fetchDataTxt);
        myDB = new AdminSQLiteOpenHelper(this);
        savedOnDb = (TextView) findViewById(R.id.guardadoEnDb);
        editTextComp = (EditText) findViewById(R.id.editTextComponent);

        // Intent intent = this.getIntent();

        fetchDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchData process = new FetchData(); //a new FetchData object was created
                process.execute(); //the new object is now being used. Therefore:
                //-data will be shown
                //-dollar official's prices information will be send to MainActivity (with parcelable)


              Date fecha = Calendar.getInstance().getTime();
              String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(fecha);


                //VARIABLES PROVISORIAS
                //Problemas: -no pude extraer los datos del objeto "casa" en FetchData
                //           -tengo que volver a investigar como se envian datos con parcelable
                Double compra=0.01;
                Double venta=0.02;


               //Saving the received variables into a new DolarOficial object:
                DolarOficial dolarOficial = new DolarOficial();
                dolarOficial.setCompra(compra);
                dolarOficial.setVenta(venta);
                dolarOficial.setFecha(fecha1);

                //Saving the new object into the database:
               Boolean saved = myDB.addDolarOficial(dolarOficial);
               if(saved == true){ //This lets the user know what happened:
                    Toast.makeText(MainActivity.this, "Guardado exitosamente!", Toast.LENGTH_LONG).show();
               } else {
                   Toast.makeText(MainActivity.this, "Error al intentar guardar", Toast.LENGTH_LONG).show();
               }
            }
        }); //first onClickListener ends


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String fechaBusqueda = editTextComp.getText().toString();

                //The following receives from the user the string "fechaBusqueda" and returns an object with the search results:
                DolarOficial savedOnDb = myDB.getDolarOficial(fechaBusqueda);

                //Show results:
                String text = "Resultados de la busqueda por fecha: "+ fechaBusqueda +"\n"+ "Compra: " + savedOnDb.getCompra() + "\n" + "Venta: " + savedOnDb.getVenta();
                MainActivity.savedOnDb.setText(text);


            }
        }); //second onClickListener ends



    }//oncreate ends
}//class ends