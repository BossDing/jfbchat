/*
   To change this template, choose Tools | Templates
   and open the template in the editor.
*/

/*
   GeneralPanel.java

   Created on 11 mars 2011, 18:08:37
*/

package jfbchat.frames.preferences;

import jfbchat.resources.Options;
import jfbchat.resources.ChatPreferences;

import javax.swing.JCheckBox;

public class GeneralPanel extends javax.swing.JPanel {

  private ChatPreferences prefs;

  /** Creates new form GeneralPanel */
  public GeneralPanel() {
    initComponents();
    this.prefs = new ChatPreferences();
    // Init the options
    chackAutoLogin.setSelected(prefs.getPreferences().getBoolean(Options.AUTOLOGIN, false));
  }

  /** This method is called from within the constructor to
    initialize the form.
    WARNING: Do NOT modify this code. The content of this method is
    always regenerated by the Form Editor.
  */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">// GEN-BEGIN:initComponents
  private void initComponents() {
    jPanel1 = new javax.swing.JPanel();
    chackAutoLogin = new javax.swing.JCheckBox();
    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Login"));
    chackAutoLogin.setText("Autologin");
    chackAutoLogin.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        chackAutoLoginItemStateChanged(evt);
      }
    });
    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chackAutoLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chackAutoLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                              javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>// GEN-END:initComponents

  private void chackAutoLoginItemStateChanged(java.awt.event.ItemEvent
      evt) {// GEN-FIRST:event_chackAutoLoginItemStateChanged
    // Get the item
    JCheckBox item = (JCheckBox) evt.getItem();
    // Change the value in the preferences with the value of the checkbox
    prefs.getPreferences().putBoolean(Options.AUTOLOGIN, item.isSelected());
  }// GEN-LAST:event_chackAutoLoginItemStateChanged

  // Variables declaration - do not modify// GEN-BEGIN:variables
  private javax.swing.JCheckBox chackAutoLogin;
  private javax.swing.JPanel jPanel1;
  // End of variables declaration// GEN-END:variables

}
