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


public class PanelMessage extends javax.swing.JPanel {

    /** Creates new form PanelMessage */
    public PanelMessage(boolean send,Contact contact, String text) {

        initComponents();
        if (send){
            Labelfromto.setText("Me: ");
        }
        else{
            if(text == null){
                Labelfromto.setText(contact.getUser() + " is typing...");
            }else{
            Labelfromto.setText(contact.getUser() + " says: ");
            }
        }

        LabelText.setText(text);
       
       
        setVisible(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Labelfromto = new javax.swing.JLabel();
        LabelText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        Labelfromto.setFont(new java.awt.Font("Arial", 1, 15));
        Labelfromto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Labelfromto.setText("... say: / me:");
        Labelfromto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Labelfromto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(Labelfromto);

        LabelText.setText("Some text here..");
        add(LabelText);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelText;
    private javax.swing.JLabel Labelfromto;
    // End of variables declaration//GEN-END:variables

}
