package ua.unitfactory.avaj.aircraftsimulator.weather;

import ua.unitfactory.avaj.aircraftsimulator.vehicles.Coordinates;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider         weatherProvider;
    private static String           weather;
    private                         WeatherProvider() { }
    public static WeatherProvider   getProvider() {
        weatherProvider = new WeatherProvider();
        return weatherProvider;
    }
    public String                   getCurrentWeather(Coordinates coordinates) {
        String w[] = {"RAIN", "SNOW", "SUN", "FOG"};
        int latitude = coordinates.getLatitude();
        int longitude = coordinates.getLongitude();
        int height = coordinates.getHeight();
        int n = latitude + longitude + height;
        n = n % 4;
        return w[n];
    }
}
