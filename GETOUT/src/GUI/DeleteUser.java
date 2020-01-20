//2020 Jan 21 Fred Chen, Ashwin Boni Bangari, Sam Rogers
/*
 * Gui to delete a user.
 * The gui contains a table that displays the user's email, name, and an option to delete it.
 * This gui is only accessable by lvl 2 and 3 users.
 */
package GUI;

import Backend.ParseJson;
import Backend.User;
import getout.GETOUT;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Fred Chen and Sam Rodgers
 */
public class DeleteUser extends javax.swing.JFrame {

    /**
     * Creates new form DeleteUser
     */
    Object[][] data;

    public DeleteUser() {
        //The table takes a 2 dimensional array as input to display
        //Since one cannot delete oneself or the admin account, this determines how many users there are other than those two
        int accNum = LoginScreen.currentUser.getEmail().equals(GETOUT.ADMIN_EMAIL) ? 1 : 2;
        
        int counter = 0;
        
        data = new Object[GETOUT.users.size() - accNum][3];
        
        //For each user in the list users, we assign the email, name, to a spot on the data[][] array to be displayed
        //This will skip over the admin and current users to make sure that they can't be deleted
        for (int i = 0; i < GETOUT.users.size() - accNum; i++) {
            for (int j = counter; j < GETOUT.users.size(); j++) {
                counter++;
                if (!GETOUT.users.get(j).getEmail().equals(GETOUT.ADMIN_EMAIL)
                        && !GETOUT.users.get(j).getEmail().equals(LoginScreen.currentUser.getEmail())) {
                    System.out.println(j);

                    data[i][0] = GETOUT.users.get(j).getName();
                    data[i][1] = GETOUT.users.get(j).getEmail();
                    data[i][2] = false;
                    break;
                }

            }

        }
        initComponents();
        
        //***********Table UI formatting******************
        table.setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        table.setShowGrid(false);
        table.getTableHeader().setOpaque(false);
        //************************************************


    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        confirmDelete = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(355, 518));
        setSize(new java.awt.Dimension(365, 518));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Delete Users");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(100, 50, 150, 33);

        confirmDelete.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        confirmDelete.setForeground(new java.awt.Color(255, 255, 255));
        confirmDelete.setText("Confirm Delete");
        confirmDelete.setBorder(null);
        confirmDelete.setBorderPainted(false);
        confirmDelete.setContentAreaFilled(false);
        confirmDelete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        confirmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(confirmDelete);
        confirmDelete.setBounds(110, 390, 130, 50);

        cancel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 255, 255));
        cancel.setText("Cancel");
        cancel.setBorder(null);
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        getContentPane().add(cancel);
        cancel.setBounds(140, 450, 39, 17);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jScrollPane1.setOpaque(false);

        table.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String [] {
                "Name", "Email", "Delete?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setOpaque(false);
        table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        table.setSelectionForeground(new java.awt.Color(204, 255, 255));
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 320, 250);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DeleteUserBackground.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 350, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        new MenuScreen().setVisible(true);
        dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void confirmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmDeleteActionPerformed

        //Going through the table, it checks whether the checkbox beside each user is checked.
        //If so, then the user is removed from the master list.
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if ((boolean) table.getValueAt(i, 2)) {
                final String TEMP_EMAIL = (String) table.getValueAt(i, 1);
                GETOUT.users.removeIf((User u) -> u.getEmail().equals(TEMP_EMAIL));
                
            }
        }
        //The updated list is then saved to a file.
        ParseJson.writeToFile(GETOUT.users, GETOUT.USERS_FILE_PATH);
        
        java.awt.EventQueue.invokeLater(() -> {
            new ErrorPanel("User(s) Deleted Successfully", true).setVisible(true);
        });
        dispose();
        
        new MenuScreen().setVisible(true);       
        
        
    }//GEN-LAST:event_confirmDeleteActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton confirmDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
