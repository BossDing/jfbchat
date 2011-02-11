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
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.*;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import javax.swing.JOptionPane;

public class PacketListening{

    private Connection connection;
    private PacketListener myListener;
    private PacketCollector myCollector;
    private PacketFilter filter;

    public PacketListening(Connection connection){

        this.connection = connection;
        // Create a packet filter to listen for new messages from a particular
        // user. We use an AndFilter to combine two other filters.
        filter = new PacketTypeFilter(Message.class);




        // First, register a packet collector using the filter we created.
        try{
        myCollector = connection.getConnection().createPacketCollector(filter);
        }
        catch (Exception ex) {                                            //TODO: gestire meglio le eccezzioni
          
            JOptionPane.showMessageDialog(null, "Cannot Listen to Messages.");



        }
        // Normally, you'd do something with the collector, like wait for new packets.

        // Next, create a packet listener. We use an anonymous inner class for brevity.
        myListener = new MyPacketListener(connection);
        // Register the listener.
        connection.getConnection().addPacketListener(myListener, filter);

    }

}
