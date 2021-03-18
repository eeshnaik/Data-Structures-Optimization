
/**
 * Exception class to handle IndexOutOfBoundsException (i < 0 or i > 99)
 * @author eeshn
 */

public class BagIndexException extends IndexOutOfBoundsException {
    
    /**
     * Constructs an object with specific message
     * @param message String message that shows up during exception
     */
    
    public BagIndexException(String message) {
    
        super(message);

    }
}
