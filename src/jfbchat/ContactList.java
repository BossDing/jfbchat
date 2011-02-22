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
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * This class represent a contact list.
 * @author Digitex
 */

public class ContactList {

    private ArrayList<Contact> contactList;
    private Connection connection;
    private Roster roster;

    public ContactList(Connection connection){

        this.contactList = new ArrayList();
        this.connection = connection;
      
    }

    /**
     * Get the contact list from the server populate the contactList and sort by
     * name.
     */
    
    public void getList(){
        try{


            roster = connection.getConnection().getRoster();

            Collection<RosterEntry> entries = roster.getEntries();

            for (RosterEntry entry : entries) {
                contactList.add(new Contact(this.connection
                                                ,entry
                                                ,roster.getPresence(entry.getUser())));
            }
            
            sortByName();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Contact getContact(int index){

        return contactList.get(index);
        
    }

    /**
     * Gets an address and returns the associated contact from the list
     * @param a contact address
     * @return a contact from the list
     */
    public Contact getContact(String addr){

        for (Iterator<Contact> iter = contactList.iterator() ; iter.hasNext();){
            Contact next = iter.next();
           if (next.getAdress().equals(addr)){
               return next;
           }
        }

        return null;

    }

    /**
     * Gets a name and returns the associated contact from the list
     * @param A contact name
     * @return The contact in the list associated to the name
     */

    public Contact getContactFromName(String name){
        
        
        for (Iterator<Contact> iter = contactList.iterator() ; iter.hasNext();){
            Contact next = iter.next();
            if (next.getUser().equals(name)){
               return next;
           }
        }

        return null;

    }

    public Iterator iterator(){
        return contactList.iterator();
    }

    /**
     * Get an id from a position in the contactList
     * @param index
     * @return an id
     */
    public int getID(int index){

        return contactList.get(index).getID();

    }

    /**
     * Get an id from an address in the contactList
     * @param a contact address
     * @return an id
     */

    public int getID(String adr){
        /*Return the ID of the relative address*/
        
        return getContact(adr).getID();

    }

    public int getSize(){
        return contactList.size();
    }

    /**
     * Sorts the contactlist by name
     */

    public void sortByName(){

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

    public Roster getRoster(){
        return roster;
    }

    @Override
    public String toString(){

        //TODO: iterator
         String resu = "";

         for(int i = 0; i < contactList.size(); i++){
             resu +=  contactList.get(i).toString();
         }

         return resu;

    }

}
