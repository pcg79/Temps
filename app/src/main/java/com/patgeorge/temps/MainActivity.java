package com.patgeorge.temps;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.openweathermap.org/data/2.5/weather?q=Edinburgh,uk&appid=" +
                BuildConfig.OpenWeatherMapApiKey;

        final TextView mFahrenheitView = findViewById(R.id.fahrenheitView);
        final TextView mCelciusView = findViewById(R.id.celsiusView);
        final TextView mStatusView = findViewById(R.id.statusView);

        final String status = "Calling: " + url + "\n";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject resp = null;

                        try {
//                            mStatusView.setText(status + "Resp: " + response.substring(0,500));
                            resp = new JSONObject(response);
                            DecimalFormat df = new DecimalFormat("#.##");
                            df.setRoundingMode(RoundingMode.CEILING);
                            Double kelvin = resp.getJSONObject("main").getDouble("temp");
                            String fahrenheit = df.format(kelvin * 9/5 - 459.67);
                            String celcius = df.format(kelvin - 273.15);

                            mFahrenheitView.setText(fahrenheit + "° F");
                            mCelciusView.setText(celcius + "° C");
                        } catch (Exception e) {
                            mStatusView.setText(status + "Error: " + resp.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mStatusView.setText(status + "That didn't work! " + error.toString());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
