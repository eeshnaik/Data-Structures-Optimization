
/**
 * Exception class to handle exceptions when index < 0 or > LS length
 * @author eeshn
 */
public class StringIndexOOBException extends IndexOutOfBoundsException {

    /**
     * Constructs an object with specific message
     * @param message String message that shows up during exception
     */
    
    public StringIndexOOBException (String message) {
    
        super(message);

    }
    
}
