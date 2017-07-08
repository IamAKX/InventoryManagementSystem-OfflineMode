/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.src;

import AkashApplications.downloadBarcode.HttpDownloadUtility;
import AkashApplications.networkmanager.SendBulkRequest;
import AkashApplications.networkmanager.ServerConstants;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author akash
 */
public class DownloadBarcode extends javax.swing.JFrame {

    /**
     * Creates new form DownloadBarcode
     */
    private static DownloadBarcode obj = null;
    
    private DownloadBarcode() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        downloadLabel.setVisible(false);
        downloadProgress.setVisible(false);
    }
    
    public static DownloadBarcode getObj() {
        if(obj==null)
            obj = new DownloadBarcode();
        return obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        downloadProgress = new javax.swing.JProgressBar();
        downloadLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        downloadLocation = new javax.swing.JTextField();
        downloadBrowse = new javax.swing.JButton();
        downloadBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Downloading Barcodes");
        setResizable(false);

        downloadProgress.setFocusable(false);
        downloadProgress.setIndeterminate(true);

        downloadLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downloadLabel.setText("Downloading.. Please wait...");
        downloadLabel.setFocusable(false);
        downloadLabel.setInheritsPopupMenu(false);

        jLabel2.setText("Download location");

        downloadBrowse.setText("Browse");
        downloadBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadBrowseActionPerformed(evt);
            }
        });

        downloadBtn.setText("Download");
        downloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downloadProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(downloadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downloadLocation))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(downloadBtn)
                        .addGap(29, 29, 29)
                        .addComponent(downloadBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(downloadLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downloadBrowse)
                    .addComponent(downloadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(downloadLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void downloadBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadBrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose download location");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            downloadLocation.setText(chooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_downloadBrowseActionPerformed

    private void downloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadBtnActionPerformed
        // TODO add your handling code here:
        new DownloadProgress().execute();
    }//GEN-LAST:event_downloadBtnActionPerformed

    class DownloadProgress extends SwingWorker<Integer, String>
    {

        @Override
        protected Integer doInBackground() throws Exception {
            if(downloadLocation.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Choose the location where you want download the folder.","Alert",JOptionPane.WARNING_MESSAGE);
                return 0;
            }
            downloadProgress.setVisible(true);
            downloadLabel.setVisible(true);
            String Response = new SendBulkRequest(new HashMap<String, String>() , new ServerConstants(true).BASE_URL+"Barcodes.zip").serverResponse();
//            String fileURL = ServerConstants.BASE_URL+"Barcodes.zip";
//            try {
//                HttpDownloadUtility.downloadFile(fileURL, downloadLocation.getText());
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//            }

            downloadProgress.setVisible(true);
            downloadLabel.setVisible(true);
            setVisible(false);
            dispose();
            return 0;
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void runDownloadDialog() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DownloadBarcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DownloadBarcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DownloadBarcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DownloadBarcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DownloadBarcode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downloadBrowse;
    private javax.swing.JButton downloadBtn;
    private javax.swing.JLabel downloadLabel;
    private javax.swing.JTextField downloadLocation;
    private javax.swing.JProgressBar downloadProgress;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}