 /* ###########################################################################
  *
  *  JFBChat it's a simple software written in Java that let you in contact
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

package jfbchat.listeners;

import jfbchat.Connection;
import jfbchat.Contact;
import jfbchat.ContactList;
import jfbchat.MyChatManager;
import jfbchat.debug.DebugMessage;
import jfbchat.panels.PanelMessage;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class MyPacketListener implements PacketListener{

  private final int TIMEOUT = 1000;
  private Connection connection;
  private MyChatManager chatManager;
  private ContactList contactList;

  public MyPacketListener(Connection connection) {
    this.connection = connection;
    this.chatManager = connection.getChatManager();
    this.contactList = connection.getContactList();
  }

  // the packet is filtered as a message
  public void processPacket(Packet packet) {
    // Contact who sends thet packet
    Contact contact = connection.getContactList().getContact(packet.getFrom());

    // Managing a message
    Message msg = (Message) packet;

    new DebugMessage(this.getClass(), "processPacket: \"" + msg + "\""
    + " received from "  + contact.getUser());

    // If the contact has a conversation active in the chatManager
    if (!(contact.isActive())) {
    // Create a new PanelChat and show it
    contact.initChat();
    // Add the message in the PanelMessage
    contact.getPanelChat().
      addPanelMessage(new PanelMessage(
              false,
              contact,
              msg.getBody()));

    }

    // Focus the contact tab
    connection
      .getChatFrame()
      .getjTabbedPaneChats()
      .setSelectedIndex(contact
            .getPanelChat()
            .getTabIndex());
    // Show the ChatFrame
    connection.getChatFrame().setVisible(true);

    // do nothing for TIMEOUT miliseconds
    try
    {
    Thread.sleep(TIMEOUT);
    }
    catch (Exception e)
    {
    new DebugMessage(this.getClass(), "Cannot timeout for " + TIMEOUT + "miliseconds");
    }

    new DebugMessage("processPacket:Adding a new panel to "
      + "the chatframe :" + msg.getBody());
  }

  }

