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
import org.jivesoftware.smack.RosterGroup;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Represents a contact
 * @author Digitex(digitex3d@gmail.com Giuseppe Federico)
 */

public class Contact {
    private static int contactId = 0;

    private Connection connection;

    private String name;
    private RosterEntry entry;
    private Presence presence;
    private boolean chatActive;
    private int id;
    private ArrayList<String> groups;
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
        this.groups = new ArrayList();
        init_groups();

    }

    /**
     * initialize the groups of the contact
     */
    private void init_groups(){
        int i = 0;

        if (!(entry.getGroups().isEmpty())){

            for (Iterator<RosterGroup> iter = entry.getGroups().iterator(); iter.hasNext();){
                RosterGroup nextGroup = iter.next();

                    this.groups.add(nextGroup.getName());


             }
        }
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
        //TODO: caccia new
        return new ContactPanel(connection, this);
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

    
    /**
     *
     * @param A group
     * @return True if the contact is in the group, false if not
     */
    public boolean isInGroup(String group){
        if(hasGroup()){
            for (Iterator<String> iter = groups.iterator() ; iter.hasNext();){
                String nextGroup = iter.next();
                if (nextGroup.equals(group)){
                    return true;
                }
            }

            return false;
        }
        else{
            return false;
        }

    }

    public void removeFromGroup(String group){
        
       
        if(this.hasGroup()){
            for (int i = 0; i < groups.size(); i++){
                

                if (groups.get(i).equals(group) ){
                   groups.remove(i);
                }
                
            }
        }
    }

    public boolean hasGroup(){

        if ( this.groups.isEmpty() ){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * @return The id associated to the contact
     */
    public int getId(){
        return this.id;
    }

    
    


    @Override
    public String toString(){
        return "Contact name: "+ name + " presence: " + presence.toString();


    }


}
