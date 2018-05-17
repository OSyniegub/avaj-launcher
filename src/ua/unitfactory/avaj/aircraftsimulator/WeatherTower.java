package ua.unitfactory.avaj.aircraftsimulator;

import ua.unitfactory.avaj.aircraftsimulator.vehicles.Coordinates;
import ua.unitfactory.avaj.aircraftsimulator.vehicles.Flyable;
import ua.unitfactory.avaj.aircraftsimulator.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public  String   getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }
    void    changeWeather() {
        conditionsChanged();
    }
}
