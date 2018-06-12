package grantkellycode.androidweather;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveXML extends AsyncTask<Void, Void, String>{

    private Exception exception;
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY = "6927d88cf63a83785732f1bc32728ef0";
    private static String cityName = "";
    private static String MODE = "&mode=xml";

    public RetrieveXML(String city){
        cityName = city;
    }

    protected void onPreExecute() {

    }

    protected String doInBackground(Void... urls) {
        // Do some validation here

        try {
            URL url = new URL(API_URL + cityName + MODE + "&APPID="+API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
    }

}
