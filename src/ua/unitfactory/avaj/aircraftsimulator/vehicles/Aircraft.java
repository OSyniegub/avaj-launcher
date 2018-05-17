package ua.unitfactory.avaj.aircraftsimulator.vehicles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by osyniegu on 2/20/18.
 */

public class Aircraft {
    protected long                  id;
    protected String                name;
    protected Coordinates           coordinates;
    private static long             idCounter = 0;
    protected String                type;

    Aircraft() {}
    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }
    private long nextId() {
        return (++idCounter);
    }
    public String   getName() {
        return name;
    }
    public long getId() {
        return id;
    }
    public void landing() {
        try {
            coordinates.setHeight(0);
            FileWriter writer = new FileWriter("simulation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(type+"#"+getName()+"("+getId()+") landing.\n");
            bufferedWriter.write(type+"#"+getName()+"("+getId()+"): My current coordinates: latitude("
                    +coordinates.getLatitude()+"), longitude("+coordinates.getLongitude()+"), height("
                    +coordinates.getHeight()+").\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
