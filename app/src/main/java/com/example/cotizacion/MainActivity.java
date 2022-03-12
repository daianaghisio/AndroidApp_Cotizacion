package com.example.cotizacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Daiana Ghisio
 */

public class MainActivity extends AppCompatActivity {

    private Button fetchDataBtn;
    public static TextView fetchDataTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchDataBtn = (Button) findViewById(R.id.fetchDataBtn);
        fetchDataTxt = (TextView) findViewById(R.id.fetchDataTxt);

        AdminSQLiteOpenHelper myDB = new AdminSQLiteOpenHelper(this);
        Intent intent = this.getIntent();


        fetchDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchData process = new FetchData(); //a new FetchData object was created
                process.execute(); //the new object is now being used. Therefore:
                //-data will be shown
                //-dollar official's prices information will be received in MainActivity (parcelable)

                Double compra = intent.getParcelableExtra("compra");
                Double venta = intent.getParcelableExtra("venta");

                //Creating current date:
                long dbLong = System.currentTimeMillis();
                long fecha = dbLong;

               //Saving the received variables into a new DolarOficial object:
                DolarOficial dolarOficial1 = new DolarOficial();
                dolarOficial1.setCompra(compra);
                dolarOficial1.setVenta(venta);
                dolarOficial1.setFecha(fecha);

                //Saving the new object into the database:
                myDB.addDolarOficial(dolarOficial1);

            }
        });
    }
}