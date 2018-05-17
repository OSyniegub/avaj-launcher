package ua.unitfactory.avaj.aircraftsimulator.vehicles;

import ua.unitfactory.avaj.aircraftsimulator.WeatherTower;

public interface Flyable {
    void        updateConditions();
    void        registerTower(WeatherTower WeatherTower);
    String      getName();
    long        getId();
}
