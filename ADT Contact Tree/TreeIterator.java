
/**
 * Iterator for BT
 * @author eeshn
 * @version 1.0
 */

import java.util.*;

public class TreeIterator <E> implements Iterator<E> {
    //Datafield for tree to iterate on
    private final BaseBinaryTree <E> tree;
    //List to store traversed nodes
    private final LinkedList<TreeNode<E>> list;
    /**
     * Arg constructor for iterator
     * @param tree Tree to iterate on
     */

    public TreeIterator(BaseBinaryTree<E> tree) {
        this.tree = tree;
        this.list = new LinkedList<TreeNode<E>>();
    }
    /**
     * Returns next token in tree
     * @return Next token
     * @throws NoSuchElementException if no element found
     */
    @Override
    public E next() throws NoSuchElementException {
        return list.remove().getElement();
    }
    /**
     * Removes token
     * @throws UnsupportedOperationException if no element found
     */
    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    } 
    /**
     * Tells if there is another token in tree
     * @return boolean whether another token is in tree
     */
    @Override
    public boolean hasNext() {
        return !(list.isEmpty());
    }
    /**
     * Sets iterator to preorder traversal
     */
    public void setPreorder() {
	  list.clear();
          this.preorder(tree.root);
  }
/**
 * Sets iterator to inorder traversal
 */
    public void setInorder() {
	  list.clear();
      this.inorder(tree.root);
  }
/**
 * Sets iterator to postorder traversal
 */
    public void setPostorder() {
	  list.clear();
          this.postorder(tree.root);
  }

/**
 * Function for preorder traversal
 * @param node 
 */
    private void preorder(TreeNode<E> node) {
        
        if(node != null){
            
            this.list.add(node);
            preorder(node.getLeft());
            preorder(node.getRight());
    	
        }
    }

/**
 * Function for inorder traversal
 * @param node 
 */  
    private void inorder(TreeNode<E> node) {
	  if(node != null){
              
	    	inorder(node.getLeft());
	    	this.list.add(node);
	    	inorder(node.getRight());
	    	
	    }
  }
/**
 * Function for postorder traversal
 * @param node 
 */
  
    private void postorder(TreeNode<E> node) {
	  if(node != null){
              
		postorder(node.getLeft());
	    	postorder(node.getRight());
	    	this.list.add(node);
	    	
	    }
    }
    
}
