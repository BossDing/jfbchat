/*##############################################################################


JFBChat it's a simple software written in Java that let you conncted with 
yours Facebook friends without your browser.
Copyright (C) 2011  Digitex (Giuseppe Federico)This program is free software:
you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

##############################################################################*/


package jfbchat.labels;

import jfbchat.Contact;
import jfbchat.resources.Imgs;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jivesoftware.smack.packet.Presence;

/**
 * An Icon that informs about the relative contact status
 * 
 * Author Digitex (Giuseppe Federico support@digisoftware.org)
 */
public class StatusLabel extends JLabel{    
    private String status;
    private Contact contact;
    
    public StatusLabel(){
        super("");
        this.contact = null;
        this.status = "unavailable";
        this.setIcon(new ImageIcon(getClass().
                        getResource(Imgs.AVAILABLE_ICON)));        
        
    }

    /*
     * Init or update the label icon 
     */
    public void updateLabel(Contact contact){
        this.contact = contact;
        Presence contactPresence = contact.getPresence();
        
        if (contactPresence.isAvailable() || 
            contactPresence.isAway()){

            //Available test
            if ( contactPresence.isAvailable() ){
                 //Change the icon
                this.setIcon(new ImageIcon(getClass().
                        getResource(Imgs.AVAILABLE_ICON)));
                //Change the status information
                this.status = "available";
                validate();

            }
            //Away test
            if ( contactPresence.isAway() ){
                //Change the icon
                this.setIcon(new ImageIcon(getClass().
                        getResource(Imgs.AWAY_ICON)));
                //Change the status information
                this.status = "away";
                validate();
            }

        }
        //If the contact is unavailable
        else{
            setVisible(false);
            //Change the status information
            this.status = "unavailable";
            
        }

    }

    /*
     * Get the status of the contact
     */
    public String getStatus(){
        return this.status;
        
    }
    
}
