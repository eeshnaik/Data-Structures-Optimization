enum BLOCK_TYPES {
        
        DEF,FOR,WHILE,IF,ELIF,ELSE
        
    }

public class CodeBlock {
    
    
    
    private String name;
    private Complexity blockcomp;
    private Complexity highestsubcomp;
    private String loopvar;
    private BLOCK_TYPES bt;
    
    public CodeBlock() {
        
        this.blockcomp = new Complexity();
        this.highestsubcomp = new Complexity();
        this.loopvar = null;
        
    }
    
    public CodeBlock(String n) {
        
        this.blockcomp = new Complexity();
        this.highestsubcomp = new Complexity();
        
        if (n.equals("n")) {
            this.blockcomp.setNpower(1);
            
        }
        
        else if (n.equals("logn")) {
            this.blockcomp.setLogpower(1);
        }
        
        else if (n.equals("1")) {
            this.blockcomp.setLogpower(0);
            this.blockcomp.setNpower(0);
        }
        
        this.highestsubcomp.setLogpower(0);
        this.highestsubcomp.setNpower(0);
        
        this.loopvar = null;
        
       
        
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Complexity getBlockcomp() {
        return blockcomp;
    }

    public void setBlockcomp(Complexity blockcomp) {
        this.blockcomp = blockcomp;
    }

    public Complexity getHighestsubcomp() {
        return highestsubcomp;
    }

    public void setHighestsubcomp(Complexity highestsubcomp) {
        this.highestsubcomp = highestsubcomp;
    }

    public String getLoopvar() {
        return loopvar;
    }

    public void setLoopvar(String loopvar) {
        this.loopvar = loopvar;
    }

    public void setBt(BLOCK_TYPES bt) {
        this.bt = bt;
    }

    public BLOCK_TYPES getBt() {
        return bt;
    }
    
    public Complexity totalComp() {
        Complexity total = new Complexity();
        total.setLogpower(this.blockcomp.getLogpower()+ this.highestsubcomp.getLogpower());
        total.setNpower(this.blockcomp.getNpower() + this.highestsubcomp.getNpower());
        
        return total;
        
    }
    
    public String toString() {
        return "\n" + "Block: " + this.name + " \t" + " BlockComp: " + this.blockcomp.toString() + " \t" +
                "highestsubcomp: " + this.highestsubcomp.toString() + "\n";
    }
    
    
    
    
    
    
    
    
    

}
