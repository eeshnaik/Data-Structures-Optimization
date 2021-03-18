
/**
 * Base BT class to make base for BT's
 * @author eeshn
 * @param <E> element of Base Binary Tree
 * @version 1.0
 */
public class BaseBinaryTree <E> {
    
    protected TreeNode <E> root;
    
    /**
     * No arg constructor
     */
    
    public BaseBinaryTree() {
        
    }
    /**
     * Arg constructor
     * @param rootitem Root item for BT
     */
    public BaseBinaryTree(E rootitem) {
        this.root = new TreeNode(rootitem);
        
    }
    /**
     * Tells if BT is empty
     * @return boolean whether empty
     */
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * Makes BT empty
     */
    public void makeEmpty() {
               root = null;
        
    }
/**
 * Getter for root
 * @return root
 */
    public TreeNode<E> getRoot() {
        return root;
    }
/**
 * Setter for root
 * @param root root
 */
    public void setRoot(TreeNode<E> root) throws UnsupportedOperationException {
        this.root = root;
    }
    
    

}
