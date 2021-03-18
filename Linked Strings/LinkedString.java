
/**
 * LinkedString class that creates LS and contains operations on LS (LS = LinkedString)
 * @author eeshn
 * version 1.0
 */
public class LinkedString implements LinkedStringInterface {

    private Node<Character> head = new Node<>();
    private Node<Character> tail = new Node<>();
    private int count = 0;
    
/**
 * No-arg constructor for LS
 */
    public LinkedString() {
        
    }
    
/**
 * String arg constructor for LS
 * @param str The String that turns into a LS
 */
    public LinkedString(String str) {
        this(str.toCharArray());
        
    }
/**
* Character array arg constructor for LS
* @param ch The char [] that turns into a LS
*/
    public LinkedString(char [] ch) {
        int i = 0;
        
        while (i < ch.length) {
            
            Node <Character> insert = new Node<>(ch[i]);
            
            if (count == 0) {
                head = insert;
                
            }
            else {
                tail.setNext(insert);
                insert.setPrev(tail);
            }
            
            tail = insert;
            i++;
            count++;
        }
        
        
    }
   
/**
 * Accessor for head node
 * @return Head node of LS
 */
    public Node<Character> getHead() {
        return head;
    }
/**
 * Mutator for head node
 * @param head The head node to change into
 */
    public void setHead(Node<Character> head) {
        this.head = head;
    }
/**
 * Accessor for tail node
 * @return Tail node of LS
 */
    public Node<Character> getTail() {
        return tail;
    }
/**
 * Mutator for tail node
 * @param tail The tail node to change into
 */
    public void setTail(Node<Character> tail) {
        this.tail = tail;
    }
/**
 * Tells whether LS is empty or not
 * @return Boolean to tell whether count is 0 or not
 */    
    public boolean isEmpty() {
        return count == 0;
    }
/**
 * Tells you the length of LS
 * @return Count datafield which shows count of nodes in LS
 */    
    public int length() {
        return count;
    }
/**
 * A method that concatonates two LS's
 * @param b The LS being attached at end
 * @return A new LS that is the old two combined
 */    
    public LinkedString concat(LinkedString b) {
         LinkedString a1 = this.duplicate();
         LinkedString b1 = b.duplicate();
         /**
          * Clones both LS's and then attaches the clones and nulls required nodes
          */
         // Attaches clones
         a1.tail.setNext(b1.head);
         b1.head.setPrev(a1.tail);
         
         a1.tail = b1.tail;
         
         b1.tail = null;
         // Adds up count
         a1.count += b1.count;
        
       return a1;
    }
/**
 * A helper method that clones a LS
 * @return A cloned LS
 */    
    private LinkedString duplicate() {
        LinkedString cl = new LinkedString();
        this.tail = this.head;
        /**
         * Assigns tail of LS to head and scrolls through LS while copying (tail) elements to clone nodes. 
         * Tail is back where it started at the end and original LS is unchanged
         */
        int i = 0;
        
       while (i < this.count) {
           
           Node <Character> insert = new Node<>();
           
           if (cl.count == 0) {
               insert = cl.head;
               insert.setData(this.tail.getData());
               
           }
           else {
               /**
                * Attaches new node onto tail
                */
               insert.setData(this.tail.getData());
               cl.tail.setNext(insert);
               insert.setPrev(cl.tail);
           }
           //Assigns tail to new node and scrolls this.tail by 1
           cl.tail = insert;
           this.tail = this.tail.getNext();
           cl.count++;
           i++;
            
        }
        
       return cl;
        
    }
/**
 * A method that provides a substring of an LS
 * @param a Index of start of substring
 * @param b Index of end of substring
 * @return A new LS that is the substring
 * @throws Exception when a or b are < 0 or > count
 * 
 */   
    public LinkedString substring(int a, int b) throws StringIndexOOBException {
        /**
         * Throws exceptions where index is improper or list is empty
         */
        if (a < 0 || b < 0 || a > count || b > count) {
            throw new StringIndexOOBException("Improper indexes!");
        }
        else if (head == null){
            throw new StringException("Can't make substring from empty list!");
        }
        else {
            /**
             * Creates clones to use as reference and to return as substring
             */
        LinkedString ref = this.duplicate();
        LinkedString sub = this.duplicate();
        /**
         * Creates a reference LS to "scroll through" and assigns head and tail to new LS (sub) at appropriate indexes
         */
        ref.tail = ref.head;
        
        for (int i = 0; i < count; i++) {
            if (i == a) {
                //Index a is the head of substring
                sub.head = ref.tail;
                sub.head.setPrev(null);
                
            }
            
            if (i == b) {
                //Index b is the tail of substring
                sub.tail = ref.tail;
                sub.tail.setNext(null);
                
                return sub;
            }
            //Scrolls through reference LS
            ref.tail = ref.tail.getNext();
        }
        
        return sub;
        }
    }
/**
 * A method that gives the node at a specified index
 * @param a Index to get node at
 * @return Character value of the node
 * @throws Exception if a < 0 or > count
 * @throws Exception if list is empty
 */   
    public char charAt(int a) throws StringIndexOOBException,StringException {
        if (a < 0 || a > count) {
            throw new StringIndexOOBException("Improper index!");
        }
        else if (head == null) {
            throw new StringException("Can't find character in empty list");
        }
        else {
            
        // Assigns tail to head to scroll through LS
        tail = head;
        
        int i = 0;
        Node<Character> insert = new Node<>();
        /**
         * Finds match for character
         */
        while (i < length() -1 ) {
            
            if (i == a) {
                
                insert.setData(tail.getData());
                
            }
            
            tail = tail.getNext();
            i++;
        }
      return insert.getData();  
        }
    }
/**
 * A toString method to turn LS into a String
 * @return A String version of LS
 */    
    public String toString() {
        String str = "";
        /**
         * Scrolls through LS and adds characters to str
         */
        tail = head;
        
        int i = 0;
        while (i < count) {
            
            if (tail.getNext() == null) {
                str += tail.getData();
                return str;
            }
            else {
            str += tail.getData();
            //Scrolls tail forward
            tail = tail.getNext();
            
            
            }
            i++;
        }
        
        return str;
    }
    
    public LinkedString reverse() {
         LinkedString cl = new LinkedString();
        
        
        int i = 0;
        
       while (i < this.count) {
           
           Node <Character> insert = new Node<>();
           
           if (cl.count == 0) {
               insert = cl.head;
               insert.setData(this.tail.getData());
               
           }
           else {
               /**
                * Attaches new node onto tail
                */
               insert.setData(this.tail.getData());
               cl.tail.setNext(insert);
               insert.setPrev(cl.tail);
           }
           //Assigns tail to new node and scrolls this.tail by 1
           cl.tail = insert;
           tail = tail.getPrev();
           cl.count++;
           
           i++;
            
        }
        
       return cl;
    }
    
    public static void main(String [] args) {
        LinkedString a1 = new LinkedString("abcde");
        
        System.out.println(a1.reverse().toString());
        
        System.out.println(a1.concat(a1).toString());
    }
   
}
