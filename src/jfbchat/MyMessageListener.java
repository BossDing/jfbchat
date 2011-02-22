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

import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import javax.swing.JPanel;
import jfbchat.debug.DebugMessage;

public class MyMessageListener implements  MessageListener{
    //private final String RECEIVEDMSGWAV = "/media/3318a757-b8d0-406b-97db-4ced8ba7ccdf/Progetti/jfbchat/src/jfbchat/receivingmsg.wav";
    
    private Contact contact;
    private JPanel panel;

    public MyMessageListener(Contact contact, JPanel panel){
        this.contact = contact;
        this.panel = panel;
    }

     public void processMessage(Chat chat, Message message) {
            panel.add(new PanelMessage(false, contact , message.getBody()));
            new DebugMessage("Received message by the message listener: " + message.getBody()).println();
        
        if (contact.getChatFrame().isVisible() == false){

            contact.getChatFrame().setVisible(true);

        }
        
        

        //new AePlayWave(RECEIVEDMSGWAV).start();
    }


}
