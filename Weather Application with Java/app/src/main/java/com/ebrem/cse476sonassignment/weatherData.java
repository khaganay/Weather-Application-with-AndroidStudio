package com.ebrem.cse476sonassignment;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {

    private String mTemperature;
    private String kTemperature;
    private String fTemperature;
    private String mIcon;
    private String mLocation;
    private String mWeather;
    private String mHumidity;
    private String mWindSpeed;
    private String mWindName;
    private String mWindDirection;
    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject){
        try{
            weatherData weather = new weatherData();
            weather.mLocation = jsonObject.getString("name");
            weather.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weather.mIcon = updateWeatherIcon(weather.mCondition);
            weather.mWeather=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            //weather.mWindSpeed=jsonObject.getJSONArray("wind").getJSONObject(0).getString("speed");
            //weather.mWindDirection=jsonObject.getJSONArray("wind").getJSONObject(0).getString("deg");
            //weather.mWindName=jsonObject.getJSONArray("wind").getJSONObject(0).getString("gust");
            double temp = jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            double Ktemp = jsonObject.getJSONObject("main").getDouble("temp");
            double Humidity = jsonObject.getJSONObject("main").getDouble("humidity");
            double yeet = jsonObject.getJSONObject("wind").getDouble("speed");
            double windDirection = jsonObject.getJSONObject("wind").getDouble("deg");
            double Ftemp = temp * 9/5 + 32;
            int roundedValue = (int)Math.rint(temp);
            int KroundedValue = (int)Math.rint(Ktemp);
            int FroundedValue = (int)Math.rint(Ftemp);
            int RoundedHumidity = (int)Math.rint(Humidity);
            weather.mWindSpeed = String.valueOf(yeet);
            weather.mWindDirection = String.valueOf(windDirection);
            weather.mHumidity=Integer.toString(RoundedHumidity);
            weather.mTemperature=Integer.toString(roundedValue);
            weather.kTemperature=Integer.toString(KroundedValue);
            weather.fTemperature=Integer.toString(FroundedValue);
            return weather;
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    public String getHumidity() {

        return "Humidity:"+mHumidity;
    }
    public String getWindSpeed() {

        return "Wind Speed:"+mWindSpeed+"m/s";
    }
    public String getWindDirection() {

        return "Wind Direction:°"+mWindDirection;
    }
    public String getTemperature() {

        return mTemperature+"°C";
    }
    public String getKTemperature() {

        return kTemperature+"°K";
    }
    public String getFTemperature() {

        return fTemperature+"°F";
    }


    public String getLocation() {
        return mLocation;
    }

    public String getWeatherType() {

        return mWeather;
    }

    public String getIcon(){
        return mIcon;
    }
    private static String updateWeatherIcon(int condition)
    {
        if(condition>=0 && condition<=300)
        {
            return "heavyrain";
        }
        else if(condition>=300 && condition<=500)
        {
            return "lightrain";
        }
        else if(condition>=500 && condition<=600)
        {
            return "heavyrain";
        }
        else  if(condition>=600 && condition<=700)
        {
            return "snow";
        }
        else if(condition>=701 && condition<=771)
        {
            return "fog";
        }

        else if(condition>=772 && condition<=800)
        {
            return "cloudy";
        }
        else if(condition==800)
        {
            return "sunny";
        }
        else if(condition>=801 && condition<=804)
        {
            return "partlycloudy";
        }
        else  if(condition>=900 && condition<=902)
        {
            return "heavyrain";
        }
        if(condition==903)
        {
            return "snow";
        }
        if(condition==904)
        {
            return "sunny";
        }
        if(condition>=905 && condition<=1000)
        {
            return "heavyrain";
        }

        return "0";
    }

}
