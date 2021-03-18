
/**
 * Node generic class for LS
 * @author eeshn
 * version 1.0
 */
public class Node<E> {
/**
 * Datafields for Node class
 */
private E data;

private Node<E> next;
private Node<E> prev;

/**
 * No-arg constructor for Node
 */
public Node() {
    
    
}
/**
 * Arg constructor for Node
 * @param data What you want to set inside the Node
 */
public Node(E data) {
    
    this.data = data;
    this.next = null;
    this.prev = null;
    
}
/**
 * Accessor method for data of Node
 * @return Data inside Node
 */
    public E getData() {
        return data;
    }
/**
 * Mutator method for data of Node
 * @param data The data inside Node
 */
    public void setData(E data) {
        this.data = data;
    }
/**
 * Accessor method for next Node
 * @return Next Node
 */
    public Node<E> getNext() {
        return next;
    }
/**
 * Mutator method for next Node
 * @param next Node to be changed
 */
    public void setNext(Node<E> next) {
        this.next = next;
    }
/**
 * Accessor method for prev Node
 * @return Previous Node
 */
    public Node<E> getPrev() {
        return prev;
    }
/**
 * Mutator method for prev Node
 * @param prev Node to be changed
 */
    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }
    
}
