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
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  *
  *###########################################################################
  *
  */


package jfbchat.listeners;

import jfbchat.Connection;
import jfbchat.Contact;
import jfbchat.ContactList;
import jfbchat.MyChatManager;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import jfbchat.debug.DebugMessage;

public class MyPacketListener implements PacketListener{

      private Connection connection;
      private MyChatManager chatManager;
      private ContactList contactList;


      public MyPacketListener(Connection connection){
          this.connection = connection;
          this.chatManager = connection.getChatManager();
          this.contactList = connection.getContactList();
      }

      public void processPacket(Packet packet) {
   
            //Contact who sends the packet
            Contact contact = contactList.getContact(packet.getFrom());
            
            //Managing a message
            Message msg = (Message) packet;


            new DebugMessage("processPacket: \"" + msg + "\""
                    + " recived from "  + contact.getUser()).println();
                                      
            if (contact.isActive()){
                    if (!(contact.getChatFrame().isVisible())){

                        contact.getChatFrame().setVisible(true);
                        
                    }
            }
 
                else{
                    
                    //Create a new Chatframe and show it
                    contact.setActive(true);
                    contact.addToChatManager();
                    contact.getChatFrame().addMessageToPanel(false, contact, msg);
                    new DebugMessage("processPacket:Adding a new panel to "
                            + "the chatframe :" + msg.getBody()).println();
                }
         
        }

}

