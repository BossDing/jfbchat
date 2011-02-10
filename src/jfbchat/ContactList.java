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


public class ContactList {
     private final int MAX_CONTACTS = 2048;

    private Contact[] contactList;
    private int lastcontact;
    private Connection connection;
    private MyChatManager chatManager;

    public ContactList(){
        contactList = new Contact[MAX_CONTACTS];
        init();

    }

    
    public ContactList(Connection connection){
        this.connection = connection;
        this.chatManager = new MyChatManager();
        getList();
    }

    private void init(){
        for (int i = 0; i < contactList.length; i++){
            contactList[i] = null;
        }
    }
    
    private void getList(){
        
            int i = 0;
            Roster roster = connection.getConnection().getRoster();
            Collection<RosterEntry> entries = roster.getEntries();
            contactList = new Contact[entries.size()];

            for (RosterEntry entry : entries) {
                contactList[i] = new Contact(entry,
                                            roster.getPresence(entry.getUser()));
                System.out.println(entry);

                i++;
            }
        
        }

    

    public Contact getContact(int index){

        return contactList[index];

    }

    public MyChatManager getChatManager(){
        return chatManager;
    }

    public int getSize(){

        return contactList.length;

    }

    public int getID(int index){
        return contactList[index].getID();
    }

    public int getID(String adr){
        /*Return the ID of the relative address*/
        
        for (int i = 0; i < contactList.length ; i++){
           if (contactList[i].getAdress().equals(adr)){
               return contactList[i].getID();
           }
        }
        
        return -1;



    }

}
