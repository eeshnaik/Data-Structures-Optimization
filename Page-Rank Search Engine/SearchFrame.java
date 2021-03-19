
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author eeshn
 */
public class SearchFrame extends javax.swing.JFrame {
    
    static WebGraph web;

    public SearchFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        urlPrint = new javax.swing.JButton();
        quit = new javax.swing.JButton();
        pageRemove = new javax.swing.JButton();
        pageAdd = new javax.swing.JButton();
        linkAdd = new javax.swing.JButton();
        rankPrint = new javax.swing.JButton();
        indexPrint = new javax.swing.JButton();
        linkRemove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        search = new javax.swing.JButton();
        tf1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        menu = new javax.swing.JButton();
        clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        output.setColumns(20);
        output.setFont(new java.awt.Font("Courier New", 0, 20)); // NOI18N
        output.setRows(5);
        output.setText("Welcome to Search Engine GUI! Use the buttons on the left to perform functions\nExperiment with the design of your own web by adding and removing pages and links.\n\nPrint in any custom format using the print buttons and clear the console with the clear button.\n\nYou can search up pages in your web using the search bar at the bottom.\nRemember! You can also go back to Menu to change your text files!\n\n");
        output.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(output);

        urlPrint.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        urlPrint.setText("Print by URL (ASC)");
        urlPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlPrintActionPerformed(evt);
            }
        });

        quit.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        quit.setText("Quit");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        pageRemove.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        pageRemove.setText("Remove page");
        pageRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageRemoveActionPerformed(evt);
            }
        });

        pageAdd.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        pageAdd.setText("Add page");
        pageAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageAddActionPerformed(evt);
            }
        });

        linkAdd.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        linkAdd.setText("Add a link between pages");
        linkAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkAddActionPerformed(evt);
            }
        });

        rankPrint.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        rankPrint.setText("Print by Rank (DSC)");
        rankPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankPrintActionPerformed(evt);
            }
        });

        indexPrint.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        indexPrint.setText("Print by Index (ASC)");
        indexPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexPrintActionPerformed(evt);
            }
        });

        linkRemove.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        linkRemove.setText("Remove a link between pages");
        linkRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkRemoveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Search Engine");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        search.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        tf1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setText("Search Keyword:");

        menu.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        menu.setText("Back To Menu");
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(urlPrint)
                            .addComponent(rankPrint)
                            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(indexPrint)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pageAdd)
                                .addGap(59, 59, 59)
                                .addComponent(pageRemove))
                            .addComponent(linkRemove)
                            .addComponent(linkAdd)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pageAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pageRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(linkAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(linkRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(indexPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(rankPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(urlPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(menu)
                            .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(quit)
                        .addContainerGap(56, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pageAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageAddActionPerformed
        
        new AddPage().setVisible(true);
    }//GEN-LAST:event_pageAddActionPerformed

    private void pageRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageRemoveActionPerformed
        // TODO add your handling code here:
        
        new RemovePage().setVisible(true);
    }//GEN-LAST:event_pageRemoveActionPerformed

    private void linkAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkAddActionPerformed
        // TODO add your handling code here:
        new AddLink().setVisible(true);
    }//GEN-LAST:event_linkAddActionPerformed

    private void linkRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkRemoveActionPerformed
        // TODO add your handling code here:
        new RemoveLink().setVisible(true);
    }//GEN-LAST:event_linkRemoveActionPerformed

    private void indexPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexPrintActionPerformed
        // TODO add your handling code here:
        
        ArrayList<WebPage> in = new ArrayList<>(web.getPages());
                                    
        Collections.sort(in, new IndexComparator());
                                    
        web.printCustomTable(in);
    }//GEN-LAST:event_indexPrintActionPerformed

    private void rankPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankPrintActionPerformed
        // TODO add your handling code here:
        
        ArrayList<WebPage> ran = new ArrayList<>(web.getPages());
        Collections.sort(ran, new RankComparator());
        web.printCustomTable(ran);
    }//GEN-LAST:event_rankPrintActionPerformed

    private void urlPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlPrintActionPerformed
        // TODO add your handling code here:
        
        ArrayList<WebPage> ur = new ArrayList<>(web.getPages());
        Collections.sort(ur, new URLComparator());
        web.printCustomTable(ur);
    }//GEN-LAST:event_urlPrintActionPerformed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        // TODO add your handling code here:
        
        System.exit(0);
    }//GEN-LAST:event_quitActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        
        String sr = tf1.getText();
        
        ArrayList<WebPage> keyPages = new ArrayList<>();
                            
        for (WebPage p1 : web.getPages()) {
                                
            if (p1.getKeywords().contains(sr)) {
                                    
                keyPages.add(p1);
                                    
            }
                                
        }
                            
        if (keyPages.isEmpty()) {
            SearchFrame.output.append("No search results found!\n");
        }
        else {
                                
        Collections.sort(keyPages, new RankComparator());
        
        SearchFrame.output.append("Search Results for " + sr + ":\n\n");
           
        SearchFrame.output.append(String.format("%-10s%-18s%s", 
		  "Result", "URL", "PageRank"));
            
            SearchFrame.output.append("\n");
            SearchFrame.output.append("------------------------------------------------------\n");
                
                            
            for (int i = 0; i < keyPages.size(); i++) {

                SearchFrame.output.append(String.format("   %-6d%-23s%d",(i+1),keyPages.get(i).getUrl(),keyPages.get(i).getRank()));
                SearchFrame.output.append("\n");
            }
                                
        }
        
        SearchFrame.output.append("\n\n");
        tf1.setText("");
    }//GEN-LAST:event_searchActionPerformed

    private void tf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf1ActionPerformed
        
        this.searchActionPerformed(evt);
        
    }//GEN-LAST:event_tf1ActionPerformed

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
        
        this.dispose();
        new StartPage().setVisible(true);
        
        
    }//GEN-LAST:event_menuActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        output.setText("");
    }//GEN-LAST:event_clearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JButton indexPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton linkAdd;
    private javax.swing.JButton linkRemove;
    private javax.swing.JButton menu;
    public static javax.swing.JTextArea output;
    private javax.swing.JButton pageAdd;
    private javax.swing.JButton pageRemove;
    private javax.swing.JButton quit;
    private javax.swing.JButton rankPrint;
    private javax.swing.JButton search;
    private javax.swing.JTextField tf1;
    private javax.swing.JButton urlPrint;
    // End of variables declaration//GEN-END:variables
}
