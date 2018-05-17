package ua.unitfactory.avaj.aircraftsimulator;

/**
 * Created by osyniegu on 2/24/18.
 */
public class PositiveNumberException extends Exception {
    private int number;
    public int  getNumber() {return number;}
    public PositiveNumberException(String message, int num) {
        super(message);
        number = num;
    }
}
