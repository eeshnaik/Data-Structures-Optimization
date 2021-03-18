

/**
 * Interface of address book methods
 * @author eeshn
 * @version 1.0
 */
public interface AddressBookInterface {
/**
 * Adds contact to book
 * @param c1 Contact being added
 */  
    
       public void add(Contact c1);
/**
 * Removes contact from book
 * @param c1 Contact being removed
 */       
       public void remove(Contact c1);
/**
 * Searches for contact in book
 * @param c1 Contact being searched for
 * @return Boolean if found or not
 */        
       public boolean search(Contact c1);
/**
 * Gets reference for contact
 * @param name Name to search with
 * @return Contact reference
 */        
       public Contact getref(String name);
/**
 * Gets size of book
 * @return int count
 */       
       public int getSize();
/**
 * Checks if book is empty
 * @return boolean whether empty
 */       
       public boolean isEmpty();
/**
 * Makes book empty
 */       
       public void makeEmpty();
        
               

    
    
    
    
    
    
    
    
}
