/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Backend.Email;
import Backend.ParseJson;
import Backend.User;

import getout.GETOUT;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Runnable;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import java.net.Socket;
import java.net.ServerSocket;

/**
 *
 * @author S331471193
 */
public class LoginScreen extends javax.swing.JFrame {

    //private final String PATH = "UserCred/users.json";
    /**
     * Creates new form MenuScreen
     */
    public static User currentUser;
    public static Email email;

    public LoginScreen() {
        initComponents();
        //Login.setOpaque(false);
      //  Login.setContentAreaFilled(false);
        //Login.setBorderPainted(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        Login = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 570));
        setPreferredSize(new java.awt.Dimension(450, 595));
        setResizable(false);
        getContentPane().setLayout(null);

        exitButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);

        exitButton.setBounds(371, 520, 60, 25);

        Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/LoginButtonUnpressed.png"))); // NOI18N
        Login.setToolTipText("");
        Login.setAlignmentY(0.0F);
        Login.setBorder(null);
        Login.setBorderPainted(false);
        Login.setContentAreaFilled(false);
        Login.setFocusPainted(false);
        Login.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/LoginButtonPressed.png"))); // NOI18N
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login);
        Login.setBounds(98, 431, 250, 70);

        username.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setBorder(null);
        username.setOpaque(false);
        username.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username);
        username.setBounds(60, 265, 330, 30);

        password.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setAlignmentX(0.0F);
        password.setAlignmentY(0.0F);
        password.setBorder(null);
        password.setOpaque(false);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password);
        password.setBounds(60, 370, 330, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Login.jpg"))); // NOI18N
        jLabel3.setAlignmentY(0.0F);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 450, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            while (!Thread.interrupted()) {
                try {
                    setVisible(false);
                    email = new Email(currentUser.getEmail());
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MenuScreen().setVisible(true);
                        }
                    });
                    dispose();

                } catch (GeneralSecurityException | IOException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            //Thread.sleep(10000); // Just to demo a long running task of 4 seconds.
            return "Ready!";
        }
    }
    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        // TODO add your handling code here:
        /* ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                java.awt.EventQueue.invokeLater(() -> {
                    setVisible(true);
                    new ErrorPanel("Session timed out, please try again").setVisible(true);
                });

            }
        };
        Timer timer = new Timer(3 * 1000, taskPerformer);
        timer.setRepeats(false);
        timer.start();
         */
        //try {
        currentUser = GETOUT.users.stream()
                .filter(user -> username.getText().equals(user.getEmail()))
                .findAny()
                .orElse(null);
        //users.stream().anyMatch(user -> username.equals(user.getEmail())&& password.equals(user.getPassword()))
        if (currentUser == null) {
            // timer.stop();
            java.awt.EventQueue.invokeLater(() -> {
                new ErrorPanel("Not a valid user!").setVisible(true);
            });
        } else {
            if (password.getText().equals(currentUser.getPassword())) {
                // try {
                //  Runnable logInThread = new Runnable() {
                //   public void run() {
                Timer timer = new Timer("Timer");
                Thread loginThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            setVisible(false);
                            email = new Email(currentUser.getEmail());
                            timer.cancel();
                            java.awt.EventQueue.invokeLater(() -> {
                                new MenuScreen().setVisible(true);
                            });

                            dispose();

                        } catch (GeneralSecurityException | IOException ex) {
                            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {

                        setVisible(true);
                        new ErrorPanel("Session timed out, please try again.").setVisible(true);

                        loginThread.stop();
                    }
                };

                loginThread.start();

                timer.schedule(task, 60000);

                System.out.println(1);

                System.out.println(2);
                // timer.cancel();
                /*catch (GeneralSecurityException ex) {
                                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                                }*/

 /*ExecutorService executor = Executors.newSingleThreadExecutor();
                                Future<String> future = executor.submit(new Task());

                                try {
                                    System.out.println(future.get(3, TimeUnit.SECONDS));

                                } catch (TimeoutException e) {
                                    future.cancel(true);

                                    setVisible(true);
                                    java.awt.EventQueue.invokeLater(() -> {
                                        setVisible(true);
                                        new ErrorPanel("Session timed out, please try again").setVisible(true);
                                    });

                                    System.out.println("Terminated!");

                                } catch (InterruptedException | ExecutionException ex) {
                                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                executor.shutdownNow();*/
            } // };
            //  Thread thread = new Thread(logInThread);
            //  thread.start();
            /* try {
                                thread.join();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }*/ // } catch (GeneralSecurityException ex) {
            //      Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            //  }
            else {
                //timer.stop();
                System.out.println("Not the correct password!");
            }
        }
        //  } catch (IOException ex) {
        //     Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }//GEN-LAST:event_LoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
