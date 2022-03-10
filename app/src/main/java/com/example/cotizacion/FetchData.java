package com.example.cotizacion;

import android.os.AsyncTask;

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
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void unused) {
        MainActivity.fetchDataTxt.setText(data);
        super.onPostExecute(unused);
    }
}
