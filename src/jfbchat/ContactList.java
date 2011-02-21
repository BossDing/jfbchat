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
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;



public class ContactList {

    private ArrayList<Contact> contactList;
    private Connection connection;
    private MyChatManager chatManager;

    public ContactList(Connection connection){

        this.contactList = new ArrayList();
        this.connection = connection;
    }

    
    public void getList(){
        try{

            Roster roster = connection.getRoster();

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

    public Contact getContact(String addr){
        /* Gets an address and returns a contact from the list*/
        for (int i = 0; i < contactList.size(); i++){
           if (contactList.get(i).getAdress().equals(addr)){
               return contactList.get(i);
           }
        }

        return null;

    }

    public Contact getContactFromName(String name){
        /* Gets a name and returns the associated contact from the list*/
        for (int i = 0; i < contactList.size() ; i++){
           if (contactList.get(i).getUser().equals(name)){
               return contactList.get(i);
           }
        }

        return null;

    }

    public MyChatManager getChatManager(){
        return chatManager;
    }

    public int getID(int index){
        return contactList.get(index).getID();
    }

    public int getID(String adr){
        /*Return the ID of the relative address*/
        
        return getContact(adr).getID();



    }

    public int getSize(){
        return contactList.size();
    }

    public void sortByName(){

        //Sort the contactlist by name
        //TODO: for some reasons the list is not sorted well
        
        String[] nameList = new String[contactList.size()];
        
        for (int i = 0; i < contactList.size(); i++){

            nameList[i] = contactList.get(i).getUser();
           
        }

        Arrays.sort(nameList);


        

        for(int j = 0; j < nameList.length; j++){

            
            
            if (getContactFromName(nameList[j]) != null){
                    contactList.set(j,getContactFromName(nameList[j]));
                    System.out.println(getContactFromName(nameList[j]));
            }

        }
    }

    @Override
    public String toString(){
         String resu = "";

         for(int i = 0; i < contactList.size(); i++){
             resu +=  contactList.get(i).toString();
         }

         return resu;

    }

}
