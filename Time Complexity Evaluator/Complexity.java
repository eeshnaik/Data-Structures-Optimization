
/**
 *
 * @author eeshn
 */
public class Complexity {
    
    private int npower=0;
    private int logpower=0;
    
    
    public Complexity() {
        this.logpower = 0;
        this.npower = 0;
    
        
    }

    public int getNpower() {
        return npower;
    }

    public void setNpower(int npower) {
        this.npower = npower;
    }

    public int getLogpower() {
        return logpower;
    }

    public void setLogpower(int logpower) {
        this.logpower = logpower;
    }
    
    public int getMag() {
        return npower + logpower;
    }
    
    
    
    public String toString() {
        return "O(n^" + npower + " * log(n)^" + logpower + ")";
    }

}
