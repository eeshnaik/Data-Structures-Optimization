
/**
 * BST class that includes extra operations on BinaryTree
 * @author eeshn
 * @param <E> Element of BST
 * @version 1.0
 */
public class BinarySearchTree <E extends Comparable<E>> extends BaseBinaryTree {
    /**
     * No-arg constructor for BST
     */
    public BinarySearchTree() {
        
    }
    /**
     * Arg constructor for BST
     * @param rootitem Root item being set
     */
    public BinarySearchTree(E rootitem) {
        super(rootitem);
        
    }

    /**
     * Constructor for Tree Iterator
     * @return Tree Iterator for (this) tree
     */
    public TreeIterator<E> iterator() throws UnsupportedOperationException {
        
        return new TreeIterator<>(this);
        
    }
    /**
     * Adds node
     * @param obj Element to add as node
     */
    
    public void add(E obj) {
      
        if (this.root == null) {
            this.root = new TreeNode(obj);
            
        }
        
        else {
            TreeNode<E> focus = root;
            
            
            while(true) {
            
            if (obj.compareTo(focus.getElement()) < 0) {
                if (focus.getLeft() == null) {
                    
                    focus.setLeft(new TreeNode(obj));
                    return;
                    
                }
                else {
                    focus = focus.getLeft();
                }
                
            }
            
            else if (obj.compareTo(focus.getElement()) > 0) {
                if (focus.getRight() == null) {
                    
                    focus.setRight(new TreeNode(obj));
                    return;
                    
                }
                else {
                     focus = focus.getRight();
                    
                }
               
                
            }
           
            }
            
            
        }
        

        
       
    }
    /**
     * Searches for node with element obj
     * @param obj Element to search with
     * @return Node with element obj
     * @throws TreeException when data not found
     */
    
    public TreeNode<E> search(E obj) throws TreeException {
        TreeNode<E> focus = root;
        
        while (focus != null) {
            
            switch(obj.compareTo(focus.getElement())) {
                
                case 0:
                    return focus;
                case 1: 
                    focus = focus.getRight();
                    break;
                case -1: 
                    focus = focus.getLeft();
                    break;
                   
            }
             
        }
        if (focus == null) {
            throw new TreeException("data not found");
        }
        return null;
        
    }
    /**
     * Returns parent of given node
     * @param obj Element of node to find parent of
     * @return Node parent
     */
    private TreeNode<E> parent(E obj) {
        TreeNode<E> focus = root;
        TreeNode<E> parent = null;
        
        
        while (focus != null) {
            
            switch(obj.compareTo(focus.getElement())) {
                
                case 1:
                    parent = focus;
                    focus = focus.getRight();
                    break;
                case -1: 
                    parent = focus;
                    focus = focus.getLeft();
                    break;
                case 0:
                    return parent;
                    
                }
            
        }
        System.out.println("node not in tree");
        return null;
        
    }
    /**
     * Removes node
     * @param obj Element of node to remove
     * @throws TreeException when item to remove not found
     */
    
    public void remove(E obj) throws TreeException {
        
        //Node to be deleted
        TreeNode<E> focus = search(obj);
        if (focus == null) {
            throw new TreeException("data not found");
        }

        //Parent of node to be deleted
        TreeNode<E> parent = parent(obj);
        
        
        
        /**
         * Node has no children
         */
        
        if (focus.getLeft() == null && focus.getRight() == null) {
            if (obj.compareTo(parent.getLeft().getElement()) == 0) {
                parent.setLeft(null);
            }
            else
                parent.setRight(null);
        }
        
        /**
         * Node has one child
         */
        else if (focus.getLeft() == null || focus.getRight() == null
                && !(focus.getLeft() == null && focus.getRight() == null)) {
            if (obj.compareTo(parent.getLeft().getElement()) == 0) {
                if (focus.getLeft() == null) {
                    parent.setLeft(focus.getRight());
                }
                else
                    parent.setLeft(focus.getLeft());
            }
            else
                if (focus.getLeft() == null) {
                    parent.setRight(focus.getRight());
                }
                else
                    parent.setRight(focus.getLeft());
            
        }
        /**
         * Node has two children
         */
        
        else {
            TreeNode<E> leftmin = leftbranchmin(focus.getRight());
            
            focus.setElement(leftmin.getElement());
            
            remove(leftmin.getElement());
            
        }
            
        
        
    }
    
    /**
     * Finds leftmost child of right branch
     * @param n1 TreeNode to find leftmost child of
     * @return Leftmost child
     */
    
    private TreeNode<E> leftbranchmin(TreeNode<E> n1) {
        if (n1.getLeft() == null) {
            return n1;
        }
        else {
            return leftbranchmin(n1.getLeft());
        }
        
    }
    
    /**
     * Find method to search for object in BST
     * @param obj Element to search for
     * @return Boolean whether found
     */
    public boolean find(E obj) {
        if (search(obj) == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
}
