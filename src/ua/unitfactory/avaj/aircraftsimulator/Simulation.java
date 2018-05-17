package ua.unitfactory.avaj.aircraftsimulator;

//import sun.misc.JavaIOAccess;
import ua.unitfactory.avaj.aircraftsimulator.vehicles.AircraftFactory;
import ua.unitfactory.avaj.aircraftsimulator.vehicles.Flyable;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Simulation {
    private static WeatherTower weatherTower;

    public static void  main(String[] arg) throws InterruptedException {
        try {
            BufferedReader read = new BufferedReader(new FileReader(arg[0]));
            FileWriter writer = new FileWriter("simulation.txt", false);
            writer.close();
            String line = read.readLine();
            if (line != null) {
                int j = 1;
                int cor[] = new int[4];
                weatherTower = new WeatherTower();
                int sim = Integer.parseInt(line.split(" ")[0]);
                if (sim < 0)
                    throw new PositiveNumberException("Invalid simulation count ", sim);
                while ((line = read.readLine()) != null) {
                    while (++j <= 4) {
                        if ((cor[j - 2] = Integer.parseInt(line.split(" ")[j])) <= 0)
                            throw new PositiveNumberException("Invalid simulation count ",
                                    Integer.parseInt(line.split(" ")[j]));
                    }
                    j = 1;
                    Flyable flyable = AircraftFactory.newAircraft
                            (line.split(" ")[0], line.split(" ")[1],
                                    cor[0],
                                    cor[1],
                                    cor[2]);
                    if (flyable == null)
                        System.exit(1);
                    weatherTower.register(flyable, weatherTower);
                }
                for (int i = 0; i < sim; i++) {
                    weatherTower.changeWeather();
                }
                FileWriter w1 = new FileWriter("scenario(md5).txt", false);
                BufferedWriter bufferedWriter = new BufferedWriter(w1);
                BufferedReader r = new BufferedReader(new FileReader(arg[0]));
                String l;
                while ((l = r.readLine()) != null) {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(l.getBytes());
                    byte[] digest = md.digest();
                    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
                    bufferedWriter.write(myHash+"\n");
                }
                bufferedWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file " + arg[0]);
        } catch (IOException e) {
            System.out.println("There was an error while reading file " + arg[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Specify simulation file");
        } catch (NumberFormatException e) {
            System.out.println("Input error"+"\n"+e.getLocalizedMessage());
        } catch (PositiveNumberException e) {
            System.out.println(e.getMessage() + e.getNumber());
        } catch (NoSuchAlgorithmException e) {

        }
    }
}
