/* ###########################################################################

    JFBChat it's a simple software written in Java that let you in contact
    with yours Facebook friends without your browser.
    Copyright (C) 2011  Digitex (Giuseppe Federico)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http:// www.gnu.org/licenses/>.

  ###########################################################################

*/

package jfbchat.listeners;

import jfbchat.Contact;
import jfbchat.debug.DebugMessage;
import jfbchat.panels.PanelMessage;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class MyMessageListener implements  MessageListener {

  private Contact contact;

  public MyMessageListener(Contact contact) {
    this.contact = contact;
  }

  public void processMessage(Chat chat, Message message) {
    // If the message is not null
    if (!(message.getBody() == null)) {
      // Add the message to the PanelChat associated to the contact
      contact.getPanelChat()
      .addPanelMessage(new PanelMessage(false
                                        , contact
                                        , message.getBody()));
    } else {
      // The contact is writing
      contact.getPanelChat()
      .getjIsWritingLabel().setIsWriting(true);
    }

    new DebugMessage(this.getClass(), "Received message by the listener: " + message.getBody());

    if (contact.getPanelChat().isVisible() == false) {
      contact.getPanelChat().setVisible(true);
    }

    // new AePlayWave(RECEIVEDMSGWAV).start();
  }

}
