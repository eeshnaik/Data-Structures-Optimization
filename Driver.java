import java.io.*;


/**
 * Driver class to start the program
 * Demonstrates the concept of an ADT Bag using an [] data structure
 * @author eeshn
 * @version 1.0
 */

public class Driver {
    
    /**
     * Main method of program that starts testing process
     * @param args A reference to a String array that stores command-line arguments
     * @throws FileNotFoundException if file not found
     * @throws BagIndexException If i < 0 or > 99 or improper
     * @throws BagException If bag is empty
     */

public static void main (String [] args) throws FileNotFoundException, BagIndexException, BagException {
    Helper.start(); // Starts start() method to test program
}    
}
