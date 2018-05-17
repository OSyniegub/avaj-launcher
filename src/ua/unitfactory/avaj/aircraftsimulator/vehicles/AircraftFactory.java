package ua.unitfactory.avaj.aircraftsimulator.vehicles;

import ua.unitfactory.avaj.aircraftsimulator.WrongTypeException;

public class AircraftFactory {
    public static Flyable  newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates cor = new Coordinates(longitude, latitude, height);
        try {
            switch (type) {
                case "Baloon":
                    return new Baloon(name, cor);
                case "Helicopter":
                    return new Helicopter(name, cor);
                case "JetPlane":
                    return new JetPlane(name, cor);
                default:
                    throw new WrongTypeException("Wrong type of aircraft ", type);
            }
        } catch (WrongTypeException e) {
            System.out.println(e.getMessage() + e.getStr());
            return (null);
        }
    }
}
