package ua.unitfactory.avaj.aircraftsimulator.vehicles;

import ua.unitfactory.avaj.aircraftsimulator.WeatherTower;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower    weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        type = "JetPlane";
    }
    public void updateConditions() {
        try {
            FileWriter writer = new FileWriter("simulation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if(weatherTower.getWeather(coordinates).contentEquals("RAIN")) {
                bufferedWriter.write("JetPlane#"+getName()+"("+getId()+"): It's raining. Better watch out for lightings.\n");
                coordinates.setLatitude(coordinates.getLatitude()+5);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("FOG")) {
                bufferedWriter.write("JetPlane#"+getName()+"("+getId()+"): Foggy day.\n");
                coordinates.setLatitude(coordinates.getLatitude()+1);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("SUN")) {
                bufferedWriter.write("JetPlane#"+getName()+"("+getId()+"): Nice sunny day.\n");
                coordinates.setLatitude(coordinates.getLatitude()+10);
                coordinates.setHeight(coordinates.getHeight()+2);
                if (coordinates.getHeight() > 100)
                    coordinates.setHeight(100);
            }
            else if(weatherTower.getWeather(coordinates).contentEquals("SNOW")) {
                bufferedWriter.write("JetPlane#"+getName()+"("+getId()+"): OMG! Winter is coming!\n");
                coordinates.setHeight(coordinates.getHeight()-7);
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
