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
package jfbchat.resources;

import java.awt.Desktop;

import java.net.URI;
import java.net.URISyntaxException;

import java.io.IOException;

/**
 *
 * Author Digitex (Giuseppe Federico support@digisoftware.org)
 */
public class UtilFunctions {

  /**
  * Shows a frame
  */
  public static void showFrame(javax.swing.JFrame frame) {

  //If the window is not visible
  if (!(frame.isVisible())) {

    //Show the preferences window
    frame.setVisible(true);

  }
  }

  /**
  * Open a webpage URL
  * @param A webpage URL
  */
  public static void openURL(String addr) {

  Desktop desktop = Desktop.getDesktop();

  URI uri = null;
  try {
    uri = new URI(addr);
    desktop.browse(uri);
  }
  catch (IOException ioe) {
    ioe.printStackTrace();
  }
  catch (URISyntaxException use) {
    use.printStackTrace();
  }

  }

}
