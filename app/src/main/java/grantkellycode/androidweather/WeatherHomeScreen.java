package grantkellycode.androidweather;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import javax.xml.transform.Result;


public class WeatherHomeScreen extends AppCompatActivity {

    String xml = null;

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
            new ParseAndLoad(xml).execute().get();
        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }

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
