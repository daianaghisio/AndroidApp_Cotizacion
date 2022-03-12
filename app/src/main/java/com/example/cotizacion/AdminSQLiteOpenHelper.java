package com.example.cotizacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

/**
 * This class allows us to create and edit data in our database.
 * @author Daiana Ghisio
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cotizaciones";
    public static final String TABLE_NAME = "DolarOficialTable";
    public static final String COL_ID = "ID";
    public static final String COL_COMPRA = "Compra";
    public static final String COL_VENTA = "Venta";
    public static final String COL_FECHA = "Fecha";

    public AdminSQLiteOpenHelper(@Nullable Context context) { //constructor for db
        super(context, DATABASE_NAME, null, 1);
    } //creates database


    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table cotizaciones(id int primary key autoincrement, fecha long, compra real, venta real)");
       db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + COL_FECHA + " LONG, " + COL_COMPRA + " REAL, " + COL_VENTA + " REAL)");

    } //this method creates a new table

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME); //drop older table if existed
        onCreate(db); //creates a new table
    } //this method drops older table if existed and it creates a new one


    //CRUD methods:

     //CREATE
    public boolean addDolarOficial(DolarOficial dolarOficial){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_COMPRA, dolarOficial.getCompra());
        contentValues.put(COL_VENTA, dolarOficial.getVenta());
        contentValues.put(COL_FECHA, dolarOficial.getFecha());
        // long dbLong = System.currentTimeMillis();
        //  contentValues.put("fecha", dbLong);

     //Date timestamp = new Date(dbLong)  --> TO RETRIEVE "FECHA"
     //id value is automatically generated?
       long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

       if(result == -1){
           return false;
       } else {
           return true;
       }

    }


    //method for getting(or reading) single DolarOficial object by fecha

    public DolarOficial getDolarOficial(long fecha){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("cotizaciones", new String[]{"id","compra","venta","fecha"}, "fecha" + "=?", new String[]{String.valueOf(fecha)}, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
       // DolarOficial dolarOficial = new DolarOficial(Integer.parseInt(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
        DolarOficial dolarOficial = null; //provisorio hasta encontrar el error arriba
        return dolarOficial;
    }

}