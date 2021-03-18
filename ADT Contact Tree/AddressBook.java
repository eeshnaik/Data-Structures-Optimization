
/**
 * AdressBook class with operations on Address book
 * @author eeshn
 * @version 1.0
 */
public class AddressBook  {
    // BST datafield
    private BinarySearchTree<Contact> tree;
    //Count of objects in book
    private static int count;
    /**
     * No-arg constructor for book
     */
    public AddressBook() {
        
        this.tree = new BinarySearchTree<>();
        count = 0;
        
    }
/**
 * Getter for BST
 * @return BST
 */
    public BinarySearchTree<Contact> getTree() {
        return tree;
    }
/**
 * Setter for BST
 * @param tree 
 */
 
    public void setTree(BinarySearchTree<Contact> tree) {
        this.tree = tree;
    }
/**
 * Getter for count
 * @return count
 */
    public static int getCount() {
        return count;
    }
/**
 * Setter for count
 * @param count 
 */
    public static void setCount(int count) {
        AddressBook.count = count;
    }
/**
 * Adds contact to book
 * @param c1 Contact being added
 */    
    public void add(Contact c1) {
        tree.add(c1);
        count++;
    }
/**
 * Removes contact from book
 * @param c1 Contact being removed
 */  
    public void remove(Contact c1) {
        tree.remove(c1);
        count--;
    }
/**
 * Searches for contact in book
 * @param c1 Contact being searched for
 * @return Boolean if found or not
 */ 
    public boolean search(Contact c1) {
        
        return tree.find(c1);
        
    }
/**
 * Gets reference for contact
 * @param name Name to search with
 * @return Contact reference
 */   
    public Contact getref(String name) {
        
        Contact c1 = new Contact(name);
        return tree.search(c1).getElement();
    }
/**
 * Gets size of book
 * @return int count
 */
    
    public int getSize() {
        return count;
    }
/**
 * Checks if book is empty
 * @return boolean whether empty
 */    
    public boolean isEmpty() {
        return count == 0;
    }
/**
 * Makes book empty
 */    
    public void makeEmpty() {
        tree.root.setLeft(null);
        tree.root.setRight(null);
        tree.root = null;
        
    }
    
}
