
/**
 *
 * @author eeshn
 */
public class Packet {
    
    
    
        private static int packetCount = 0;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest = packetSize/100;
	
	public Packet() {
		
            packetCount++;
	}

    public Packet(int id, int packetSize, int timeArrive) {
        this.id = id;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        packetCount++;
    }

	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}
	
	public int getPacketCount() {
		return packetCount;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	
	public int getPacketSize() {
		return packetSize;
	}
	
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}
	
	public int getTimeArrive() {
		return timeArrive;
	}
	
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}
	
	public int getTimeToDest() {
		return timeToDest;
	}
	
	public String toString() {
		return String.format("[%d, %d, %d]", this.getId(), this.getTimeArrive(), 
		  this.getTimeToDest());
	}

}
