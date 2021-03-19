

import java.util.*;

public class Router {

    
	
	private ArrayList<Packet> router = new ArrayList<>(0);
	
	public Router() {
            
            this.router = new ArrayList<>(0);
		
	}

    public ArrayList<Packet> getRouter() {
        return this.router;
    }
        
        
	
	
	public void enqueue(Packet p) {
		router.add(p);
	}
	
	public Packet dequeue() throws Exception {
		if (this.router.isEmpty()) {
                    throw new Exception("Queue already empty");
                    
                }
               
                Packet p1 = this.router.get(0);
                    
                this.router.remove(0);
           
                return p1;
                
                
	}
	
	public Packet peek() throws Exception {
		if (this.router.isEmpty()) {
			throw new Exception("Router empty");
		}
                else {
                    return this.router.get(0);
                }
                
	}
	
	public int size() {
		return this.router.size();
	}
	
	public boolean isEmpty() {
		return router.isEmpty();
	}
	
        @Override
	public String toString() {
		String str = "R:{ ";
                
            for (Packet p1 : this.router) {
                str += p1.toString() + ", ";
            }
            
		return str + " }";
	}
	
	public static int sendPacketTo(ArrayList<Router> routers) throws Exception {
		/*
            Makes an arraylist of router sizes
            */
            ArrayList<Integer> buffer = new ArrayList<>(routers.size());
            
            routers.forEach((r1) -> {
                buffer.add(r1.router.size());
        });
            
            return buffer.indexOf(Collections.min(buffer)); // picks router with least stuff in it
		
		
	}
}
