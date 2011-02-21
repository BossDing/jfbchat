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


package jfbchat;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class MyPacketListener implements PacketListener{

      private Connection connection;


      public MyPacketListener(Connection connection){
          this.connection = connection;
      
      }

      public void processPacket(Packet packet) {
            MyChatManager chatManager = connection.getChatManager();

            //Relative id of the contact
            int fromContactId = connection.getContactList().getID(packet.getFrom());

            //Contact who sends the packet
            Contact contact = connection.getContactList().getContact(fromContactId);

            
            //this is the contact id relative to the contact that send the message

            Message msg = (Message) packet;

            System.out.println("\"" + msg.getBody()+ "\"" + " recived from " + contact.getUser());
                                      
           

                if (contact.isActive()){
                    if (!(chatManager.getChatFromID(fromContactId).isVisible())){

                        chatManager.getChatFromID(fromContactId).setVisible(true);
                        
                    }
                    

                    //if the chat is present in the chatmanager then show it
                    System.out.print("The chat is already present in "
                                        + "the chat manager with id[");
                    System.out.println(contact.getID() + "]");
                }
                
                else{

                    //Create a new Chatframe and show it
                    contact.setActive(true);
                    chatManager.add(new ChatFrame(connection, contact));
                    
                }
           
        }
}
