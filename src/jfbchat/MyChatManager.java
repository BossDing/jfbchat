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


public class MyChatManager {
    /* This class represent an array with all the active chats */

    private final int MAX_CHATS = 2048;


    private ChatFrame chatManager[];
    private int lastChat;
    
    public MyChatManager(){
        chatManager = new ChatFrame[MAX_CHATS];
        init();
        
    }
    
    private void init(){
        for (int i = 0; i < chatManager.length; i++){
            chatManager[i] = null;
        }
    }
    
   /* public void add(ChatFrame c){
        chatManager[lastChat++] = c;
        
        
    }*/

    public boolean isActive(int index){

        if (chatManager[index] == null){
            System.out.println("la nun ce");
            return false;
        }
        else{
            System.out.println("la chat ce");
            return true;

        }

    }
    
    public void add(ChatFrame c, int index){
        /* Add a chatframe at the index specified*/
        
        
            chatManager[index] = c;
        
  }

    public ChatFrame getChat(int index){
        return chatManager[index];
    }



}