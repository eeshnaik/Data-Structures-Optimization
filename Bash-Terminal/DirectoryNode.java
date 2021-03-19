
/**
 *
 * @author eeshn
 */
import java.util.*;

public class DirectoryNode {
    
    
    
	private String name;
	private DirectoryNode [] children = new DirectoryNode[10];
	private boolean isFile;
	
	public DirectoryNode() {
            
		
	}
	public DirectoryNode(String name) {
		this.name = name;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DirectoryNode [] getChildren() {
            return children;
        }
	public boolean getIsFile() {
		return isFile;
	}
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	public void addChild(DirectoryNode node) throws NotADirectoryException, FullDirectoryException{
		if (this.getIsFile() == true) {
                    System.out.println("ERROR: Cannot add to file");
		}
                
                if (this.isFull() == true) {
                    System.out.println("ERROR: Directory full");
                }
                
		else {
			int i = 0;
                        
                        while (children[i] != null) {
                            i++;
                        }
                        
                        children[i] = node;
		}	
		
	}
        
        public boolean isFull() {
            return children[9] != null;
        }
	
}
