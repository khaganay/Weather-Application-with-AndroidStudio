package com.ebrem.cse476sonassignment.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.ebrem.cse476sonassignment.R;
import com.ebrem.cse476sonassignment.weatherData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TAB3 extends Fragment {

    final String APP_ID = "eb85c021cfc099f1b312281bbc2dd1d2";
    final String weather_url ="https://api.openweathermap.org/data/2.5/weather";

    TextView speed, direction, name;
    LocationManager mLocationManager;
    LocationListener mLocationListener;
    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;
    String Location_Provider = LocationManager.GPS_PROVIDER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_a_b3, container, false);
        speed = view.findViewById(R.id.windspeed);
        direction = view.findViewById(R.id.winddirection);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        getWeatherForCurrentLocation();
    }
    private void getWeatherForCurrentLocation() {
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {

                String Lat = String.valueOf(location.getLatitude());
                String Lon = String.valueOf(location.getLongitude());
                getWeatherForCurrentLocation();

                RequestParams params = new RequestParams();
                params.put("lat", Lat);
                params.put("lon", Lon);
                params.put("appid", APP_ID);
                apiCall(params);

            }


        };
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getWeatherForCurrentLocation();
            }
            else
            {

            }
        }
    }
    private void apiCall(RequestParams params) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(weather_url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                weatherData weather = weatherData.fromJson(response);
                speed.setText(weather.getWindSpeed());
                direction.setText(weather.getWindDirection());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            }
        });
    }
}
