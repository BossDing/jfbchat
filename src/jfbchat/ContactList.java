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

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jfbchat.debug.DebugMessage;

/**
 * This class represent a contact list.
 * @author Digitex Giuseppe Federico( digitex3d@gmail.com)
 */
public class ContactList {

    protected Connection connection;

    protected ArrayList<Contact> contactList;
    protected Roster roster;
    protected ArrayList<Group> groups;

    public ContactList(Connection connection){
        this.connection = connection;
        this.contactList = new ArrayList();
        this.groups = new ArrayList();

    }

    /**
    * Get the contact list from the server, populate the contactList and sort it
    * by name.
    */
    public void getList(){
        try{

            this.roster = connection.getConnection().getRoster();

            Collection<RosterEntry> entries = roster.getEntries();

            for (Iterator<RosterEntry> iter = entries.iterator(); iter.hasNext();){

                RosterEntry nextEntry = iter.next();

                contactList.add(new Contact(this.connection
                                             ,nextEntry
                                             ,roster.getPresence(
                                                        nextEntry.getUser())));

                
            
            }
           
        
        }

        catch (Exception e){

            new DebugMessage("Cannot get the list: " + e.getMessage());

        }

    }

    /**
    * Gets the contact associated at position index
    * @param index - the position of the contact in the ArrayList
    * @return a contact
    */
    public Contact getContact(int index){
        return contactList.get(index);
        
    }

    /**
    * Gets an address and returns the associated contact from the list
    * @param A contact address
    * @return The contact address associated to the address
    */
    public Contact getContact(String addr){

        if(!(contactList.isEmpty())){        
            Iterator<Contact> iter = contactList.iterator();

            while(iter.hasNext()){
         
                Contact nextContact = iter.next();
      
                if (nextContact.getAdress().equals(addr)){
                    return nextContact;

               }
            }
        }

        new DebugMessage("ContactList.getContact : Cannot find contact " + addr + " in the contact list.");

        return null;

    }

    /**
    * Gets a contact name and returns the associated contact from the list
    * @param A contact name
    * @return The contact in the list associated to the name
    */
    public Contact getContactFromName(String name){
        if(!(contactList.isEmpty())){
            for (Iterator<Contact> iter = contactList.iterator() ; iter.hasNext();){
                Contact next = iter.next();
                if (next.getUser().equals(name)){
                   return next;

               }
            }
        }

        new DebugMessage("ContactList.getContactFromName : Cannot find name " + name + " in the contact list.");
        return null;

    }
               
    /**
    *
    * @param A contact id
    * @return  The contact in the list associated with the the id
    */
    public Contact getContactFromId(int id){
        if(!(contactList.isEmpty())){
            for (Iterator<Contact> iter = contactList.iterator() ; iter.hasNext();){
                Contact next = iter.next();
                if (next.getId() == id ){
                   return next;
               }
            }
        }

        new DebugMessage("ContactList.getContactFromId : Cannot find id " + id + " in the contact list.");
        return null;

    }

    /**
    *
    * @return the iterator associated to this contact list
    */
    public Iterator iterator(){
        return contactList.iterator();
    }

    /**
     * 
     * 
     * @return Ths size of this contact list
     */
     public int getSize(){
        return contactList.size();
    }


    /**
    * Sorts this contactlist by name
    */
    public void sortByName(){
        //TODO: alternative to this function
        /**
         * In nameList we are going to stock the names
         */

        ArrayList<String> nameList = new ArrayList();

        //Local Arraylist that will contain the sorted contact list
        ArrayList<Contact> newList = new ArrayList();
        
        for (Iterator<Contact> iter =  contactList.iterator(); iter.hasNext();){
            Contact next = iter.next();
            nameList.add(next.getUser());
        }

        //Sort the list of names by name
        Collections.sort(nameList);

        //Populate the newList sorted by name
        for(Iterator<String> iter = nameList.iterator(); iter.hasNext();){
            String next = iter.next();
            
            if (getContactFromName(next) != null){
                 newList.add(getContactFromName(next));
            }
        }

        //Clear the list
        contactList.clear();

        //Copy newList to the instance variable
        contactList = newList;
    }

    /**
     *
     * @return the roster associated to this connection
     */
    public Roster getRoster(){
        return roster;
    }

    /**
     * This method gets all the groups in the contact list
     * Should be called after getList
     */

    public ArrayList<Group> getGroups(){

        if (groups != null){
            return groups;
        }
        else{
            new DebugMessage("ContactList.getGroups: Cannot get groups: The roster is not defined, maybe call getList first or no groups?");
            return null;
        }
    }

    public ArrayList<Contact> getContactList(){
        return contactList;
    }


    /**
    * This method defines the groups of a contactList
    * For each group in the roster defines a new Group filled of all contacts
    * in groups
    *Should be called after getList
    */
    public void updateGroupPanels(){
        if( !(groups.isEmpty())){

              for(Iterator<Group> iter = groups.iterator(); iter.hasNext();){
                    Group nextGroup = iter.next();

                        //If the contact has no group add to the temporaney group list


                            nextGroup.getPanel().updatePanel();


                }

        }
    }

    public void defineGroups(){

        //A temporaney arraylist for the groups
        ArrayList<Contact> temp_g = new ArrayList();
        //A temporaney arraylist for the contacts
        List<Contact> temp_c = ((List) ((ArrayList) contactList).clone());


        if ( !(roster.getGroups().isEmpty())){

            //Read all the groups
            for(Iterator<RosterGroup> iter = roster.getGroups().iterator(); iter.hasNext();){
                RosterGroup nextGroup = iter.next();

                temp_g.clear();

                //Read the contactList
                for(Iterator<Contact> iterContact = temp_c.iterator(); iterContact.hasNext();){
                    Contact nextContact = iterContact.next();
                    
                    //If the contact is in the group
                    if ( nextContact.isInGroup(nextGroup.getName())){

                        //Add to the temporaney contact list
                        temp_g.add(nextContact);

                        //Remove the contact from the temporaney group
                        nextContact.removeFromGroup(nextGroup.getName());

                        //Remove the contact has no groups
                        if (!(nextContact.hasGroup())){

                            //Remove it from the temporaney contact list
                            temp_c.remove(nextContact);

                            //Update the iterator
                            iterContact = temp_c.iterator();

                        }
                    }
                 
                }

            //Add the temporaney group to a new group
            this.groups.add(new Group(connection, nextGroup.getName(), temp_g));

            }
            if( ! (temp_c.isEmpty())){
                //Add the temporaney group to a new group
                this.groups.add(new Group(connection, "Other Friends", (ArrayList) temp_c));
            }

        }else{

            new DebugMessage("Cannot define groups: The roster is not defined, maybe call getList first or no groups...?");

        }

    }

    /**
     * Get a group from the groupList by giving the name of this group.
     * @param the name of a group
     * @return the group associated with that name
     */
    public Group getGroupFromName(String name){

        for (Iterator<Group> iter = groups.iterator() ; iter.hasNext();){
            Group next = iter.next();
            if (next.getName().equals(name)){

               new DebugMessage(this.getClass(), "Found " + name + " in the group list.");
               return next;
           }
        }

        new DebugMessage(this.getClass(), "Cannot find group " + name + " in the group list.");
        return null;


    }


    /**
     *
     * @return True if the contact is in the contactlist and false if not.     */

    public boolean isInList(int id){

        if(getContactFromId(id) != null ){
                return true;

        }else{
                return false;
        }

    }
    /**
     * This method should be called when the user disconnects.
     * Clear all the instance variables
     */
    public void clear(){

        this.contactList.clear();
        this.groups.clear();


    }

    @Override
    public String toString(){

        return contactList.toString();

    }

}
