
import java.io.*; 
import java.util.*;

/**
 * Helper class to test creation and operations of ADT Bag
 * @author eeshn
 * @version 1.0
 */

public class Helper {
    
    /**
     * Starts process of creating and displaying bag
     * @throws FileNotFoundException if file not found
     * @throws BagException needed for necessary methods with exceptions
     * @throws BagIndexException for necessary methods with imp
     */

    public static void start() throws FileNotFoundException, BagException, BagIndexException{
        
        Bag bag1 = create(); // Creates bag
        
        display(bag1); // Displays bag
        
        System.out.println("Is bag empty?: " + bag1.isEmpty()); // Tests isEmpty()
        
        System.out.println("Adding an item");
        bag1.insert("whipped cream"); // Tests insert()
        display(bag1);
        
        System.out.println("Removing last item");
        bag1.removeLast(); // Tests removeLast()
        display(bag1);
        
        System.out.println("Removing random item");
        bag1.removeRandom(); // Tests removeRandom()
        display(bag1);
        
        
        System.out.println("Index of bread is: " + bag1.get("bread")); // Tests get() to get index
        
        System.out.println("The item at index 3 is: " + bag1.getRef(3)); // Tests getRef() to get reference
        
        bag1.size(); // Tests size()
        
        System.out.println("Emptying bag");
        bag1.makeEmpty(); // Tests makeEmpty()
        display(bag1);
        
        
        
    }
    
  /**
   * Creates bag and writes into it from text file
   * @return a Bag with groceries from text file
   * @throws FileNotFoundException if file not found
   */
    public static Bag create() throws FileNotFoundException {
        Bag bag1 = new Bag(); // Creates a Bag
        
       Scanner sc = new Scanner(new File("Groceries.txt"));  // Creates scanner to read file
		
		int i = 0; // Writes file into Bag
		while(sc.hasNext()){
			bag1.insert(sc.next()); 
			i++;
	    }
		sc.close();
                
                return bag1; // Returns filled bag
                
                
    }
    
    /**
     * Displays bag using Arrays.toString()
     * @param mybag What bag to display
     */
    
    public static void display(Bag mybag) {
        System.out.println(Arrays.toString(mybag.getBag())); // Displays bag
        
    }
}
