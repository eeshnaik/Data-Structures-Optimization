
/**
 * Treenode class for BST
 * @author eeshn
 * @version 1.0
 * @param <E> Element of Treenode
 */
public class TreeNode <E> {
    private E element;
    private TreeNode <E> left;
    private TreeNode <E> right;
    
    /**
     * No-arg constructor for TreeNode
     */
    public TreeNode() {
        
        
    }
    /**
     * Arg constructor for Treenode
     * @param element Element to pass into TreeNode
     * @param left Left child of node
     * @param right Right child of node
     */

    public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
/**
 * Arg constructor for TreeNode
 * @param element Element to pass into node
 */
    public TreeNode(E element) {
        this.element = element;
        this.right = null;
        this.left = null;
    }
    /**
     * Accessor method for element
     * @return Element of node
     */
    
    public E getElement() throws TreeException {
        if (this == null) {
            throw new TreeException("TreeNode null");
        }
        else {
            return element;
        }
        
    }
    /**
     * Mutator method for element
     * @param element Element in node
     */

    public void setElement(E element) {
        this.element = element;
    }
    
    /**
     * Getter method for left child
     * @return Left child node
     */

    public TreeNode<E> getLeft() {
        return left;
    }
    /**
     * Setter method for left child
     * @param left Left child node
     */

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }
    /**
     * Getter for right child
     * @return Right child
     */

    public TreeNode<E> getRight() {
        return right;
    }
/**
 * Setter for right child
 * @param right Right child node
 */
    public void setRight(TreeNode<E> right) {
        this.right = right;
    }
    
    
    

}
