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
import org.jivesoftware.smack.RosterEntry;

public class Contact {
    private static int contactID = 0;

    private int id;
    private String name;
    private Connection connection;
    private RosterEntry entry;
    private Presence presence;
    private boolean chatActive;
    //Panel associated with the contact in Swing.
    private ContactPanel contactPanel;
    private ChatFrame chatFrame;

    public Contact(Connection connection, RosterEntry entry, Presence presence){
        this.entry = entry;
        this.connection = connection;
        this.chatActive = false;
        this.name =  entry.getName();
        this.presence = presence;
        this.id = contactID++;
        this.contactPanel = new ContactPanel(connection, this);
        this.chatFrame = null;

        }

    public String getUser(){
        /* returns the name of the contact displayed in the contact list*/
        return this.name;

    }

    public ChatFrame getChatFrame(){
        return chatFrame;
    }
    
    public Presence getPresence(){
        return this.presence;    }

    public void setPresence(Presence p){
        this.presence = p;
    }

    /**
     * Update the panel associated to the contact, normally called after
     * some contact changes.
     */
    
    public void updateContactPanel(){
        
        this.contactPanel.update(this);
    }

    public String getAdress(){
        return entry.getUser();   }

    public boolean isAvailable(){
        return presence.isAvailable();
    }

    public boolean isActive(){
        return chatActive;
    }
    public void setActive(boolean active){
        chatActive = active;
    }
    
    public ContactPanel getContactPanel(){
        return contactPanel;
    }

    public void addToChatManager(){

        this.chatFrame =  new ChatFrame(connection, this);
        connection.getChatManager().add(chatFrame);

    }



    public String getGroups(){
        return this.presence.toString();    }

    public int getID(){
        return id;
    }

    @Override
    public String toString(){
        return "Contact " + id+ " : "+ " name:"+ name + " presence: " + presence.toString();


    }


}
