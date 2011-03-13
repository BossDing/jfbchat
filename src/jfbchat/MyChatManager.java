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
import java.util.Iterator;

import jfbchat.frames.ChatFrame;
import jfbchat.debug.DebugMessage;



/**
 * A ChatManager is a list with all the active conversations
 * @author @author Digitex(digitex3d@gmail.com Giuseppe Federico)
 */
public class MyChatManager {

    private ArrayList<ChatFrame> chatManager;
    
    public MyChatManager(){

        chatManager = new ArrayList();

        }

    /**
     * Add a ChatFrame to this chatManager
     * @param A ChatFrame
     */
    public void add(ChatFrame c){
        /* Add a chatframe at the specified index */

        try{
        
            chatManager.add(c);
            new DebugMessage("Add a ChatFrame at[" + chatManager.size()+"]");

        }
        catch(Exception e){

            new DebugMessage("Cannot add a ChatFrame to the ChatManager: "
                              + e.getMessage()); 

        }
    }

    public int getSize(){
        return chatManager.size();
    }

    public ChatFrame getChat(int index){
        return chatManager.get(index);
    }

    /**
     * This method should be called when the user disconnets
     * clear the chatManager array
     */
    public void clear(){


        //Hide all the opened windows
        for(Iterator<ChatFrame> iter = this.chatManager.iterator(); iter.hasNext();){

            ChatFrame nextChatFrame = iter.next();

            nextChatFrame.setVisible(false);

        }

        //clear the ArrayList
        this.chatManager.clear();
        
    }

}
