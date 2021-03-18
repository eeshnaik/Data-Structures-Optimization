
/**
 * Exception class that handles RuntimeException if methods are called on empty list
 * @author eeshn
 */
public class StringException extends RuntimeException{

    /**
     * Constructs an object with specific message
     * @param message String message that shows up during exception
     */
    
    public StringException (String message) {
    
        super(message);

    }
}
