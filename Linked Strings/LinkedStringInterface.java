
/**
 * Interface of LS methods you can use
 * @author eeshn
 * version 1.0
 */
public interface LinkedStringInterface {
/**
 * A method that gives the node at a specified index
 * @param a Index to get node at
 * @return Character value of the node
 * @throws Exception if a < 0 or > count
 * @throws Exception if list is empty
 */  
     public char charAt(int a) throws StringIndexOOBException, StringException;
/**
 * A method that provides a substring of an LS
 * @param a Index of start of substring
 * @param b Index of end of substring
 * @return A new LS that is the substring
 * @throws Exception if a or b are < 0 or > count
 */       
     public LinkedString substring(int a, int b) throws StringIndexOOBException;
/**
 * Tells whether LS is empty or not
 * @return Boolean to tell whether count is 0 or not
 */      
     public boolean isEmpty();
/**
 * Tells you the length of LS
 * @return Count datafield which shows count of nodes in LS
 */      
     public int length();
/**
 * A method that concatonates two LS's
 * @param b The LS being attached at end
 * @return A new LS that is the old two combined
 */      
     public LinkedString concat(LinkedString b);

/**
 * A toString method to turn LS into a String
 * @return A String version of LS
 */ 
     public String toString();
}
