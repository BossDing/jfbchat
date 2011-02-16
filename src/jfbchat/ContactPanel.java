 /* ###########################################################################
  *
  *  JFBChat it's a simple software written in Java that let you int contact
  *  with yours Facebook friends without your browser.
  *  Copyright (C) 2011  Digitex (Giuseppe Federico)
  *
  *  This program is free software: you can redistribute it and/or modify
  *  it under the terms of the GNU General Public License as published by
  *  the Free Software Foundation, either version 3 of the License, or
  *  (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  *
  *###########################################################################
  *
  */

package jfbchat;

import java.awt.Color;
import javax.swing.ImageIcon;

public class ContactPanel extends javax.swing.JPanel {
    private final String availableIcon = "/jfbchat/imgs/availableIcon.png";
    private final String awayIcon = "/jfbchat/imgs/awayIcon.png";

    private Contact contact;
    private Connection connection;

    /** Creates new form ContactPanel */
    public ContactPanel(Connection connection, Contact contact) {

        this.contact = contact;
        this.connection = connection;


        initComponents();

        //Init the contact username
        contactLabel.setText(contact.getUser());

        update(contact);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contactLabel = new javax.swing.JLabel();
        stateIcon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        contactLabel.setBackground(new java.awt.Color(255, 255, 255));
        contactLabel.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        contactLabel.setForeground(new java.awt.Color(109, 132, 180));
        contactLabel.setText("Cotact");
        contactLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        stateIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jfbchat/imgs/availableIcon.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contactLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(stateIcon)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contactLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stateIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  
    public void update(Contact contact){

        this.contact = contact;



          //If the contact is available change the icon
        if (contact.getPresence().isAway()){

            stateIcon.setIcon(new ImageIcon(getClass().getResource(awayIcon)));
            setVisible(true);


        }else {
            if ((contact.getPresence().isAvailable())){
            stateIcon.setIcon(new ImageIcon(getClass().getResource(availableIcon)));
            setVisible(true);
            }
            else{
                setVisible(false);
            }

        }
    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
        MyChatManager chatManager = connection.getChatManager();
        //The chatFrame associated to the contact
        ChatFrame chatFrame = chatManager.getChat(contact.getID());

        
        if (contact.isActive()){
            //If the chat is present in the chatmanager show it.

            if (chatFrame.isVisible() == false){

                  chatFrame.setVisible(true);
                        
            }

            System.out.print("The chat is already present in the chat manager with id[");
            System.out.println(contact.getID() + "]");
        }



       else{
            /*If the chat is NOT present in the chatmanager then create it and
             * set the contact active for the chatmanager.
             */

            contact.setActive(true);
            chatManager.add(new ChatFrame(connection, contact), contact.getID());
            

            
            //TODO: make with exceptions here..
            //TODO: set the focus on the window and maximize it if minimized
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        this.setBackground(new Color(109,132,180));
        contactLabel.setForeground(Color.white);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        this.setBackground(Color.white);
        contactLabel.setForeground(new Color(109,132,180));
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contactLabel;
    private javax.swing.JLabel stateIcon;
    // End of variables declaration//GEN-END:variables

}
