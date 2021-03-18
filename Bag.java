
/**
 * Bag Class that contains the data structure and operations for ADT Bag
 * @author eeshn
 * @version 1.0
 */
import java.util.*;

public class Bag implements BagInterface {
    
    /**
     * Reference to Object array of data
     */
    
private Object [] bag;

/**
 * Constructor to create empty array of size 100
 */
    
    public Bag() {
         this.bag = new Object [100]; // Makes object array of size 100
         Arrays.fill(this.bag," "); // Fills array with empty spots
    }
    
    /**
     * Accessor method for private data field bag
     * @return Data field bag
     */
    
    public Object [] getBag() {
        return this.bag; //returns bag datafield
    }
    
    /**
     * Tells if bag is empty
     * @return boolean value telling if bag is empty
     */
    
    public boolean isEmpty() {
        if (bag[0].equals(" ")) //since items are sequential, if the first idex is null then the list is empty
            return true;
        else
            return false;
    }
    
    /**
     * Adds items to end of bag list
     * @param item A String to add to bag
     * @throws BagException if bag is full
     */
    
    public void insert(String item) throws BagException {
                      
       if (!bag[99].equals(" ")) { // Since items are sequential, if the last item is full then the whole bag is full
           throw new BagException("Bag full");
           
       } 
        
       else {
           
       int i = 0;
       while (!bag[i].equals(" ")) { // Iterates through array until it finds last spot with item in it
           i++;
       }
       bag[i] = item; // Inserts item
       }
       
    }
    
    /**
     * Removes last item on list
     * @throws BagException if bag is empty
     */
    
    public void removeLast() throws BagException{
       if (bag[0].equals(" ")) {
           throw new BagException("Bag Empty"); // Checks if bag empty. Since items are sequential, if the the first item is empty then so is the list.
       }
        
       int i = 0;
       while (!bag[i].equals(" ")) { // Iterates through array to find last spot with item to remove
           i++;
       }
       
       bag[i-1] = " ";
       
    }
    
    /**
     * Removes random item in list
     * @throws BagIndexException for index < 0 or > 99. Also if spot is already empty
     */
    
    public void removeRandom() throws BagIndexException{
       
        int i = 0; // Iterates to find index of first empty item
         while (!bag[i].equals(" ")) {
           i++;
       }
        
        int index = (int)(Math.random()*(i)); // Generates random integer within list of full spots
        
        if (index < 0 || index > 99 || bag[index].equals(" ")) { // Throws exception if index is out of bounds or if spot is empty
            throw new BagIndexException("Index out of bounds or invalid");
        }
        else {
        
        while (index < i) { // Deletes and shifts spots
        bag[index] = bag[index+1];
        index++;
        }
        }
    }
    
    /**
     * Finds index of an item you want to search for in the list
     * @param it String value which represents item you want to search for
     * @return Index of searched item
     * @throws exception if bag empty
     * 
     */
    
    public int get(String it) throws BagException {
        
        if (this.isEmpty()) {
            throw new BagException("Bag is empty!"); // Throws exception for bag is empty
        }
        
        else {
        
        int i = 0;
        
        while (!bag[i].equals(it) && i < 100) { // Scrolls through array checking for match
            i++;
        }
        
    if (i == 99) { // i = 99 means no match was found
        return -1; // Returns -1 if item not found
        
    }
    else {
        return i; // Returns index
    }
        }
        
    }
    
    /**
     * Gets reference for item at a specified index
     * @param index The index at which you want the items reference
     * @return The String reference at the specified index
     * @throws BagIndexException if index is < 0 or > 99 or if the spot is empty in list
     */
    
    public Object getRef(int index) throws BagIndexException {
      
        /**
        * Throws exception for improper indexes (including empty spots)
        */
        
        if (index < 0 || index > 99 || bag[index].equals(" ")) {
            throw new BagIndexException("Index out of bounds or invalid"); 
        }
        else {
        
        return bag[index]; // Returns item at index
        }
    }
    
    /**
     * Prints the size of list
     */
    public void size() {
        int i = 0;
        while (!bag[i].equals(" ")) { // Scrolls until first empty spot
            i++;
        }
        System.out.println("Bag size: " + (i)); // Prints size 
    }
    
    /**
     * Makes bag empty
     * @throws BagException if bag is already empty
     */
    
    public void makeEmpty() throws BagException {
        if (bag[0].equals(" ")) {
            throw new BagException("Bag already empty!"); // Throws exception if bag already empty
        }
        else
            
        for (int i = 0; i < 100; i++) { // Scrolls through array making all spots empty
            bag[i] = " ";
        }
    }
    
   
}//Bag class


