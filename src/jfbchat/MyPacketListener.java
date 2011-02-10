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
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class MyPacketListener implements PacketListener{

      private Connection connection;

      public MyPacketListener(Connection connection){
          this.connection = connection;
      
      }

      public void processPacket(Packet packet) {
            MyChatManager chatManager = connection.getChatManager();

            int fromContactId = connection.getContactList().getID(packet.getFrom());
            //this is the contact id relative to the contact that send the message

            Message msg = (Message) packet;

           // if (msg.getBody() == null){



                if (chatManager.isActive(fromContactId)){
                    chatManager.getChat(fromContactId).setVisible(true);

                    //if the chat is present in the chatmanager then show it
                    System.out.println("Chat manager already active with thi user" + fromContactId);
                }
                else{

                    chatManager.add(new ChatFrame(connection, connection.getContactList().getContact(fromContactId)));
                    chatManager.getChat(fromContactId).setVisible(true);

                    System.out.println("Reload the last chat");
                    System.out.println(fromContactId);
                    //TODO: make with exceptions here..
                    //TODO: set the focus on the window and maximize it if minimized
                }
           // }

            System.out.println(packet.getFrom());

          //TODO: add a new chat in the chatmanager if it is not present or throw the message to the chatframe
            //new ChatFrame(connection , packet.getFrom());

        }

}
