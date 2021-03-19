import java.io.*;
import java.util.*;

public class WebGraph {

	private static final int MAX_PAGES = 40;
        
	private ArrayList<WebPage> pages = new ArrayList<>();
        
	private int[][] edges = new int[MAX_PAGES][MAX_PAGES]; 
	
	public WebGraph() {
            
            
            
		
	}

        public ArrayList<WebPage> getPages() {
            return pages;
        }

        public int [][] getEdges() {
            return edges;
        }
        
        
	
	public void addPage(String url, ArrayList<String> keywords) 
	  throws IllegalArgumentException {
            
            
            
            WebPage p1 = new WebPage(url, keywords);
            p1.setIndex(this.pages.size());
            
            pages.add(p1);
            
            this.updatePageRanks();
            this.updateLinks();
            
	}
	
	public void addLink(String source, String destination) 
	  throws IllegalArgumentException {
            
            
            this.edges[pageIndex(source)][pageIndex(destination)] = 1;
            
            this.updatePageRanks();
            this.updateLinks();
		
	}
	
	public void removePage(String url) {
            
            if (!linkValid(url)) {
                
                
                
            }
            else {
                
                int rem = pageIndex(url); //index to remove
                
                /**
                 * Removes required row
                 */
                for (int i = rem; i < pages.size(); i++) {
                    
                    for (int j = 0; j < pages.size(); j++) {
                        
                        if (i == pages.size()-1) { //if row is at the edge, just replace with 0's
                            edges[i][j] = 0;
                        }
                        else {
                            
                            edges[i][j] = edges[i+1][j];
                            
                        }
                        
                    }
                    
                }
                /**
                 * Removes required column
                 */
                for (int j = rem; j < pages.size(); j++) {
                    for (int i = 0; i < pages.size()-1; i++) {
                        
                        if (j == pages.size() - 1) { //if col is at the edge, just replace with 0's
                            edges[i][j] = 0;
                        }
                        
                        else {
                            edges[i][j] = edges[i][j+1];
                        }
                        
                    }
                }
                
                pages.remove(rem);
                
                for (int i = 0; i < pages.size(); i++) {
                    
                    pages.get(i).setIndex(i);
                }
                
            this.updatePageRanks();
            
            this.updateLinks();
                
            }
            
            
	}
	
	public void removeLink(String source, String destination) {
            
            if (!linkValid(source) || !linkValid(destination)) {
                
            }
            
            else {
                
                this.edges[pageIndex(source)][pageIndex(destination)] = 0;
            
                this.updatePageRanks();
                this.updateLinks();
                
            }
                
	}
        
        /**
         * Adds column values to get each rank
         */
	
	public void updatePageRanks() {
            
            int rank;
            
            
            for (WebPage p1 : this.pages) {
                
                int col = pageIndex(p1.getUrl());
                
                rank = 0;
                
                for (int i = 0; i < this.pages.size(); i++) {
                    
                    rank += this.edges[i][col];
                    
                }
                
                p1.setRank(rank);
                
            }
            
            for (WebPage p1 : pages) {
                
                int row = pageIndex(p1.getUrl());
                
                for (int i = 0; i < pages.size(); i++) {
                    
                    if (edges[row][i] == 1) {
                        p1.getLinks().add(i);
                    }
                }
                
            }
            
	}
        
        /**
         * Adds index of each connection (value of 1) as a link
         */
        
        public void updateLinks() {
            
            for (WebPage p1 : pages) {
                p1.getLinks().clear();
            }
            
            for (int i = 0; i < pages.size(); i++) {
                for (int j = 0; j < pages.size(); j++) {
                    
                    if (edges[i][j] == 1) {
                        pages.get(i).getLinks().add(j);
                    }
                    
                }
            }
            
        }
        
       
	public void printTable() {
            
                
                
		System.out.println(String.format("%-10s%-19s%-10s%-19s%s", 
		  "Index", "URL", "PageRank", "Links", "Keywords"));
		System.out.println("-------------------------------------------------------"
		  + "-----------------------------------------------");
                
                for (WebPage p1 : this.pages) {
                    
                    System.out.println(p1.toString());
                }
                
                
		
	}
        /**
         * Prints custom sorted table
         * @param list sorted list
         */
        public void printCustomTable(ArrayList<WebPage> list) {
            
            SearchFrame.output.append("\n\n");
            
            SearchFrame.output.append(String.format("%-10s%-18s%-10s%-19s%s", 
		  "Index", "URL", "PageRank", "Links", "Keywords"));
            
            SearchFrame.output.append("\n");
            
		SearchFrame.output.append("-------------------------------------------------------"
		  + "-----------------------------------");
                SearchFrame.output.append("\n");
                
                for (WebPage p1 : list) {
                    
                    String links = Arrays.toString(p1.getLinks().toArray()).substring(1, Arrays.toString(p1.getLinks().toArray()).length()-1);
                    String kw = Arrays.toString(p1.getKeywords().toArray()).substring(1, Arrays.toString(p1.getKeywords().toArray()).length()-1);
                    
                    String str = String.format("   %-6d%-23s%-6d%-19s%s",p1.getIndex(),p1.getUrl(),p1.getRank(),links,kw);
                    
                    
                    SearchFrame.output.append(str);
                    SearchFrame.output.append("\n");
                    

                }
                
                SearchFrame.output.append("\n\n");
            
        }
	
	public static WebGraph buildFromFiles(File pagesFile, File linksFile) throws IllegalArgumentException, FileNotFoundException {
            
            WebGraph web = new WebGraph();
            
            Scanner sc = new Scanner(pagesFile);
            int line = 0;
            
            while (sc.hasNext()) {
                
                String input = sc.nextLine();
                
                String [] list = input.split(" ");
                WebPage p1 = new WebPage();
                p1.setIndex(line);
            
                    for (int i = 0; i < list.length; i++) {
                
                            if (i ==0) {
                                p1.setUrl(list[i]);
                            }
                            
                            else {
                                p1.getKeywords().add(list[i]);
                            }
                    }
                    
                web.pages.add(p1);
                line++;
            }
            
            Scanner scan = new Scanner(linksFile);
            
            while (scan.hasNext()) {
                String in = scan.nextLine();
                
                String [] list2 = in.split(" ");
                
                int row = web.pageIndex(list2[0]);
                int col = web.pageIndex(list2[1]);
                
                web.edges[row][col] = 1;
                
            }
            
           web.updatePageRanks();
           
           
           return web; 
	}
        
        
        /**
         * Gives index of url in pages list
         * @param url
         * @return 
         */
        public int pageIndex(String url) {
            
            for (int i = 0; i < pages.size();i++) {
                
                if (pages.get(i).getUrl().equalsIgnoreCase(url)) {
                    return i;
                }
            }
            return -1;
        }
        /**
         * checks if url is valid by matching to webgraph
         * @param input
         * @return 
         */
        public boolean addValid(String input) {
            
            for (WebPage p1 : this.pages) {
                
                if (p1.getUrl().equalsIgnoreCase(input)) {
                    return false;
                }
                
            }
            
            return true;
            
            
        }
        /**
         * checks if url is valid by matching to webgraph
         * @param input
         * @return 
         */
        public boolean linkValid(String input) {
            
            
            for (WebPage p1 : this.pages) {
                
                if (p1.getUrl().equalsIgnoreCase(input)) {
                    return true;
                }
                
            }
            
            return false;
            
        }
        
        
	
}
