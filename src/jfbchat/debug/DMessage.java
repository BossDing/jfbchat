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

/**

   Author Digitex (Giuseppe Federico digitex3d@gmail.com)
*/
public class DMessage {

  protected String value;

  public DMessage(String value) {
    this.value = value;
  }

  public void println() {
    System.out.println(value);
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

}
