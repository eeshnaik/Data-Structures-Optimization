import java.util.*;
import java.io.*;
/**
 * Helper class to test methods
 * @author eeshn
 * @version 1.0
 */

public class Helper {
    /**
     * Start method to start testing software
     * @throws FileNotFoundException if file not found
     */
    public static void start() throws FileNotFoundException {
        AddressBook book = new AddressBook();
        
        Scanner sc = new Scanner(new File("C:\\Users\\eeshn\\Desktop\\contacts.txt"));
        
        while (sc.hasNextLine()) {
             StringTokenizer st = new StringTokenizer(sc.nextLine(),"\t");
             
             book.add(new Contact(st.nextToken() + st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));
        }
        
        //Display book
        
        TreeIterator<Contact> it = new TreeIterator<>(book.getTree());
        it.setInorder();
        
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("");
        //Test add method
        Contact c1 = new Contact("Zuckerberg M", "38576 Main St", "Cupertino", "CA", "95014","408-456-1893");
        Contact c2 = new Contact("zennis T");
        book.add(c2);
        book.add(c1);
        System.out.println("Zuckerberg and zennis T added");
        
        //Test remove method
        book.remove(c1);
        System.out.println("Zuckerberg removed!");
         
        //Test search method (true)
        System.out.println("Searching 'zennis T' result: " + book.search(c2));
        
        //Test getSize() method
        System.out.println("book size: " + book.getSize());
        
        //Test isEmpty() method
        System.out.println("Book empty?: " + book.isEmpty());
        
        //Test getReference method
        Contact c3 = book.getref("zennis T");
        System.out.println("Reference fetched for zennis T");
        
        //Test make empty method
        System.out.println("Making book empty");
        System.out.println("Printing book");
        book.makeEmpty();
        
        //Display book again
        it.setInorder();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("Book is empty");
        
        
        
        
        
        
        
        
           
            
            
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

}
