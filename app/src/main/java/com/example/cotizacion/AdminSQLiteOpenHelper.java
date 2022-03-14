package com.example.cotizacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class allows us to create and find data in our database.
 * @author Daiana Ghisio
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DolarOficialTable";
    public static final String TABLE_NAME = "DolarOficialTable";
    public static final String COL_ID = "id";
    public static final String COL_COMPRA = "compra";
    public static final String COL_VENTA = "venta";
    public static final String COL_FECHA = "fecha";

    public AdminSQLiteOpenHelper(@Nullable Context context) { //constructor for db
        super(context, DATABASE_NAME, null, 1);
    } //creates database


    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " Integer PRIMARY KEY, " + COL_FECHA + " long, " + COL_COMPRA + " real, " + COL_VENTA + " real)");

    } //this method creates a new table

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    } //this method drops older table if existed and it creates a new one




     //CREATE
    public boolean addDolarOficial(DolarOficial dolarOficial){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_COMPRA, dolarOficial.getCompra());
        contentValues.put(COL_VENTA, dolarOficial.getVenta());
        contentValues.put(COL_FECHA, dolarOficial.getFecha());

       long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

       if(result == -1){
           return false;
       } else {
           return true;
       }

    }


    //READ (Gets single DolarOficial object)
    public DolarOficial getDolarOficial(String fecha){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID, COL_COMPRA,COL_VENTA, COL_FECHA}, "fecha" + "=?", new String[]{String.valueOf(fecha)}, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
         DolarOficial dolarOf = new DolarOficial(cursor.getDouble(0), cursor.getDouble(1),cursor.getString(2));
         return dolarOf;
    }


    //READ (Gets all)
    public List<DolarOficial> getAllInfo(){
        List<DolarOficial> allInfo = new ArrayList<>();
        String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_ALL_QUERY, null);
        if(cursor.moveToFirst()){
            do {
                DolarOficial dolar = new DolarOficial();
                dolar.setId(cursor.getInt(0));
                dolar.setCompra(cursor.getDouble(1));
                dolar.setVenta(cursor.getDouble(2));
                dolar.setFecha(cursor.getString(3));

                allInfo.add(dolar);
            } while(cursor.moveToNext());
        cursor.close();
        }
        return allInfo;
    }
}