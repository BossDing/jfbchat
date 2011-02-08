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
    private String name;
    private RosterEntry entry;
    private Presence presence;



    

    public Contact(RosterEntry entry, Presence presence){
        this.entry = entry;
        this.name =  entry.getName();
        this.presence = presence;


    }

    public String getUser(){
        return this.name;    }

    public Presence getPresence(){
        return this.presence;    }

    public String getAdress(){
        return entry.getUser();   }

    public boolean isAvailable(){
        return presence.isAvailable();
    }

    public String getGroups(){
        return this.presence.toString();    }



}
