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
 ###################################################*/

package jfbchat;

import jfbchat.debug.DebugMessage;
import jfbchat.resources.Options;

import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.smack.XMPPException;

import javax.swing.ImageIcon;
/**
 *
 * Author Digitex (Giuseppe Federico digitex3d@gmail.com)
 */
public class MyVCard {
    
    private VCard vCard;

    private ImageIcon avatar;    
    private Contact contact;
    private Connection connection;
    
    public MyVCard(Connection connection, Contact contact){
        
       this.vCard = new VCard();
       this.contact = contact;
       this.connection = connection;
       
       

    }

    public MyVCard(){

       this.vCard = new VCard();

    }
    /**
     * Load the VCard and the avatar of the contact in a temporaney byte array and store it
     * in avatar a ImageIcon
     */
    public void loadAvatar(){
        if(Options.DownloadImages){
            byte[] avatarBytes;

            try{

                vCard.load(connection.getConnection(), contact.getAdress());

            }catch(XMPPException e){

                new DebugMessage("MyVCard: Couldn't load the VCard of " + contact.getUser() + " : " + e.getMessage());

            }

            try{

                avatarBytes = vCard.getAvatar();
                this.avatar = new ImageIcon(avatarBytes);

            }catch(Exception e){

                new DebugMessage("MyVCard.loadAvatar: Couldn't load the Avatar of " + contact.getUser() + " : " + e.getMessage());

            }
        
        }
    }

    public ImageIcon getAvatar(){

        return this.avatar;

    }

    public String getNickName(){
        
        return vCard.getNickName();
    }

}
