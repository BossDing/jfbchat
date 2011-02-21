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

import java.util.ArrayList;

public class MyChatManager {
    /* This class represent an array with all the active chats */

    private ArrayList<ChatFrame> chatManager;
    private int lastChat;
    
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
            System.out.print("Add a ChatFrame at[");
            System.out.println( chatManager.size()+"]");
        }
        catch(Exception e){
            System.err.print(e.getMessage());    

        }

        
  }

    public ChatFrame getChatFromID(int id){
        /* Gets an id and returns the ChatFrame with the id associated */
        ChatFrame resu = null;

        for(int i =0 ; i < chatManager.size(); i++){
            if(id == chatManager.get(i).getID()){
                resu = chatManager.get(i);
            }
        }

        return resu;

    }

    public ChatFrame getChat(int index){
        return chatManager.get(index);
    }



}
