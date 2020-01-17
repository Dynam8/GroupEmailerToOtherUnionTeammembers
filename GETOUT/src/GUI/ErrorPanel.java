/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author S332896109
 */
public class ErrorPanel extends javax.swing.JFrame {

    /**
     * Creates new form ErrorPanel
     */
    public ErrorPanel(String errorTxt) {
        initComponents();
        errorMessage.setText("<html>" + errorTxt + "</html>");
        infoImage.setVisible(false);
    }

    public ErrorPanel(String errorTxt, boolean info) {
        initComponents();

        errorMessage.setText("<html>" + errorTxt + "</html>");
        infoImage.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        errorMessage = new javax.swing.JLabel();
        infoImage = new javax.swing.JLabel();
        errorImage = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(370, 350));
        setResizable(false);
        setSize(new java.awt.Dimension(370, 350));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(null);

        errorMessage.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        errorMessage.setForeground(new java.awt.Color(255, 255, 255));
        errorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorMessage.setText("ErrorText");
        errorMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        errorMessage.setAlignmentX(0.5F);
        errorMessage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(errorMessage);
        errorMessage.setBounds(30, 150, 310, 110);

        infoImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/InfoPanel.jpg"))); // NOI18N
        infoImage.setOpaque(true);
        getContentPane().add(infoImage);
        infoImage.setBounds(0, 0, 370, 320);

        errorImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Error.jpg"))); // NOI18N
        getContentPane().add(errorImage);
        errorImage.setBounds(0, 0, 370, 320);

        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton);
        closeButton.setBounds(160, 250, 50, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel errorImage;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JLabel infoImage;
    // End of variables declaration//GEN-END:variables
}
