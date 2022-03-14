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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

                 //for each "casa", we create the object "atributes" which turns "casa" into an object, before it was only a string.
                    JSONObject atributes = (JSONObject) jsonObject.get("casa");

                // To display the information we are interested on:
                    singleParsed = "Nombre: " + atributes.get("nombre") + "\n" + "Valor para la compra: " + atributes.get("compra") + "\n" + "Valor para la venta: " + atributes.get("venta");
                    dataParsed = dataParsed + singleParsed + "\n";

            }
            //The information we need to SAVE (dolar oficial) belongs to index 0:
            JSONObject jsonObjectDO = (JSONObject) jsonArray.get(0);
            JSONObject atributesDO = (JSONObject) jsonObjectDO.get("casa");

   //The following lines are throwing an ERROR:
         // String compra1 = (String) atributesDO.get("compra"); //obtain attribute (string)
         //  compra = (Double) Double.valueOf(compra1); //turn into Double

         //  String venta1 = (String) atributesDO.get("venta");
         //  venta = (Double) Double.valueOf(venta1);


        /* TODO send variables to MainActivity.java */


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
