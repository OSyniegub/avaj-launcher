package ua.unitfactory.avaj.aircraftsimulator.vehicles;

import ua.unitfactory.avaj.aircraftsimulator.WeatherTower;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower    weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        type = "Helicopter";
    }
    public void updateConditions() {
        try {
            FileWriter writer = new FileWriter("simulation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if(weatherTower.getWeather(coordinates).contentEquals("RAIN")) {
                bufferedWriter.write("Helicopter#"+getName()+"("+getId()+"): Rain showers my spirit and waters my Soul.\n");
                coordinates.setHeight(coordinates.getHeight()+5);
                if (coordinates.getHeight() > 100)
                    coordinates.setHeight(100);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("FOG")) {
                bufferedWriter.write("Helicopter#"+getName()+"("+getId()+"): I can't see anything because of the fog.\n");
                coordinates.setHeight(coordinates.getHeight()+1);
                if (coordinates.getHeight() > 100)
                    coordinates.setHeight(100);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("SUN")) {
                bufferedWriter.write("Helicopter#"+getName()+"("+getId()+"): This is hot.\n");
                coordinates.setLongitude(coordinates.getLongitude()+10);
                coordinates.setHeight(coordinates.getHeight()+2);
                if (coordinates.getHeight() > 100)
                    coordinates.setHeight(100);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("SNOW")) {
                bufferedWriter.write("Helicopter#"+getName()+"("+getId()+"): My rotor is going to freeze!\n");
                coordinates.setHeight(coordinates.getHeight()-12);
            }
            bufferedWriter.close();
            if (coordinates.getHeight() <= 0) {
                landing();
                weatherTower.unregister(this);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}
