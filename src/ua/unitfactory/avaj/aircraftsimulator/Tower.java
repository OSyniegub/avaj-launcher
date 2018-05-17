package ua.unitfactory.avaj.aircraftsimulator;

import ua.unitfactory.avaj.aircraftsimulator.vehicles.Flyable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tower {
    private ArrayList<Flyable>   observers = new ArrayList<>();

    public void     register(Flyable flyable, WeatherTower weatherTower) {
        try {
            FileWriter writer = new FileWriter("simulation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            observers.add(flyable);
            flyable.registerTower(weatherTower);
            if (flyable.getClass().toString().contains("Helicopter"))
                bufferedWriter.write("Tower says: Helicopter#"+flyable.getName()+"("+
                        flyable.getId()+")"+" registered to weather tower.\n");
            else if (flyable.getClass().toString().contains("Baloon"))
                bufferedWriter.write("Tower says: Baloon#"+flyable.getName()+"("+
                        flyable.getId()+")"+" registered to weather tower.\n");
            else if (flyable.getClass().toString().contains("JetPlane"))
                bufferedWriter.write("Tower says: JetPlane#"+flyable.getName()+"("+
                        flyable.getId()+")"+" registered to weather tower.\n");
            bufferedWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    public void     unregister(Flyable flyable) {
        try {
            FileWriter writer = new FileWriter("simulation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            observers.remove(flyable);
            if (flyable.getClass().toString().contains("Helicopter"))
                bufferedWriter.write("Tower says: Helicopter#"+flyable.getName()+"("+
                        flyable.getId()+")"+" unregistered from weather tower.\n");
            else if (flyable.getClass().toString().contains("Baloon"))
                bufferedWriter.write("Tower says: Baloon#"+flyable.getName()+"("+
                        flyable.getId()+")"+" unregistered from weather tower.\n");
            else if (flyable.getClass().toString().contains("JetPlane"))
                bufferedWriter.write("Tower says: JetPlane#"+flyable.getName()+"("+
                        flyable.getId()+")"+" unregistered from weather tower.\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    protected void  conditionsChanged() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
    }
}
