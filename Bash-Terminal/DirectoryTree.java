
/**
 *
 * @author eeshn
 */
import java.util.*;
public class DirectoryTree {
    
    private DirectoryNode root;
    private DirectoryNode cursor;
	
	public DirectoryTree() {
		root = new DirectoryNode("root");
		cursor = root;
	}
        
        public DirectoryTree(int num) {
		root = new DirectoryNode("root");
		cursor = root;
                
	}
	
	public DirectoryNode getRoot() {
		return root;
	}
	public void setRoot(DirectoryNode root) {
		this.root = root;
	}
	public DirectoryNode getCursor() {
		return cursor;
	}
	public void setCursor(DirectoryNode cursor) {
		this.cursor = cursor;
	}
	public void resetCursor() {
		cursor = root;
	}
	public void changeDirectory(String name) throws NotADirectoryException {
            
            DirectoryNode temp = root;
            String [] path = new String [100];
            
            if (search(name,temp,path) && getRef(name,root).getIsFile() == false) {
                
                this.setCursor(getRef(name,root));
                
            }
            else if (getRef(name,root).getIsFile() == true) {
                System.out.println("ERROR: Cannot change directory to a file");
            }
            else {
                System.out.println("ERROR: No such directory found, enter again");
            }
            
            
	}
	public String presentWorkingDirectory() { 
             
        return find(this.cursor.getName());
            	
	}
	public String listDirectory() {
		String str = "";
                
        for (DirectoryNode c1 : cursor.getChildren()) {
            
            if (c1 != null) {
                str += c1.getName() + " ";
                
            }
            
        }
        
        if (str.equals("")) {
                System.out.println("No children");
            }
        
        return str;
        
	}
	public void printDirectoryTree() { //print whole thing formatted
		preOrder(root);
		
	}
	public void makeDirectory(String name) throws FullDirectoryException, IllegalArgumentException, NotADirectoryException {
		
		DirectoryNode node = new DirectoryNode(name);
		cursor.addChild(node);
                
                
                
        }
	public void makeFile(String name) throws FullDirectoryException, IllegalArgumentException, NotADirectoryException{
		DirectoryNode node = new DirectoryNode(name);
                node.setIsFile(true);
		cursor.addChild(node);
		 
	}
	public void preOrder(DirectoryNode cursor) {
		if (cursor != null) {
                    
			if (cursor.getIsFile() == false) {
                            
                            for (int i = 0; i <= tabs(find(cursor.getName())); i++) {
                                System.out.print("    ");
                            }
				System.out.println("|- " + cursor.getName());
				
			} 
			else {
                            for (int i = 0; i <= tabs(find(cursor.getName())); i++) {
                                System.out.print("    ");
                            }
				System.out.println(" - " + cursor.getName());
				

			}
			for (DirectoryNode c1 : cursor.getChildren()) {
                            preOrder(c1);
                        }
		}
		
	}
	
        public String find(String name) {
            
            DirectoryNode temp = root;
            String [] path = new String [100];
            String str = "";
            
            if (search(name,temp,path)) {
                
                for (String s1 : path) {
                    
                    if (s1 == null) {
                        break;
                    }
                    
                    str += s1 + "/";
                    
                }
                
                
            }
            else {
                System.out.println("File Not Found");
                return "";
            }
            
            return str.substring(0,str.length()-1);
        }
        public boolean search(String name, DirectoryNode start, String [] path) {  
          
            if (start == null) {
                return false;
            }  
        
        for (int i =0; i<path.length;i++) {
            if (path[i] == null) {
                path[i] = start.getName();
                break;
            }
        }      
        
        if (start.getName().equalsIgnoreCase(name)) {
            
            return true; 
        }      
             
        for (DirectoryNode c1 : start.getChildren()) {
            
            if (search(name, c1, path) == true) {
                return true;
            }
        }
             
        for (int i=0; i < path.length;i++) {
            if (path[i] == null) {
                path[i-1] = null;
                break;
            }
        }  
        
        return false;              
    }
        public DirectoryNode getRef(String name, DirectoryNode root) {  
          
            if (root == null) {
                return null;
            }  
              
        
        if (root.getName().equalsIgnoreCase(name)) {
            
            return root; 
        }      
             
        for (DirectoryNode c1 : root.getChildren()) {
            
            if (getRef(name, c1) != null) {
                return getRef(name,c1);
            }
        }
          
        
        return null;              
    }
        public DirectoryNode getParent(String name, DirectoryNode root) {
            String [] path = new String [100];
            
            if (search(name,root,path) == false) {
                
                return null;
            }
          
            if (root == null) {
                return null;
            }  
        
             
        for (DirectoryNode c1 : root.getChildren()) {
            
            if (c1 != null && c1.getName().equalsIgnoreCase(name)) {
            
                return root; 
            }
            
        }
              
             
        for (DirectoryNode c1 : root.getChildren()) {
            
            if (getParent(name, c1) != null) {
                return getParent(name,c1);
            }
        }
             
         
        
        return null;              
    }
        public void moveToParent() {
            
            if (getParent(cursor.getName(),root) != null) {
                this.setCursor(getParent(cursor.getName(),root));
            }
            else {
                System.out.println("No such parent found");
            }
        }
        
        public void moveToPath(String path) {
            
            String[] dirs = path.split("/");
            
            String dest = dirs[dirs.length-1];
            
            
            if (getRef(dest,root) != null && getRef(dest,root).getIsFile() == false) {
                
                
                this.setCursor(getRef(dest,root));
                
            }
            else if (getRef(dest,root).getIsFile()) {
                System.out.println("Cannot change directory to a file");
            }
            else {
                System.out.println("No such directory found");
            }
        }
        
        public int tabs(String path) {
            int tabs = 0;
            String[] dirs = path.split("/");
            
            for (int i = 0; i < dirs.length;i++) {
                
                if (!dirs[i].equals("/")) {
                    tabs++;
                    
                }
            }
            
            return tabs -2;
        }
        
        public void moveToDest(String src1, String dest1) throws FullDirectoryException, NotADirectoryException  {
            
            String[] dirs = src1.split("/");String src = dirs[dirs.length-1];
            
            String[] dirs2 = dest1.split("/");String dest = dirs2[dirs2.length-1];
            
            DirectoryNode destNode = getRef(dest,root);
            
            if (getRef(src,root).getName().equals("root"))
            {
                System.out.println("Cannot move root");
                return;
            }            
            if (destNode.isFull()) {
                throw new FullDirectoryException("destination is full");
            }
            
            if (destNode.getIsFile() == true) {
                throw new NotADirectoryException("destination is file");
            }
            
            if (destNode == null || getRef(src,root) == null) {
                System.out.println("No such nodes found");
                
            }
            else {
                
                DirectoryNode parent = getParent(src,root);
                
                DirectoryNode source = getRef(src,root);
                
                for (int i = 0; i < parent.getChildren().length; i++) {
                    
                    
                    if (parent.getChildren()[i] != null && parent.getChildren()[i].getName().equalsIgnoreCase(src)) {
                        
                        parent.getChildren()[i]  = null;
                        
                        while (parent.getChildren()[i] != null) {
                            
                            parent.getChildren()[i] = parent.getChildren()[i+1];
                            
                            i++;
                            
                        }
                        
                        break;
                        
                    }
                    
                    
                }
                
                destNode.addChild(source);
          
            }
                    
                    
        }
        
        
         
        
        
        

}
