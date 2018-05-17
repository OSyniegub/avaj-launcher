package ua.unitfactory.avaj.aircraftsimulator;

/**
 * Created by osyniegu on 2/24/18.
 */
public class WrongTypeException extends Exception {
    private String str;
    public String  getStr() {return str;}
    public WrongTypeException(String message, String s) {
        super(message);
        str = s;
    }
}
