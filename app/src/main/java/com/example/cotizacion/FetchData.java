package com.example.cotizacion;

import android.content.Intent;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class gives the application access to information from the internet.
 * @author Daiana Ghisio
 */

public class FetchData extends AsyncTask<Void, Void, Void> {

String data ="";
String dataUrl = "https://www.dolarsi.com/api/api.php?type=valoresprincipales";
String singleParsed="";
String dataParsed="";

Double compra = 0.00;
Double venta = 0.00;
long fecha = 65435789;

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            URL url = new URL(dataUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream  = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line!=null){
                line = bufferedReader.readLine();
                data = data+line;
            }
            JSONArray jsonArray = new JSONArray(data); //"data" is a String that contains the complete information that comes from the Json

            for (int i=0; i<jsonArray.length();i++){ //Our array has 8 indexes, each of one of them contain an object "casa"

                    JSONObject jsonObject = (JSONObject) jsonArray.get(i); //this jsonObject gets "casa" from the array

                 //for each "casa", we create the object "atributes" which turns "casa into an object, before it was only a string.
                    JSONObject atributes = (JSONObject) jsonObject.get("casa");

                // To extract the information we are interested on:
                    singleParsed = "Nombre: " + atributes.get("nombre") + "\n" + "Valor para la compra: " + atributes.get("compra") + "\n" + "Valor para la venta: " + atributes.get("venta");
                    dataParsed = dataParsed + singleParsed + "\n";

            }
            //The information we need to SAVE (dolar oficial) comes from index 0:
            JSONObject jsonObjectDO = (JSONObject) jsonArray.get(0);
            JSONObject atributesDO = (JSONObject) jsonObjectDO.get("casa");

            compra = (Double) atributesDO.get("compra");
            venta = (Double) atributesDO.get("venta");
           //generating current date:
           long dbLong = System.currentTimeMillis();
           fecha = dbLong;

            Intent intent = new Intent();
            intent.putExtra("compra", compra); // I'm sending these variables 1 by 1 to my MainActivity class
             intent.putExtra("venta", venta);
             intent.putExtra("fecha", fecha);

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void unused) {
        MainActivity.fetchDataTxt.setText(dataParsed);
        super.onPostExecute(unused);
    }
}
