
import java.util.*;

/**
 * GenericStack class that provides template for stacks used in Expression
 * @author eeshn
 * @version 1.0
 */

public class GenericStack <E> implements GenericStackInterface <E> {
    /**
     * Data-field list for GenericStack
     */
    
      private ArrayList<E> list;
     
    /**
     * Constructor for Stack
     */
    
    public GenericStack () {
        this.list = new ArrayList<>();
        
    }
    /**
     * Method to get size of stack
     * @return Size of stack
     */
    
    @Override
    public int getSize() {
        return list.size();
    }
     /**
     * Returns top object in stack
     * @return top object in stack
     */
    
    @Override
    public E peek() throws StackException {
        
        if (list.isEmpty()) {
            throw new StackException("List is empty!");
        }
        else
   
         return list.get(list.size()-1);
    }
    /**
     * Adds object to stack
     * @param obj Item to add to stack
     */
    
    @Override
    public void push(E obj) {
        list.add(obj);
    }
    /**
     * Returns and removes item from stack
     * @return Removed item
     * @throws StackException if stack is empty
     */
    
    @Override
    public E pop() throws StackException {
        
        if (list.isEmpty()) {
            throw new StackException("List is empty");
        }
        
        
        E result = list.get(list.size() - 1);
        list.remove(list.size()-1);
        
        return result;
    }
    /**
     * Tells if stack is empty
     * @return Boolean that tells if stack is empty
     */
    
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

}
