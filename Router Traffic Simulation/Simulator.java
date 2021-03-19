


import java.util.*;




public class Simulator {

	private Router dispatcher = new Router();
	private ArrayList<Router> routers; 
	private int totalServiceTime=0;
	private int totalPacketsArrived;
	private int packetsDropped=0;
	private double arrivalProb;
	private int numIntRouters;
	private int maxBufferSize;
	private int minPacketSize;
	private int maxPacketSize;
	private int bandwidth;
	private int duration;
        public static final int MAX_PACKETS = 3;
        static int journ = 0;
        
        public Simulator() {
            
        }
        
        
	
	public void setDispatcher(Router dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public Router getDispatcher() {
		return dispatcher;
	}
	
	public void setRouters(ArrayList<Router> routers) {
		this.routers = routers;
	}
	
	public Collection<Router> getRouter(){
		return routers;
	}
	
	public void setTotalServiceTime(int totalServiceTime) {
		this.totalServiceTime = totalServiceTime;
	}
	
	public int getTotalServiceTime() {
		return totalServiceTime;
	}
	
	public void setTotalPacketsArrived(int totalPacketsArrived) {
		this.totalPacketsArrived = totalPacketsArrived;
	}
	
	public int getTotalPacketsArrived() {
		return totalPacketsArrived;
	}
	
	public void setPacketsDropped(int packetsDropped) {
		this.packetsDropped = packetsDropped;
	}
	
	public int getPacketsDropped() {
		return packetsDropped;
	}
	
	public void setArrivalProb(double arrivalProb) {
		this.arrivalProb = arrivalProb;
	}
	
	public double getArrivalProb() {
		return arrivalProb;
	}
	
	public void setNumIntRouters(int numIntRouters) {
		this.numIntRouters = numIntRouters;
	}
	
	public int getNumIntRouters() {
		return numIntRouters;
	}
	
	public void setMaxBufferSize(int maxBufferSize) {
		this.maxBufferSize = maxBufferSize;
	}
	
	public int getMaxBufferSize() {
		return maxBufferSize;
	}
	
	public void setMinPacketSize(int minPacketSize) {
		this.minPacketSize = minPacketSize;
	}
	
	public int getMinPacketSize() {
		return minPacketSize;
	}
	
	public void setMaxPacketSize(int maxPacketSize) {
		this.maxPacketSize = maxPacketSize;
	}
	
	public int getMaxPacketSize() {
		return maxPacketSize;
	}
	
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	
	public int getBandwidth() {
		return bandwidth;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}
        
        public boolean iscong(int bw) {
            boolean c=false;
            
            for (int i =0; i < this.routers.size();i++) {
                if (routers.get(i).size()== bw) {
                    c=true;
                    
                }
                else {
                    return false;
                }
            }
            return true;
        }
	
	public double simulate(int bandwidth, double arrivalProb, int numRouter, 
                int maxBufferSize, int minPacketSize, int maxPacketSize, int duration) throws Exception {
            int time=0;
            double served = 0;
            this.routers = new ArrayList<>();
            for (int i=0;i<numRouter;i++) {
                routers.add(new Router());
            }
            
            while (time < duration) {
                System.out.println("Time: " + time);
                /**
                 * Creates packets in dispacther
                 */
                for (int i=0;i < MAX_PACKETS; i++) {
                    if (Math.random() < arrivalProb) {
                        
                        Packet p1 = new Packet();
                        p1.setPacketSize(randInt(minPacketSize,maxPacketSize));
                        p1.setId(p1.getPacketCount());
                        p1.setTimeArrive(time);
                        p1.setTimeToDest(p1.getPacketSize()/100);
                        System.out.println("Packet " + p1.getId()+ " arrived to dispatch with size " + p1.getPacketSize());
                        
                        dispatcher.enqueue(p1); //id,size,timear,timedest
                    }
                }
                /**
                 * Empties dispatcher, sends packets to intermediate routers
                 */
                while (!dispatcher.getRouter().isEmpty()) {
                    
                    
                    Packet p1 = dispatcher.dequeue();
                    
                    if (iscong(maxBufferSize)) {
                        
                        System.out.println("network congested, dropped Packet " + p1.getId());
                        packetsDropped++;
                        
                        
                    }
                    
                    else {
                        int i = +1;
                        System.out.println("Packet " + p1.getId() + " sent to router " + (Router.sendPacketTo(this.routers) + 1));
                        this.routers.get(Router.sendPacketTo(this.routers)).enqueue(p1);
                        
                        
                    }
                            
                    
                    
                    
                }
                
                /**
                 * Decrements time to reach destination in packets inside intermediate routers
                 */
                
                for (int i=0; i < routers.size();i++) {
                    
                    if (!routers.get(i).getRouter().isEmpty()) {
                        
                        routers.get(i).getRouter().get(0).setTimeToDest(routers.get(i).getRouter().get(0).getTimeToDest()-1);
                        
                    }
                }
                
                /**
                 * sends packets to destination while accounting for bandwidth and journeytime is stored
                 */
                for (int i=0,j=0; i < routers.size() && j <= bandwidth; i++) {
                    
                    if (!routers.get(i).getRouter().isEmpty() && 
                        routers.get(i).getRouter().get(0).getTimeToDest()<=0){
                        
                        Packet p1 = routers.get(i).dequeue();
                        journ = time - p1.getTimeArrive();
                        System.out.println("Packet " + p1.getId() + " reached dest. " + journ);
                        served++;
                        
                        
                        
                        
                        j++;
                        
                        
                        
                    }
                }
                
                
                time++;
                
                for (int i=0; i < routers.size();i++) {
                    System.out.println(routers.get(i).toString());
                }
            }
		
            System.out.println("Total service time: " + time);
            System.out.println("Packets dropped: " + packetsDropped);
            System.out.println("Average service time: " + time/served);
            System.out.println("Packets served: " + served);
            return journ;
            
	}
	
	private int randInt(int minVal, int maxVal) {
            
            return (int)(Math.random() * ((maxVal - minVal) + 1)) + minVal;
		
	}
	
	public static void main(String[] args) throws Exception {
            Scanner sc = new Scanner(System.in);
            Simulator s1 = new Simulator();
            
         
            
            s1.simulate(4, 0.5, 6, 3, 500, 1700, 20);
		
	}
	
	
        
        
    
    
}
