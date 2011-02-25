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

import jfbchat.frames.ChatFrame;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.RosterEntry;

/**
 * Represents a contact
 * @author Digitex(digitex3d@gmail.com Giuseppe Federico)
 */

public class Contact {

    private Connection connection;

    private String name;
    private RosterEntry entry;
    private Presence presence;
    private boolean chatActive;

    //Panel associated with the contact
    private ContactPanel contactPanel;

    //ChatFrame associated with the contact
    private ChatFrame chatFrame;

    public Contact(Connection connection, RosterEntry entry, Presence presence){
        
        this.connection = connection;

        this.entry = entry;
        this.chatActive = false;
        this.name =  entry.getName();
        this.presence = presence;
        this.contactPanel = new ContactPanel(connection, this);
        this.chatFrame = null;

    }

    /**
     * Returns the name of the contact displayed in the contact list
     * @return the name of the contact
     */
    public String getUser(){
        /* returns the name of the contact displayed in the contact list*/
        return this.name;

    }

    /**
     * Get the ChatFrame associated to this contact
     * @return the ChatFrame associated to the contact
     */
    public ChatFrame getChatFrame(){
        return chatFrame;
    }

    /**
     * Returns the presence associated to this contact
     * @return the presence of the contact
     */
    public Presence getPresence(){
        return this.presence;    }

    /**
     * Sets the presence of this contact
     *
     */
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

    /**
     * Get the Adress of the contact
     * @return
     */

    public String getAdress(){
        return entry.getUser();
    }

    /**
     * Returns true if the contact is available and false if not
     * @return a boolean
     */
    public boolean isAvailable(){
        return presence.isAvailable();
    }

    /**
     * Returns true if a chat is active with this contact
     * @return a boolean
     */
    public boolean isActive(){
        return chatActive;
    }

    /**
     * Set it true if a chat with this contact is active and false if not
     *
     */
    public void setActive(boolean active){
        chatActive = active;
    }

    /**
     * Returns the contact panel associated to this contact
     * @return a ContactPanel
     */
    public ContactPanel getContactPanel(){
        return contactPanel;
    }

    /**
     * Adds the ChatFrame associated to this contact to the ChatManager
     */

    public void addToChatManager(){

        this.chatFrame =  new ChatFrame(connection, this);
        connection.getChatManager().add(chatFrame);

    }

    /* 0.2.0
     public String getGroups(){
        return this.presence.toString();    
     }*/

    
    


    @Override
    public String toString(){
        return "Contact name: "+ name + " presence: " + presence.toString();


    }


}
