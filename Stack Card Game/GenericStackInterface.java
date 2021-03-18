
/**
 * Interface for GenericStack methods
 * @author eeshn
 * @param <E> Elements stored in Stack
 * version 1.0
 */

public interface GenericStackInterface <E> {
    /**
     * Method to get size of stack
     * @return Size of stack
     */
    public int getSize();
    
    /**
     * Returns top object in stack
     * @return top object in stack
     */
    
    public E peek() throws StackException;
    /**
     * Adds object to stack
     * @param obj Item to add to stack
     */
    
    public void push(E obj);
    /**
     * Returns and removes item from stack
     * @return Removed item
     * @throws StackException if stack is empty
     */
    
    public E pop() throws StackException;
    /**
     * Tells if stack is empty
     * @return Boolean that tells if stack is empty
     */
    
    public boolean isEmpty();
    
    
    
    
            
            
     
    
}
