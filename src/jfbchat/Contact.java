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

import jfbchat.panels.PanelContact;
import jfbchat.frames.ChatFrame;
import jfbchat.debug.DebugMessage;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;


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
    private ArrayList<PanelContact> contactPanels;

    //ChatFrame associated with the contact
    private ChatFrame chatFrame;

    private String jID;
    private MyVCard vCard;

    public Contact(Connection connection, RosterEntry entry, Presence presence){
        
        this.connection = connection;

        this.entry = entry;
        this.chatActive = false;
        this.name =  entry.getName();
        this.presence = presence;
        this.contactPanels = new ArrayList();
        this.chatFrame = null;
        this.groups = new ArrayList();
        this.vCard = new MyVCard(connection, this);
        this.id = ++contactId;
        this.jID =  entry.getUser();
        init_groups();

    }

    /**
     * initialize the groups of the contact
     */
    private void init_groups(){
        int i = 0;


        if (!(entry.getGroups().isEmpty())){


            try{
                for (Iterator<RosterGroup> iter = entry.getGroups().iterator(); iter.hasNext();){
                    RosterGroup nextGroup = iter.next();

                        this.groups.add(nextGroup.getName());
                        this.contactPanels.add(new PanelContact(connection, this, nextGroup.getName()));
                 }
            }
            catch(Exception e){

                new DebugMessage("Contact.init_groups: can't initialize groups :" + e.getMessage());

            }


        }else{

            this.contactPanels.add(new PanelContact(connection, this, "Other Friends"));
            new DebugMessage( " Contact.init_groups: no groups --" + entry.getName());

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

    public ArrayList<String> getGroups(){
        return this.groups;

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
     * Update the contactPanels associated to the contact, normally called after
     * some contact changes.
     */
    
    public void updateContactPanels(){

        try{
            if ( !(this.contactPanels.isEmpty()) ){

                for(Iterator<PanelContact> iterContactPanel = this.contactPanels.iterator(); iterContactPanel.hasNext();){
                    PanelContact nextContactPanel = iterContactPanel.next();

                    nextContactPanel.update(this);

                }
            }
        }catch(Exception e){

           new DebugMessage("contact.updateContactPanel() exception: " + e.getMessage());

        }
            
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
     * @return a PanelContact
     */

    /**
     * Add a contactPanel to the contactPanels ArrayList
     * @param A contactPanel
     */
    public void addContactPanel(PanelContact contactPanel){

        this.contactPanels.add(contactPanel);

    }


    public ArrayList<PanelContact> getContactPanel(){
        
        return contactPanels;

    }

    public PanelContact getContactPanelbyGroup(String groupName){

         try{
            if ( !(this.contactPanels.isEmpty()) ){

                for(Iterator<PanelContact> iterContactPanel = this.contactPanels.iterator(); iterContactPanel.hasNext();){
                    PanelContact nextContactPanel = iterContactPanel.next();

                    if (nextContactPanel.getGroupName().equals(groupName)){

                        return nextContactPanel;
                    }
                }

            }
        }catch(Exception e){

           new DebugMessage("contact.updateContactPanel() exception: " + e.getMessage());

        }

        return null;

    }

    /**
     * Adds the ChatFrame associated to this contact to the ChatManager
     */

    public void addToChatManager(){
       
            this.chatFrame =  new ChatFrame(connection, this);
            connection.getChatManager().add(chatFrame);
       
    }

    
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

    /**
     * Remove a contact from groups
     * @param A group
     */

    public void removeFromGroup(String group){
        
       
        if(this.hasGroup()){

            for (int i = 0; i < groups.size(); i++){                

                if (groups.get(i).equals(group) ){

                   groups.remove(i);

                }               
            }
        }
    }

    /**
     *
     * @return true if the contact is in a group false if not
     */

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

    public String getJID(){
        return this.jID;
    }
    public MyVCard getVCard(){
        return vCard;

    }
    
    


    @Override
    public String toString(){
        return "Contact name: "+ name + " presence: " + presence.toString();


    }




}
