/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domain.Pharmacist;
import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import server.Server;

/**
 *
 * @author Milos Milic
 */
public class FrmMain extends javax.swing.JFrame {
Server server;
    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblServer.setText("Server off.");
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblServer = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jmbMain = new javax.swing.JMenuBar();
        jmServer = new javax.swing.JMenu();
        jmiSettings = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmAbout = new javax.swing.JMenu();
        jmiAboutSoftware = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblServer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServer.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btnStart.setText("Start Server");
        btnStart.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setText("Stop Server");
        btnStop.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jmServer.setText("Server");

        jmiSettings.setText("Settings");
        jmiSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSettingsActionPerformed(evt);
            }
        });
        jmServer.add(jmiSettings);

        jMenuItem1.setText("Active pharmacists");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmServer.add(jMenuItem1);

        jmbMain.add(jmServer);

        jmAbout.setText("About");

        jmiAboutSoftware.setText("About Software");
        jmAbout.add(jmiAboutSoftware);

        jmbMain.add(jmAbout);

        setJMenuBar(jmbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblServer, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblServer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSettingsActionPerformed
        new FrmSettings(this, true).setVisible(true);
    }//GEN-LAST:event_jmiSettingsActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
    if (server == null || !server.isAlive()) {
        try {
        server = new Server();
        server.start();
        lblServer.setText("Server live.");
        lblServer.setForeground(Color.GREEN);
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    } catch (IOException ex) {
        Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
    if (server.getServerSocket() != null) {
        try {
        server.closeActivePharmacists();
        server.getServerSocket().close();
        lblServer.setText("Server off.");
        lblServer.setForeground(Color.RED);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        //System.exit(1);
        server.closeActivePharmacists();
    } catch (IOException ex) {
        Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }//GEN-LAST:event_btnStopActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //System.out.println(server.getActivePharmacists());
        if(!server.getServerSocket().isClosed()){
        new FrmActivePharmacists(this, true,server).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Server is closed.");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jmAbout;
    private javax.swing.JMenu jmServer;
    private javax.swing.JMenuBar jmbMain;
    private javax.swing.JMenuItem jmiAboutSoftware;
    private javax.swing.JMenuItem jmiSettings;
    private javax.swing.JLabel lblServer;
    // End of variables declaration//GEN-END:variables
}
