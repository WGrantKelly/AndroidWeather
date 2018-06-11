package grantkellycode.androidweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputEmail;
        final EditText inputPswd;

        inputEmail = (EditText) findViewById(R.id.email);
        inputPswd = (EditText) findViewById(R.id.pswd);

        Button btnSignIn = (Button) findViewById(R.id.signIn);
        Button btnSignUp = (Button) findViewById(R.id.signUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent weatherHome = new Intent(getApplicationContext(), WeatherHomeScreen.class);

                //Sending data to another Activity
                weatherHome.putExtra("email", inputEmail.getText().toString());
                weatherHome.putExtra("pswd", inputPswd.getText().toString());

                Log.e("n", inputEmail.getText()+"."+ inputPswd.getText());

                startActivity(weatherHome);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent weatherHome = new Intent(getApplicationContext(), WeatherHomeScreen.class);

                //Sending data to another Activity
                weatherHome.putExtra("email", inputEmail.getText().toString());
                weatherHome.putExtra("pswd", inputPswd.getText().toString());

                Log.e("n", inputEmail.getText()+"."+ inputPswd.getText());

                startActivity(weatherHome);

            }
        });

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
