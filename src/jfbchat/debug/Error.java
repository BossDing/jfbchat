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
  along with this program.  If not, see <http:// www.gnu.org/licenses/>. To change this template, choose Tools | Templates

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
  along with this program.  If not, see <http:// www.gnu.org/licenses/>. and open the template in the editor.
  ###################################################*/

package jfbchat.debug;
import javax.swing.JOptionPane;
import jfbchat.Connection;

/*Error 1: Connection error , print a message and close the connection;

*/
public class Error {
  // TODO: make an array with all the errors.
  public Error(int type, String message) {
    switch (type) {
      case 2:
        System.err.println("Error " + type + " , " + message);
        JOptionPane.showMessageDialog(null, "Error " + type + " , " + message + ".");
        break;
    }
  }

  public Error(Connection connection, int type, String message) {
    switch (type) {
      case 1:
        System.err.println("Connection Error " + type + " , " + message + ".");
        JOptionPane.showMessageDialog(null, "Connection Error " + type + " , " + message + ".");
        break;

      case 2:
        System.err.println("PacketListening Error " + type + " , " + message + ".");
        JOptionPane.showMessageDialog(null, "PacketListening " + type + " , " + message + ".");
    }

    connection.closeConnection();
  }

}
