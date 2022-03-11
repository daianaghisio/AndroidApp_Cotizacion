package com.example.cotizacion;

import android.os.AsyncTask;

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

public class FetchData extends AsyncTask<Void, Void, Void> {

String data ="";
String dataUrl = "https://www.dolarsi.com/api/api.php?type=valoresprincipales";
String singleParsed="";
String dataParsed="";
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
            JSONArray jsonArray = new JSONArray(data); //data es el String que contiene la informacion completa del Json

            for (int i=0; i<jsonArray.length();i++){ //Nuestro array tiene 8 indices, los cuales contienen un objeto "casa" en cada uno de ellos

                    JSONObject jsonObject = (JSONObject) jsonArray.get(i); //jsonObject contiene a cada uno de los objetos "casa" pertenecientes a los diferentes indices

                 //para cada objeto "casa", creamos el objeto "atributes"
                    JSONObject atributes = (JSONObject) jsonObject.get("casa"); //convierte a "casa" en objeto, antes solo lo convertia en String

                //extraemos la informacion de nuestro interes:
                    singleParsed = "Nombre: " + atributes.get("nombre") + "\n" + "Valor para la compra: " + atributes.get("compra") + "\n" + "Valor para la venta: " + atributes.get("venta");
                    dataParsed = dataParsed + singleParsed + "\n";


            }


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
