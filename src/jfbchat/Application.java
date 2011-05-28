 /* ###########################################################################
  *
  *  JFBChat it's a simple software written in Java that let you in contact
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

import jfbchat.frames.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * this class represent a lifecycle
 * @author Digitex (Giuseppe Federico - digitex3d@gmail.com)
 */

public class Application {

    public final static String VERSION = "0.2.2";

    private MainFrame mainFrame;

    public static final String NAME_OS = System.getProperty("os.name");
    public static final String VERSION_OS = System.getProperty("os.version");
    public static final String ARCH_OS = System.getProperty("os.arch");


    public Application(){
        //Set system look and feel by default
        //TODO: Add the feature to change the look in a option frame
        
        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        run();

    }

    private void run(){

        //Show the MainFrame
        mainFrame = new MainFrame();

    }


    private void setLookAndFeel(String laf){
         try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
