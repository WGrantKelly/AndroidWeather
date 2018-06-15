package grantkellycode.androidweather;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import javax.xml.transform.Result;


public class WeatherHomeScreen extends AppCompatActivity {

    String xml = null;
    CurrentWeatherData cwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_home_screen);

        RetrieveXML retrieved = new RetrieveXML("Bentonville");
        try{
            xml = new RetrieveXML("Bentonville").execute().get();
        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }

        try{
            cwd = new ParseAndLoad(xml).execute().get();
            Log.d("TESTER","WELL HELLO");
        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }

        int temp = (int)(9.0/5.0*(Float.parseFloat(cwd.tempValue) - 273.0) + 32.0);
        String tem = String.valueOf(temp);
        TextView currentTemp = findViewById(R.id.textView);
        currentTemp.setText(tem);

        String loc = cwd.cityName+"\t"+cwd.cityCountry;
        TextView location = findViewById(R.id.textView3);
        location.setText(loc);

        String hum = "Humidity " +cwd.humidityValue+" "+cwd.humidityUnit;
        TextView humidity = findViewById(R.id.textView2);
        humidity.setText(hum);

        String pres = "Pressure " +cwd.pressureValue+" "+cwd.pressureUnit;
        TextView pressure = findViewById(R.id.textView4);
        pressure.setText(pres);

        String wi = "Wind " +cwd.windSpeedValue+" mph "+cwd.windDirectionName;
        TextView wind = findViewById(R.id.textView5);
        wind.setText(wi);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}
