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

import jfbchat.resources.Imgs;
import jfbchat.Contact;
import jfbchat.debug.DebugMessage;
        
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * An avatar label
 * Author Digitex (Giuseppe Federico support@digisoftware.org)
 */
public class AvatarLabel extends JLabel{
    private ImageIcon icon;
    
    public AvatarLabel(){
        super("");
        this.icon = null;
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource(Imgs.NO_AVATAR_ICON)));
        
    }
    
    public void updateAvatarLabel(Contact contact){
        
        if (contact.getPresence().isAvailable() || 
            contact.getPresence().isAway()){

            //Avatar test
            if ( this.icon == null){
                try{
                    //Store the icon in avatarIcon
                    this.icon = contact.getVCard().getAvatar();

                    if ( this.icon != null){
                        //Set the avatar icon
                        this.setIcon( this.icon );
                        
                    }else{
                        new DebugMessage(this.getClass(), "Cannot get the Avatar");
                    }

                }catch(Exception e){
                    new DebugMessage(this.getClass(), "Cannot get the Avatar :", e);

                }
            }
        }
        
        //Set the tool tip text
        this.setToolTipText(contact.getUser());
    }
    
}
