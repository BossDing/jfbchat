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
  * along with this program.  If not, see <http:// www.gnu.org/licenses/>.
  *
  *###########################################################################
  *
  */

package jfbchat;

import java.util.ArrayList;
import java.util.Iterator;
import jfbchat.debug.DebugMessage;
import jfbchat.panels.PanelChat;
import jfbchat.panels.PanelContact;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.packet.Presence;

/**
 * Represents a contact
 * @author Digitex(digitex3d@gmail.com Giuseppe Federico)
 */

public class Contact {

  private static int contactId = 0;

  private Connection connection;

  private String name;
  private RosterEntry entry;
  private Presence presence;
  private boolean chatActive;
  private int id;
  // List of groups associated with the contact
  private ArrayList<String> groups;
  // Panel associated with the contact
  private ArrayList<PanelContact> contactPanels;

  // PanelChat associated with the contact
  private PanelChat panelChat;

  private String jID;
  private MyVCard vCard;

  public Contact(Connection connection, RosterEntry entry, Presence presence) {

  this.connection = connection;
  this.name =  entry.getName();
  this.presence = presence;

  this.entry = entry;
  this.chatActive = false;
  this.contactPanels = new ArrayList();
  this.panelChat = null;
  this.groups = new ArrayList();
  this.vCard = new MyVCard(connection, this);
  this.id = ++contactId;
  this.jID =  entry.getUser();

  add_to_groups();

  }

  public boolean equals(Contact c_contact) {

  return this.jID.equals(c_contact.jID);
  }

  /**
  * Add the contact to the group and initialize his panels
  */
  private void add_to_groups() {

  // If the contact is in a group
  if (!(entry.getGroups().isEmpty())) {
    try {
    for (Iterator<RosterGroup> iter = entry.getGroups().iterator(); iter.hasNext();) {
      RosterGroup nextGroup = iter.next();

      this.groups.add(nextGroup.getName());
      this.contactPanels.add(new PanelContact(connection, this, nextGroup.getName()));
    }
    }
    catch (Exception e) {

    new DebugMessage(this.getClass()," Can't add the contact to a group :", e);

    }

  } else {
    // Add the contact to "Other Friends" group.
    try {
    this.contactPanels.add(new PanelContact(connection, this, "Other Friends"));

    }
    catch (Exception e) {

    new DebugMessage(this.getClass()," Can't add the contact to Other Friends :", e);

    }

  }

  }

  /**
  * Returns the name of the contact displayed in the contact list
  * @return the name of the contact
  */
  public String getUser() {
  /* returns the name of the contact displayed in the contact list*/
  return this.name;

  }

  /**
  *
  * @return an ArrayList with all the groups of the contact
  */
  public ArrayList<String> getGroups() {
  return this.groups;

  }

  /**
  * Get the PanelChat associated to this contact
  * @return the ChatP associated to the contact
  */
  public PanelChat getPanelChat() {
  return panelChat;
  }

  /**
  * Returns the presence associated to this contact
  * @return the presence of the contact
  */
  public Presence getPresence() {
  return this.presence;

  }

  /**
  * Get the Adress of the contact
  * @return
  */
  public String getAdress() {
  return this.entry.getUser();

  }

  /**
  *
  * @return The id associated to the contact
  */
  public int getId() {
  return this.id;

  }

  public String getJID() {
  return this.jID;

  }
  public MyVCard getVCard() {
  return vCard;

  }

  /**
  * Sets the presence of this contact
  *
  */
  public void setPresence(Presence p) {
  try {
    this.presence = p;
  }catch (Exception e) {
    new DebugMessage(this.getClass(), "Cannot set Presence " + p.toString() + ".", e);
  }
  }

  /**
  * Update the contactPanels associated to the contact, normally called after
  * some contact changes.
  */
  public void updateContactPanels() {

  try {
    if (!(this.contactPanels.isEmpty())) {

    for (Iterator<PanelContact> iterContactPanel = this.contactPanels.iterator(); iterContactPanel.hasNext();) {
      PanelContact nextContactPanel = iterContactPanel.next();

      nextContactPanel.updateStatus();
      nextContactPanel.updateAvatar();
    }
    }
  }catch (Exception e) {

    new DebugMessage(this.getClass(), " Cannot update the contact panels " + e.getMessage());

  }

  }
  /**
  *
  * Uncollapse all Panels
  *
  */
  public void uncollapseAll() {
  for (Iterator<PanelContact> iterContactPanel
        =  this.contactPanels.iterator();
        iterContactPanel.hasNext();) {

      PanelContact nextContactPanel = iterContactPanel.next();

      nextContactPanel.uncollapse();
    }
  }

  /**
  *
  * Collapse all Panels
  *
  */
  public void collapseAll() {
  for (Iterator<PanelContact> iterContactPanel
        =  this.contactPanels.iterator();
        iterContactPanel.hasNext();) {

      PanelContact nextContactPanel = iterContactPanel.next();

      nextContactPanel.collapse();

    }
  }

  /**
  * Returns true if the contact is available and false if not
  * @return a boolean
  */
  public boolean isAvailable() {
  return presence.isAvailable();
  }

  /**
  * Returns true if a chat is active with this contact
  * @return a boolean
  */
  public boolean isActive() {
  return chatActive;
  }

  /**
  * Set it true if a chat with this contact is active and false if not
  *
  */
  public void setActive(boolean active) {
  chatActive = active;
  }

  /**
  * Add a contactPanel to the contactPanels ArrayList
  * @param A contactPanel
  */
  public void addContactPanel(PanelContact contactPanel) {
  try {
    this.contactPanels.add(contactPanel);

  }catch (Exception e) {
    new DebugMessage(this.getClass(), " Cannot add contact to the contactPanels " + e);

  }
  }

  /**
  *
  * @return A list with all the contactPanels associated with this contact
  */
  public ArrayList<PanelContact> getContactPanels() {
  return contactPanels;
  }

  /**
  *
  * @param groupName
  * @return The PanelContact associated with this name
  */
  public PanelContact getContactPanelbyGroup(String groupName) {

  try {
    if (!(this.contactPanels.isEmpty())) {

    for (Iterator<PanelContact> iterContactPanel = this.contactPanels.iterator(); iterContactPanel.hasNext();) {
      PanelContact nextContactPanel = iterContactPanel.next();

      if (nextContactPanel.getGroupName().equals(groupName)) {

      return nextContactPanel;
      } else {
      new DebugMessage(this.getClass(), "Cannot find the PanelContact "
              + groupName);
      }
    }

    } else {
    new DebugMessage(this.getClass(), "Cannot find the PanelContact "
              + groupName +
              " :There are no contactPanels for this contact");
    }
  }catch (Exception e) {

    new DebugMessage("contact.updateContactPanel() exception: " + e.getMessage());

  }

  new DebugMessage(this.getClass(), "Cannot find the PanelContact: " + groupName);

  return null;

  }

  /**
  * Init the chat with the contact and show the conversation
  *
  */
  public void initChat() {
  // If the chat is active in the chatmanager show it.
  if (!(this.isActive())) {
    try {
    this.setActive(true);
    if (this.panelChat==null) {
      this.panelChat =  new PanelChat(connection, this);
      connection.getChatManager().add(this.panelChat);
      connection.getChatFrame().addTab(connection, this , this.panelChat);

    } else {
      connection.getChatFrame().addTab(connection, this , this.panelChat);

    }
    }catch (Exception e) {
      new DebugMessage(this.getClass(), "Cannot init the chat with " + this.getUser(), e);

    }

  }
  try {
    // Focus the contact tab
    connection
      .getChatFrame()
      .getjTabbedPaneChats()
      .setSelectedIndex(this
            .getPanelChat()
            .getTabIndex());
    // Show the ChatFrame
    connection.getChatFrame().setVisible(true);
  }catch (Exception e) {
    new DebugMessage(this.getClass(), "Cannot show the chat with " + this.getUser(), e);

    }

  }

  /**
  * Remove a contact from groups
  * @param A group to remove
  */
  public void removeFromGroup(String group) {
  boolean removed = false;

  if (this.hasGroup()) {
    for (int i = 0; i < groups.size(); i++) {
    if (groups.get(i).equals(group)) {
      groups.remove(i);
      removed = true;
    }
    }
  }

  if (!removed) {
    new DebugMessage(this.getClass(), "Cannot remove the group " + group + " from the groups");
  }
  }

  /**
  *
  * @param A group
  * @return True if the contact is in the group, false if not
  */
  public boolean isInGroup(String group) {

  // If the contact has a group
  if (hasGroup()) {
    for (Iterator<String> iter = groups.iterator() ; iter.hasNext();) {
    String nextGroup = iter.next();
    if (nextGroup.equals(group)) {
      return true;

    }
    }
    // new DebugMessage(this.getClass(), "The contact is not in the group " + group);
    return false;

  } else {
    // new DebugMessage(this.getClass(), "The contact is not in the group " + group);
    return false;

  }

  }

  /**
  *
  * @return true if the contact is in a group false if not
  */
  public boolean hasGroup() {

  if (this.groups.isEmpty()) {
    return false;
  } else {
    return true;
  }
  }
  /**
  * Update all the avatar of all contactPanels
  */
  public void updateAvatar() {
  if (!(this.contactPanels.isEmpty())) {
    Iterator<PanelContact> iter = this.contactPanels.iterator();

    while (iter.hasNext()) {
    PanelContact nextPanel = iter.next();
    nextPanel.updateAvatar();

    }
  }
  }

  @Override
  public String toString() {
  return "Contact name: "+ name + " presence: " + presence.toString();

  }

}
