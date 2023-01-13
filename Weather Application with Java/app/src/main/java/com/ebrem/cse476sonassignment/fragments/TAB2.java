package com.ebrem.cse476sonassignment.fragments;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ebrem.cse476sonassignment.R;
import com.ebrem.cse476sonassignment.weatherData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TAB2 extends Fragment {

    final String APP_ID = "eb85c021cfc099f1b312281bbc2dd1d2";
    final String weather_url ="https://api.openweathermap.org/data/2.5/weather";

    TextView location, weatherCondition, Temperature;
    ImageView weatherIcon;
    EditText editText;

    Button cityFinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_a_b2, container, false);
        weatherCondition = view.findViewById(R.id.weatherCondition);
        Temperature = view.findViewById(R.id.temperature);
        location = view.findViewById(R.id.Location);
        weatherIcon = view.findViewById(R.id.weatherIcon);
        cityFinder = view.findViewById(R.id.goButton);
        editText = view.findViewById(R.id.PlainText);

        cityFinder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newLocation = editText.getText().toString();
                getLocationWeather(newLocation);
            }
        });
        return view;

    }
    private void apiCall(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(weather_url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                weatherData weather = weatherData.fromJson(response);
                Temperature.setText(weather.getTemperature());
                location.setText(weather.getLocation());
                weatherCondition.setText(weather.getWeatherType());
                int resourceID=getResources().getIdentifier(weather.getIcon(),"drawable",getActivity().getPackageName());
                weatherIcon.setImageResource(resourceID);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            }
        });
    }
    private void getLocationWeather(String location){
            RequestParams params = new RequestParams();
            params.put("q",location);
            params.put("appid",APP_ID);
            apiCall(params);
    }
}

