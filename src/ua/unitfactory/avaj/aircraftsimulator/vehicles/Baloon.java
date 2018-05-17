package ua.unitfactory.avaj.aircraftsimulator.vehicles;

import ua.unitfactory.avaj.aircraftsimulator.WeatherTower;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {
    int i = 0;
    private WeatherTower    weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        type = "Baloon";
    }
    public void updateConditions() {
        try {
            FileWriter writer = new FileWriter("simulation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if(weatherTower.getWeather(coordinates).contentEquals("RAIN")) {
                bufferedWriter.write("Baloon#"+getName()+"("+getId()+"): Damn you rain! You messed up my baloon.\n");
                coordinates.setHeight(coordinates.getHeight()-5);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("FOG")) {
                bufferedWriter.write("Baloon#"+getName()+"("+getId()+"): Nothing is visible, I reduce the height.\n");
                coordinates.setHeight(coordinates.getHeight()-3);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("SUN")) {
                bufferedWriter.write("Baloon#"+getName()+"("+getId()+"): Let's enjoy the good weather and take some pics.\n");
                coordinates.setLongitude(coordinates.getLongitude()+2);
                coordinates.setHeight(coordinates.getHeight()+4);
                if (coordinates.getHeight() > 100)
                    coordinates.setHeight(100);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("SNOW")) {
                bufferedWriter.write("Baloon#"+getName()+"("+getId()+"): It's snowing. We're gonna crash.\n");
                coordinates.setHeight(coordinates.getHeight()-15);
            }
            bufferedWriter.close();
            if (coordinates.getHeight() <= 0)
            {
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
    public void landing() {
        super.landing();
    }
}
