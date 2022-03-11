package com.example.cotizacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button fetchDataBtn;
    public static TextView fetchDataTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchDataBtn = (Button) findViewById(R.id.fetchDataBtn);
        fetchDataTxt = (TextView) findViewById(R.id.fetchDataTxt);


        public void Register(View view){
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "cotizaciones", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
        }


        fetchDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchData process = new FetchData();
                process.execute();




            }
        });
    }
}