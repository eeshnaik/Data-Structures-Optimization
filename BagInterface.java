

/**
 * Interface listing operations on ADT Bag
 * @author eeshn
 * @version 1.0
 */

public interface BagInterface {
    
     /**
     * Tells if bag is empty
     * @return boolean value telling if bag is empty
     */
    
public boolean isEmpty();
 
    /**
     * Adds items to end of bag list
     * @param item A String to add to bag
     * @throws BagException if bag is full
     */

public void insert(String item) throws BagException;
 
    /**
     * Removes last item on list
     * @throws BagException if bag is empty
     */

public void removeLast() throws BagException;
  
    /**
     * Removes random item in list
     * @throws BagIndexException for index < 0 or > 99. Also if spot is already empty
     */

public void removeRandom() throws BagIndexException;
  
    /**
     * Finds index of an item you want to search for in the list
     * @param it String value which represents item you want to search for
     * @return index of searched item
     * @throws exception if bag is empty
     * 
     */

public int get(String it) throws BagException;

    /**
     * Gets reference for item at a specified index
     * @param index The index at which you want the items reference
     * @return The String reference at the specified index
     * @throws BagIndexException if index is < 0 or > 99 or if the spot is empty in list
     */

public Object getRef(int index) throws BagIndexException;
  
    /**
     * Prints the size of list
     */

public void size();
  
    /**
     * Makes bag empty
     * @throws BagException if bag is already empty
     */
    
public void makeEmpty() throws BagException;

}
