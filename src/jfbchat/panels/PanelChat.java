/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jfbchat.panels;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import jfbchat.Connection;
import jfbchat.Contact;
import jfbchat.debug.DebugMessage;
import jfbchat.frames.ChatFrame;
import jfbchat.labels.IsWritingLabel;
import jfbchat.listeners.MyMessageListener;
import jfbchat.resources.ChatPreferences;
import jfbchat.resources.Options;
import jfbchat.resources.UtilFunctions;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.ChatState;
import org.jivesoftware.smackx.ChatStateManager;

/**
 *
 * @author peppe
 */
public class PanelChat extends javax.swing.JPanel {

  final int K_ENTER_ID = 10;

  private Contact contact;
  private Connection connection;
  private ChatManager chatmanager;
  private Chat relatedChat;
  private String contactAdr;
  private JScrollBar verticalScrollBar;
  private ImageIcon avatarIcon;
  private boolean isWriting;
  private ChatStateManager chatStateManager;
  private int tabIndex;
  private ChatPreferences prefs;

  /** Creates new form ChatFrame */
  public PanelChat(Connection connection, Contact contact) {

  this.connection = connection;
  this.contact = contact;
  this.contactAdr = contact.getAdress();
  this.isWriting = false;
  this.tabIndex = -1;
  this.prefs = new ChatPreferences();

  // Get the chatStateManager of this connection
  this.chatStateManager = ChatStateManager.
        getInstance(this.connection.getConnection());

  // TODO: Every tab should have this icon
  // Init window icon image
  // java.awt.Image contactIcon = contact.getVCard().getAvatar().getImage();

  this.chatmanager = connection.getConnection().getChatManager();

  initComponents();
  // Init the StatusLabel1
  this.statusLabel1.updateLabel(this.contact);
  this.statusLabel1.setVisible(false);

  // Init isWritingLabel1 as not visible
  this.isWritingLabel1.setIsWriting(false);

  this.verticalScrollBar = ScrollMessages.getVerticalScrollBar();

  relatedChat = chatmanager.createChat(contactAdr ,
           new MyMessageListener(
           contact));

  setVisible(true);
  }

  /**
  * Send a message to the contact associated to the ChatFrame
  * @param texttosend
  */
  public void SendMessage(String texttosend) {

  // If texttosend is not empty
  if (! (texttosend.equals(""))) {

    try {

    addPanelMessage(new PanelMessageSent(this.connection,
               texttosend));

    // Clear the TextField
    messageField.setText("");

    relatedChat.sendMessage(texttosend);

    new DebugMessage(this.getClass(),"Sended \""+ texttosend +"\" to " + contact.getUser());

    }catch (XMPPException e) {

    new DebugMessage(this.getClass(), "Cannot send message to " + contact.getUser(), e);

    }

  } else {
    try {
    relatedChat.sendMessage(new Message(null));
    } catch (XMPPException ex) {
    Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  }

  /**
  * Add a message to the PanelMessages.
  * Set the scrollbar at the maximum position for every incoming or
  * outgoing message.
  * Set isWriting to false.
  */
  public void addPanelMessage(PanelMessage panel) {
  if (panel.getMessage() != null) {
    PanelMessages.add(panel);
    validate();
    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    validate();

    // The contact is not writing
    this.isWritingLabel1.setIsWriting(false);

  } else {
    // The contact is writing
    this.isWritingLabel1.setIsWriting(true);

  }
  }

  /**
  * Add a message to the PanelMessages.
  * Set the scrollbar at the maximum position for every incoming or
  * outgoing message.
  * Set isWriting to false.
  */
  public void addPanelMessage(PanelMessageSent panel) {
  if (panel.getMessage() != null) {
    PanelMessages.add(panel);
    validate();
    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    validate();

    // The contact is not writing
    this.isWritingLabel1.setIsWriting(false);

  } else {
    // The contact is writing
    this.isWritingLabel1.setIsWriting(true);

  }
  }
  /**
  * Update the chatframe (the contact might be disconnected after a roster up
  * date)
  */
  public void update() {
  messageField.setEnabled(contact.isAvailable());
  this.statusLabel1.updateLabel(this.contact);
  // Update the statusLabel icon
  this.getTabbedChat().setIconAt(this.getTabIndex()
              ,  this
                .statusLabel1
                .getIcon());

  }

  private void formKeyPressed(java.awt.event.KeyEvent evt) {

  }

  /**
  * MenuItem Close action
  * @param evt
  */
  private void jMenuCloseActionPerformed(java.awt.event.ActionEvent evt) {

  // Hide the window
  this.setVisible(false);
  }

  /**
  * MenuItem Clear action
  * @param evt
  */
  private void jMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {

  // Clear all the messages in the PanelMessages
  this.clearAllMessages();

  }

  private void jMenuItemHelpOnlineActionPerformed(java.awt.event.ActionEvent evt) {

  // Open the ONLINE_HELP page.
  UtilFunctions.openURL(Options.ONLINE_HELP);
  }

  private void jMenuItemReportProblemActionPerformed(java.awt.event.ActionEvent evt) {

  // Open the Options.WEBPAGE_BUG_TRACKER page.
  UtilFunctions.openURL(Options.WEBPAGE_BUG_TRACKER);
  }

  /**
  * @return The jIsWritingLabel label
  */
  public IsWritingLabel getjIsWritingLabel() {
  return this.isWritingLabel1;

  }

  /**
  * This method clears all the messages in the PanelMessages
  */
  public void clearAllMessages() {

  // If PanelMessages is not empty
  if (!(this.PanelMessages.getComponents().length == 0)) {

    try {

    // Clear all the sended and received messages
    this.PanelMessages.removeAll();

    // Validate the PanelMessages
    this.PanelMessages.validate();

    // Repaint the PanelMessages
    this.PanelMessages.repaint();

    new DebugMessage(this.getClass(), "PanelMessages cleared");

    }catch (Exception e) {

    new DebugMessage(this.getClass(), "Cannot clear PanelMessages ", e);

    }

  } else {

    new DebugMessage(this.getClass(), "PanelMessages has nothing to clear");

  }

  }

  public int getTabIndex() {
  return this.tabIndex;

  }

  public void setTabIndex(int tabIndex) {
  this.tabIndex = tabIndex;

  }

  /**
  * Get the contact
  */
  public Contact getContact() {
  return this.contact;

  }

  public JTabbedPane getTabbedChat() {
  return this.connection.getChatFrame().getjTabbedPaneChats();

  }

  /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">// GEN-BEGIN:initComponents
  private void initComponents() {

  MainFrame = new javax.swing.JPanel();
  jPanelScrollMessages = new javax.swing.JPanel();
  ScrollMessages = new javax.swing.JScrollPane();
  PanelMessages = new javax.swing.JPanel();
  jPanelUnderAvatar = new javax.swing.JPanel();
  isWritingLabel1 = new jfbchat.labels.IsWritingLabel();
  statusLabel1 = new jfbchat.labels.StatusLabel();
  messageField = new javax.swing.JTextField();

  setPreferredSize(new java.awt.Dimension(470, 410));

  jPanelScrollMessages.setMinimumSize(new java.awt.Dimension(50, 50));
  jPanelScrollMessages.setLayout(new java.awt.BorderLayout());

  PanelMessages.setBackground(new java.awt.Color(255, 255, 255));
  PanelMessages.setAlignmentY(0.0F);
  PanelMessages.setLayout(new javax.swing.BoxLayout(PanelMessages, javax.swing.BoxLayout.Y_AXIS));
  ScrollMessages.setViewportView(PanelMessages);

  jPanelScrollMessages.add(ScrollMessages, java.awt.BorderLayout.CENTER);

  javax.swing.GroupLayout jPanelUnderAvatarLayout = new javax.swing.GroupLayout(jPanelUnderAvatar);
  jPanelUnderAvatar.setLayout(jPanelUnderAvatarLayout);
  jPanelUnderAvatarLayout.setHorizontalGroup(
    jPanelUnderAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jPanelUnderAvatarLayout.createSequentialGroup()
    .addComponent(isWritingLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(30, 30, 30)
    .addComponent(statusLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addContainerGap(419, Short.MAX_VALUE))
);
  jPanelUnderAvatarLayout.setVerticalGroup(
    jPanelUnderAvatarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(statusLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addComponent(isWritingLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
);

  messageField.addFocusListener(new java.awt.event.FocusAdapter() {
    public void focusLost(java.awt.event.FocusEvent evt) {
    messageFieldFocusLost(evt);
    }
  });
  messageField.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyTyped(java.awt.event.KeyEvent evt) {
    messageFieldKeyTyped(evt);
    }
  });

  javax.swing.GroupLayout MainFrameLayout = new javax.swing.GroupLayout(MainFrame);
  MainFrame.setLayout(MainFrameLayout);
  MainFrameLayout.setHorizontalGroup(
    MainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(jPanelScrollMessages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(jPanelUnderAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(messageField, javax.swing.GroupLayout.Alignment.TRAILING)
);
  MainFrameLayout.setVerticalGroup(
    MainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(MainFrameLayout.createSequentialGroup()
    .addComponent(jPanelScrollMessages, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jPanelUnderAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
);

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
  this.setLayout(layout);
  layout.setHorizontalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGap(0, 470, Short.MAX_VALUE)
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
      .addGap(0, 0, 0)
      .addComponent(MainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGap(0, 0, 0)))
);
  layout.setVerticalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGap(0, 410, Short.MAX_VALUE)
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
      .addGap(0, 0, 0)
      .addComponent(MainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGap(0, 0, 0)))
);
  }// </editor-fold>// GEN-END:initComponents

  private void messageFieldFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_messageFieldFocusLost
  // Change the ChatState informing the server the user not composing anymore
  try {
    this.chatStateManager.setCurrentState(ChatState.paused, relatedChat);

  } catch (XMPPException e) {
    new DebugMessage(this.getClass(), "Cannot change chatState to paused.", e);

  }
  }// GEN-LAST:event_messageFieldFocusLost

  private void messageFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_messageFieldKeyTyped
  // Change the ChatState informing the server the user is composing
  try {
    if (prefs.getPreferences().getBoolean(Options.SEND_TYPING_MESSAGE, true)) {
    this.chatStateManager.setCurrentState(ChatState.composing, relatedChat);

    }

  } catch (XMPPException e) {
    new DebugMessage(this.getClass(), "Cannot change chatState to composing.", e);

  }

  if ((int) evt.getKeyChar() == K_ENTER_ID) {

    SendMessage(messageField.getText());

  }
  }// GEN-LAST:event_messageFieldKeyTyped
  public jfbchat.labels.StatusLabel getStatusLabel() {
  return this.statusLabel1;

  }
  // Variables declaration - do not modify// GEN-BEGIN:variables
  private javax.swing.JPanel MainFrame;
  private javax.swing.JPanel PanelMessages;
  private javax.swing.JScrollPane ScrollMessages;
  private jfbchat.labels.IsWritingLabel isWritingLabel1;
  private javax.swing.JPanel jPanelScrollMessages;
  private javax.swing.JPanel jPanelUnderAvatar;
  private javax.swing.JTextField messageField;
  private jfbchat.labels.StatusLabel statusLabel1;
  // End of variables declaration// GEN-END:variables
}
