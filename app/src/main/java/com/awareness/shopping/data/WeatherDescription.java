package com.awareness.shopping.data;

import android.content.Context;
import android.util.SparseArray;

import com.awareness.shopping.R;
import com.huawei.hms.kit.awareness.status.weather.constant.WeatherId;

public class WeatherDescription {
    private static SparseArray<String> weatherDescMap = new SparseArray<>();
    private static SparseArray<String> cnWeatherDescMap = new SparseArray<>();

    public static void init(Context context) {
        weatherDescMap.put(WeatherId.SUNNY, context.getString(R.string.weather_sunny));
        weatherDescMap.put(WeatherId.MOSTLY_SUNNY, context.getString(R.string.weather_mostly_sunny));
        weatherDescMap.put(WeatherId.PARTLY_SUNNY, context.getString(R.string.weather_partly_sunny));
        weatherDescMap.put(WeatherId.INTERMITTENT_CLOUDS, context.getString(R.string.weather_intermittent_clouds));
        weatherDescMap.put(WeatherId.HAZY_SUNSHINE, context.getString(R.string.weather_hazy_sunshine));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY, context.getString(R.string.weather_mostly_cloudy));
        weatherDescMap.put(WeatherId.CLOUDY, context.getString(R.string.weather_cloudy));
        weatherDescMap.put(WeatherId.DREARY, context.getString(R.string.weather_dreary));
        weatherDescMap.put(WeatherId.FOG, context.getString(R.string.weather_fog));
        weatherDescMap.put(WeatherId.SHOWERS, context.getString(R.string.weather_showers));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_SHOWERS, context.getString(R.string.weather_mostly_cloudy_with_showers));
        weatherDescMap.put(WeatherId.PARTLY_SUNNY_WITH_SHOWERS, context.getString(R.string.weather_partly_sunny_with_flurries));
        weatherDescMap.put(WeatherId.T_STORMS, context.getString(R.string.weather_T_storms));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_T_STORMS, context.getString(R.string.weather_mostly_cloudy_with_T_storms));
        weatherDescMap.put(WeatherId.PARTLY_SUNNY_WITH_T_STORMS, context.getString(R.string.weather_partly_sunny_with_T_storms));
        weatherDescMap.put(WeatherId.RAIN, context.getString(R.string.weather_rain));
        weatherDescMap.put(WeatherId.FLURRIES, context.getString(R.string.weather_flurries));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_FLURRIES, context.getString(R.string.weather_mostly_cloudy_with_flurries));
        weatherDescMap.put(WeatherId.PARTLY_SUNNY_WITH_FLURRIES, context.getString(R.string.weather_partly_sunny_with_flurries));
        weatherDescMap.put(WeatherId.SNOW, context.getString(R.string.weather_snow));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_SNOW, context.getString(R.string.weather_mostly_cloudy_with_snow));
        weatherDescMap.put(WeatherId.ICE, context.getString(R.string.weather_ice));
        weatherDescMap.put(WeatherId.SLEET, context.getString(R.string.weather_sleet));
        weatherDescMap.put(WeatherId.FREEZING_RAIN, context.getString(R.string.weather_freezing_rain));
        weatherDescMap.put(WeatherId.RAIN_AND_SNOW, context.getString(R.string.weather_rain_and_snow));
        weatherDescMap.put(WeatherId.HOT, context.getString(R.string.weather_hot));
        weatherDescMap.put(WeatherId.COLD, context.getString(R.string.weather_cold));
        weatherDescMap.put(WeatherId.WINDY, context.getString(R.string.weather_windy));
        weatherDescMap.put(WeatherId.CLEAR, context.getString(R.string.weather_clear));
        weatherDescMap.put(WeatherId.MOSTLY_CLEAR, context.getString(R.string.weather_mostly_clear));
        weatherDescMap.put(WeatherId.PARTLY_CLOUDY, context.getString(R.string.weather_partly_cloudy));
        weatherDescMap.put(WeatherId.INTERMITTENT_CLOUDS_2, context.getString(R.string.weather_intermittent_clouds));
        weatherDescMap.put(WeatherId.HAZY_MOONLIGHT, context.getString(R.string.weather_hazy_moonlight));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_2, context.getString(R.string.weather_mostly_cloudy));
        weatherDescMap.put(WeatherId.PARTLY_CLOUDY_WITH_SHOWERS, context.getString(R.string.weather_partly_cloudy_with_showers));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_SHOWERS_2, context.getString(R.string.weather_mostly_cloudy_with_showers));
        weatherDescMap.put(WeatherId.PARTLY_CLOUDY_WITH_T_STORMS, context.getString(R.string.weather_partly_cloudy_with_T_storms));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_T_STORMS_2, context.getString(R.string.weather_mostly_cloudy_with_T_storms));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_FLURRIES_2, context.getString(R.string.weather_mostly_cloudy_with_flurries));
        weatherDescMap.put(WeatherId.MOSTLY_CLOUDY_WITH_SNOW_2, context.getString(R.string.weather_mostly_cloudy_with_snow));
    }
}
