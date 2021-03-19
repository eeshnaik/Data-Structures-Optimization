import java.util.*;

public class WebPage {

	private String url;
	private int index;
	private int rank;
	private ArrayList<String> keywords = new ArrayList<>();
        private ArrayList<Integer> links = new ArrayList<>();
        
	
	public WebPage() {
		
	}

        public WebPage(String url, ArrayList<String> keywords) {
            
            this.url = url;
            this.keywords = keywords;
        }

        public ArrayList<Integer> getLinks() {
            return links;
        }

        public void setLinks(ArrayList<Integer> links) {
            this.links = links;
        }
        
        
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	public Collection<String> getKeywords() {
		return keywords;
	}
	
	public String toString() {
		String str = String.format("   %-4d%-2s%-19s%-5s%-5d%-2s%-18s%-2s%s", 
		  this.getIndex(), "|", this.getUrl(), "|", this.getRank(), "|", 
		  this.getLinks(), "|", this.getKeywords());
		return str;
	}
	
}