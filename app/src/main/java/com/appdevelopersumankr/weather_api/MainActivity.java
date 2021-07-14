package com.appdevelopersumankr.weather_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    AppCompatButton button;
    EditText editText;
    //String apikey="62d008d1f8ed8d6eb0c1a5fc14d8873a";


    String exampleurl="api.openweathermap.org/data/2.5/weather?q=London&appid={API key}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        textView=findViewById ( R.id.resultid);
        button=findViewById ( R.id.buttonid);
        editText=findViewById ( R.id.edittextid);

        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                textView.setText ( "" );
                String cityname=editText.getText ().toString ();
                String url="http://api.openweathermap.org/data/2.5/weather?q="+cityname+"&appid=62d008d1f8ed8d6eb0c1a5fc14d8873a";

                RequestQueue queue= Volley.newRequestQueue ( getApplicationContext () );
                JsonObjectRequest request=new JsonObjectRequest ( Request.Method.GET, url, null, new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                           getfunction(response,cityname);
                    }
                }, new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText ( MainActivity.this, ""+error.toString (), Toast.LENGTH_SHORT ).show ();

                    }
                } );
                queue.add ( request);
            }
        } );

    }

    private void getfunction(JSONObject response,String nameofcity) {
        JSONObject object=response.optJSONObject ( "main" );
        JSONObject weatherobj=response.optJSONObject ( "wind");
        JSONObject object3=response.optJSONObject ( "sys");
        JSONObject object4=response.optJSONObject ( "coord");


        String nameofcity2=nameofcity;

        String temp=object.optString ( "temp");
        String maximumtep=object.optString ( "temp_max");
        String mintemp=object.optString ( "temp_min");
        String pressure=object.optString ( "pressure");
        String humadity=object.optString ( "humidity");

        String description=weatherobj.optString ( "speed" );

        String sumrise=object3.optString ( "sunrise");
        String sunset=object3.optString ( "sunset" );

        Double longi=object4.optDouble ( "lon" );
        Double lati=object4.optDouble ( "lat");

        textView.setText ( nameofcity2+"\n"+"Temprature in k   "+temp+"\n"+"Maximum temprature   "+maximumtep+"\n"+"Minmum temprature   "+mintemp+"\n"+"Pressure  "+pressure+"\n"+"Humadity   "
                +humadity+"\n"+"Description   "+description+"\n"+"SUNRISE TIME   "+sumrise+"\n"+"SUN SET TIME   "+sunset+"\n"+"Long..   "+longi+"\n"+"Lati..   "+lati);
    }
}