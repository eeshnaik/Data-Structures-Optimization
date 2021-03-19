
/**
 *
 * @author eeshn
 */
import java.util.*;

public class BashTerminal {
    
    
    public static void main(String[] args) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
		
		Scanner sc = new Scanner(System.in);
                String input;
                DirectoryTree tree = new DirectoryTree();
                
                
                System.out.println("Starting bash terminal...");
                
                do {
                    
                    System.out.println("[enaik@host]: $ ");
                    input = sc.nextLine();
                    
                    
                    if (input.equals("pwd")) {
                        System.out.println(tree.presentWorkingDirectory());
                        
                    }
                    else if (input.equals("ls")) {
                        System.out.println(tree.listDirectory());
                    }
                    else if (input.equals("ls -R")) {
                        tree.printDirectoryTree();
                        System.out.println("\n");
                    }
                    else if (input.equals("exit")) {
                        continue;
                    }
                    else if (input.contains("mkdir ")) {
                        
                        String[] dirs = input.split(" ");
                        String dir = dirs[dirs.length-1];
                        tree.makeDirectory(dir);
                        
                    }
                    else if (input.contains("touch ")) {
                        
                        String[] dirs = input.split(" ");
                        String file = dirs[dirs.length-1];
                        
                        tree.makeFile(file);
                        
                    }
                    else if (input.contains("cd")) {
                        
                        if (input.equals("cd /")) {
                            tree.resetCursor();
                        }
                        else if (input.equals("cd ..")) {
                            tree.moveToParent();
                        }
                        else if (input.contains("cd ")) {
                            if (input.contains("/")) {
                                String[] dirs = input.split(" ");
                                String path = dirs[dirs.length-1]; 
                                
                                tree.moveToPath(path);
                            }
                            else {
                                String[] dirs = input.split(" ");
                                String directory = dirs[dirs.length-1];
                                
                                tree.changeDirectory(directory);
                                
                            }
                        }
                        
                    }
                    else if (input.contains("find ")) {
                        String[] dirs = input.split(" ");
                        String node = dirs[dirs.length-1];
                        
                        System.out.println(tree.find(node));
                      
                        
                    }
                    else if (input.contains("mv ")) {
                        
                        String[] dirs = input.split(" ");
                        String dest = dirs[dirs.length-1];
                        String src = dirs[dirs.length-2];
                        
                        tree.moveToDest(src, dest);
                        
                    }
                    
                    else if (input.equals("")) {
                        
                    }
                    else {
                        System.out.println("ERROR: Invalid input, enter again");
                    }
                    
                   
                } while (!input.equalsIgnoreCase("exit"));
		
                
                System.out.println("Program exiting normally...");
                
	}

}
