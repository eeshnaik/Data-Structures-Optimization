

import java.util.*;
import java.io.*;
/**
 * Helper class to assist in testing LS methods
 * @author eeshn
 */
public class Helper {
    
/**
 * Method that starts create() and displayandMrre() to test methods
 * @throws FileNotFoundException If a file is not found
 */
    public static void start() throws FileNotFoundException {
        ArrayList<LinkedString> mylist = create();
        
        displayandMore(mylist);
    }
    /**
     * A method that creates an ArrayList of LS's
     * @return An ArrayList of LS's
     * @throws FileNotFoundException If a file not Found
     */
    public static ArrayList<LinkedString> create() throws FileNotFoundException {
        ArrayList<LinkedString> list = new ArrayList<>();
        
        Scanner sc = new Scanner(new File("C:\\Users\\eeshn\\Desktop\\Strings.txt")); // Creates scanner to read file
		
	int i = 0; 
            while(sc.hasNext()){
                
                if (i % 2 == 0) {
			list.add(new LinkedString(sc.next())); 
			i++;
                }
                else
                    list.add(new LinkedString(sc.next().toCharArray()));
                i++;
	    }
		sc.close();
                
                return list; // Returns filled list
        
        
    }
    /**
     * A method that explicitly tests LS methods and displays the results
     * @param list The ArrayList of LS's to test
     */
    public static void displayandMore(ArrayList<LinkedString> list) {
        
        /**
         * For Loop that tests every method on every pair of adjacent LS's
         * It also prints the LS's before and after the methods are performed to prove immutability
         */
        for (int i = 0; i < list.size(); i+= 2) {
            System.out.println("LS 1: " + list.get(i));
            System.out.println("LS 2: " + list.get(i+1));
            System.out.println("Length of LS 1: " + list.get(i).length());
            System.out.println("Length of LS 2: " + list.get(i+1).length());
            
            System.out.println("CharAt(0) of LS 1: " + list.get(i).charAt(0));
            System.out.println("CharAt(0) of LS 2: " + list.get(i+1).charAt(0));
            
            System.out.println("Substring (0,lenght()) of LS 1: " + list.get(i).substring(0, list.get(i).length()-1));
            System.out.println("Substring (0,lenght()) of LS 2: " + list.get(i+1).substring(0,list.get(i+1).length()-1));
            
            System.out.println("Concatonation of LS 1 and 2: " + list.get(i).concat(list.get(i+1)));
            
            System.out.println("LS 1 (unchanged): " + list.get(i));
             System.out.println("LS 2 (unchanged): " + list.get(i+1));
             
             System.out.println("");
        }
    
}
}
