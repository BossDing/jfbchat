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

import java.io.*;

import jfbchat.resources.*;
import jfbchat.debug.DMessage;


/**
 * A User
 * @author Digitex ( Giuseppe Federico - digitex3d@gmail.com )
 */

public class User {

    
    private String username;
    private String password;
    private MyVCard vCard;
    private ChatPreferences prefs;

    public User(String username, String password){


        this.username = username;
        this.password = password;
      
        this.vCard = new MyVCard();
        this.prefs = new ChatPreferences();

    }

    public User(){

        this.username = null;
        this.password = null;
        this.prefs = new ChatPreferences();
    }

    public String getNickName(){
        return vCard.getNickName();
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }


    public void saveUserAndPass(){

        /* Write username and password to  preferences for autologin*/
        this.prefs.getPreferences().put( Options.USERNAME, username);
        this.prefs.getPreferences().put( Options.PASSWORD, password);


    }
    

    public void setAutologin(boolean value){

        this.prefs.getPreferences().putBoolean( Options.AUTOLOGIN, value);
     
    }

    public boolean isAutoLogin(){

        return this.prefs.getPreferences().getBoolean( Options.AUTOLOGIN, false);
      
  }

    

    public String getSavedPass(){

        return this.prefs.getPreferences().get( Options.PASSWORD, "");

      
  }

    public String getSavedUser(){
        return this.prefs.getPreferences().get( Options.USERNAME, "");
       
  }

    @Override
    public String toString(){
        return "Username: " + username;
         
    }


}




