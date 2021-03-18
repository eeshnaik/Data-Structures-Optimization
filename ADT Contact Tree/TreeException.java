

/**
 * Exception class for when "item not found" during BST operations
 * @author eeshn
 * @version 1.0
 */
public class TreeException extends RuntimeException {

    /**
     * Creates a new instance of TreeException without detail
     * message.
     */
    public TreeException() {
    }

    /**
     * Constructs an instance of TreeException with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TreeException(String msg) {
        super(msg);
    }
}
