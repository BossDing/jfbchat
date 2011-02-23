/*###################################################
 

    JFBChat it's a simple software written in Java that let you conncted with 
    yours Facebook friends without your browser.
    Copyright (C) 2011  Digitex (Giuseppe Federico)This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. To change this template, choose Tools | Templates
 

    JFBChat it's a simple software written in Java that let you conncted with 
    yours Facebook friends without your browser.
    Copyright (C) 2011  Digitex (Giuseppe Federico)This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. and open the template in the editor.
 ###################################################
*/

package jfbchat;

import jfbchat.frames.ChatFrame;
import java.util.ArrayList;
import jfbchat.debug.DebugMessage;

public class MyChatManager {
    /* This class represent an array with all the active chats */

    private ArrayList<ChatFrame> chatManager;
    
    
    public MyChatManager(){
        chatManager = new ArrayList();
          }
    

    public boolean isActive(int index){

        if (chatManager.get(index) == null){
            
            return false;
        }
        else{
            
            return true;

        }

    }
    
    public void add(ChatFrame c){
        /* Add a chatframe at the specified index */
        try{
        
            chatManager.add(c);

            new DebugMessage("Add a ChatFrame at[" + chatManager.size()+"]");
        }
        catch(Exception e){
            System.err.print(e.getMessage());    

        }

        
  }

    public int getSize(){
        return chatManager.size();
    }

    public ChatFrame getChat(int index){
        return chatManager.get(index);
    }



}
