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
import org.jivesoftware.smack.SASLAuthentication;

import javax.swing.JOptionPane;
/**
 *
 * @author peppe
 */
public class Connection {
    private final int PORT = 5222;
    private final String SERVER = "chat.facebook.com";
    private final String SERVICE_NAME = "chat.facebook.com";

    private MyChatManager myChatManager;
    private User user;
    private String server;
    private Presence presence;
    private XMPPConnection connection;
    private ConnectionConfiguration connConfig;
    private ContactList contactList;
   
    

//TODO: Completare la classe.
    
    public Connection(User user){
        
        


        this.user = user;
        this.myChatManager = new MyChatManager();
        this.contactList = new ContactList();

        SASLAuthentication.registerSASLMechanism("DIGEST-MD5", MySASLDigestMD5Mechanism.class);

        connConfig = new FBConnectionConfiguration(SERVER, PORT,SERVICE_NAME);
        connConfig.setSASLAuthenticationEnabled(true);
        connConfig.setRosterLoadedAtLogin (true);
       
        
        connection = new XMPPConnection(connConfig);
        
        


      
            

      }

    

    public boolean connect(){

        System.out.println("Starting IM client");



        try {
            connection.connect();
            System.out.println("Connected to " + connection.getHost());
        } catch (XMPPException ex) {

            JOptionPane.showMessageDialog(null, "Failed to connect to " + connection.getHost());

        }
        try {
            //SASLAuthentication.supportSASLMechanism("PLAIN", 0);

            connection.login(user.getUsername(), user.getPassword());
            System.out.println("Logged in as " + connection.getUser());

            presence = new Presence(Presence.Type.available);
            connection.sendPacket(presence);
            return true;

        } catch (XMPPException ex) {                                            //TODO: gestire meglio le eccezzioni
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to log in as " + user.getUsername());
            this.closeConnection();
            return false;

        }
    }

    public XMPPConnection getConnection(){
            return connection;
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



}

    


