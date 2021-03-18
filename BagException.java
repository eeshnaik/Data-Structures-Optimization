
/**
 * Exception class to handle RuntimeException
 * @author eeshn
 */
public class BagException extends RuntimeException {
    /**
     * Constructs an object with specific message
     * @param message String message that shows up during exception
     */
    public BagException(String message) {
        super(message);
    }

}
