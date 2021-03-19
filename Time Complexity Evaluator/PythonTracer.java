
import java.util.*;
import java.io.*;


public class PythonTracer {
    
    public static final int SPACE_COUNT = 4;
    static CodeBlock oldtop;
    static int indents;
    static Stack<CodeBlock> st = new Stack();
    static int[] namelen = new int[100];
    
    
    
    public static Complexity traceFile(String file) throws FileNotFoundException {
        
        
        Scanner sc = new Scanner(new File("C:\\Users\\eeshn\\Desktop\\mult.txt")); 
        
        
        Arrays.fill(namelen,0);
        while (sc.hasNext()) {
            
            String line = sc.nextLine();
            
            
            
            if (!line.isEmpty() && !line.contains("#")) {
                
                
                
                
                
                indents = line.indexOf(line.trim()) / 4;
                
                
               
               
               
               while (indents < st.size()) {
                   
                   
                   if (indents == 0) {
                       sc.close();
                       
                       return st.peek().totalComp();
                   }
                   
                   else {
                       oldtop = st.pop();
                       
                       System.out.println("leaving block " + oldtop.getName());
                       
                       
                       if (oldtop.totalComp().getMag() > st.peek().getHighestsubcomp().getMag()) {
                           System.out.println("updating block " + st.peek().getName());
                           st.peek().getHighestsubcomp().setLogpower(oldtop.totalComp().getLogpower());
                           st.peek().getHighestsubcomp().setNpower(oldtop.totalComp().getNpower());
                           System.out.println(st.peek().toString());
                           
                           
                       }
                   }
               } //while indents < size
                   
                   
                if (line.contains("for ") || line.contains("while") || line.contains("def") ||
                        line.contains("if") || line.contains("else") || line.contains("elif")) {
                    
                    
                    
                    if (line.contains("for ")) {
                        
                        if (line.contains("in N:")) {
                            
                            CodeBlock cb = new CodeBlock("n");
                            cb.setName(name(indents));
                            
                            System.out.println("entering for block "+ cb.getName());
                            System.out.println(cb.toString());
                            st.push(cb);
                            
                        }
                        else if (line.contains("in log_N:")) {
                            CodeBlock cb = new CodeBlock("logn");
                            cb.setName(name(indents));
                            
                            System.out.println("entering for block "+ cb.getName());
                            System.out.println(cb.toString());
                            st.push(cb);
                            
                        }
                        
                    }
                    
                    else if (line.contains("while ")) {
                        
                        CodeBlock cb = new CodeBlock("1");
                        cb.setName(name(indents));
                        cb.setLoopvar(line.charAt(line.indexOf("while") + 6) + "");
                        cb.setBt(BLOCK_TYPES.WHILE);
                        
                        System.out.println("entering while block "+ cb.getName());
                        System.out.println(cb.toString());
                        
                        st.push(cb);
                        
                    }
                    
                    else if (line.contains("def ")) {
                        CodeBlock cb = new CodeBlock("1");
                        cb.setName(name(indents));
                            
                        System.out.println("entering def block "+ cb.getName());
                        System.out.println(cb.toString());
                        st.push(cb);
                    }
                    else if (line.contains("if ")) {
                        CodeBlock cb = new CodeBlock("1");
                            cb.setName(name(indents));
                            
                            System.out.println("entering if block "+ cb.getName());
                            System.out.println(cb.toString());
                            st.push(cb);
                    }
                    else if (line.contains("else ")) {
                         CodeBlock cb = new CodeBlock("1");
                            cb.setName(name(indents));
                            
                            System.out.println("entering else block "+ cb.getName());
                            System.out.println(cb.toString());
                            st.push(cb);
                        
                    }
                    
                    else if (line.contains("elif ")) {
                         CodeBlock cb = new CodeBlock("1");
                            cb.setName(name(indents));
                            
                            System.out.println("entering elif block "+ cb.getName());
                            System.out.println(cb.toString());
                            st.push(cb);
                    }
                    
                    
                } //if there's a keyword
                
                else if (st.peek().getBt() == BLOCK_TYPES.WHILE && (line.contains(st.peek().getLoopvar() + " -= 1")
                        || line.contains(st.peek().getLoopvar() + " /= 2"))) {
                    
                    System.out.println("Found update statement: ");
                    System.out.println("Updating block " + st.peek().getName());
                    
                    
                    
                    if (line.contains("/=2")) {
                        st.peek().getBlockcomp().setLogpower(1);
                        
                        
                    }
                    else {
                        st.peek().getBlockcomp().setNpower(1);
                    }
                    
                    System.out.println(st.peek());
                    
                } //else if
                
               
                
               }//if line is valid
            
            
                
            }//while sc.hasNext() loop
        
        while (st.size() > 1) {
            oldtop = st.pop();
            if (oldtop.totalComp().getMag() > st.peek().getHighestsubcomp().getMag()) {
                st.peek().getHighestsubcomp().setLogpower(oldtop.totalComp().getLogpower());
                st.peek().getHighestsubcomp().setNpower(oldtop.totalComp().getNpower());
            }
        }//"merges" all remaining blocks
        
        
        
        
        return st.pop().totalComp();
            
        }//tracefile
    
    public static String name(int indent) {
        String name = "1";
        
        
        for (int i =0; i < indent;i++) {
            name += ".1";
            
        }
        
        
        
        namelen[name.replaceAll("\\.", "").length()]++;
        
        if (namelen[name.replaceAll("\\.", "").length()] > 0 && name.replaceAll("\\.", "").length() > 1) {
            
            
            
            name = name.substring(0,name.length()-1);
            
            
            name += namelen[name.replaceAll("\\.", "").length()+1] + "";
        }
        
        
        
        
        
        return name;
    }

        
        
        
    
    
    

 
    public static void main(String[] args) throws FileNotFoundException {
        
                
        
        
        
        System.out.println(traceFile("test.txt").toString());
        
        
        
        
        
        
        
        
    }
    
}
