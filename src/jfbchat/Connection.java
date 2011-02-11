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

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;


import javax.swing.JOptionPane;


public class Connection {
    private final int PORT = 5222;
    private final String SERVER = "chat.facebook.com";
    private final String SERVICE_NAME = "chat.facebook.com";

    
    private User user;
    private Presence presence;
    private XMPPConnection connection;
    private ConnectionConfiguration connConfig;
    private ContactList contactList;
    private MyChatManager myChatManager;


    public Connection(User user){
              
        this.user = user;
        this.myChatManager = new MyChatManager();
        this.contactList = new ContactList();

        connConfig = new FBConnectionConfiguration(SERVER, PORT,SERVICE_NAME);
 
        connection = new XMPPConnection(connConfig);

      }

    public void connect(){

        /*connect to the server and return true if the connection is etabilished
         * end false if it's impossible to connect.         */

        System.out.println("Starting IM client");


        try {
            connection.connect();
            System.out.println("Connected to " + connection.getHost());
        } catch (XMPPException ex){

            JOptionPane.showMessageDialog(null, "Failed to connect to " + connection.getHost());

        }

        System.out.println("Logged in as " + connection.getUser());


        try {

            connection.login(user.getUsername(), user.getPassword());

         } catch (XMPPException ex) {
            
            JOptionPane.showMessageDialog(null, "Failed to log in as " + user.getUsername());
            this.closeConnection();
            
        }

        //Set the user status presence available.
        presence = new Presence(Presence.Type.available);
        connection.sendPacket(presence);

    }

    public XMPPConnection getConnection(){
            return connection;
    }

    public Presence getPresence(){
        return presence;
    }

    public void closeConnection(){
        connection.disconnect();
    }

    public MyChatManager getChatManager(){
        return myChatManager;
    }

    public ContactList getContactList(){
        return contactList;
    }

    public void setContactList(ContactList list){
        contactList = list;
    }
    
    public boolean isConnected(){
        return connection.isConnected();
    }

    @Override
    public String toString(){
        String msg = "";

        if (isConnected()){
            msg += "Connected to " + SERVER + " at port " + PORT + " Status is "
                    + presence.getStatus() + ".";
        }
        else{
            msg += "Not connected.";
        }

        return msg;
    }



}

    


