/*#############################################################################

JFBChat it's a simple software written in Java that let you conncted with
yours Facebook friends without your browser.
Copyright (C) 2011  Digitex (Giuseppe Federico)This program is free software:
 * you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http:// www.gnu.org/licenses/>.

##############################################################################*/
package jfbchat.labels;

import javax.swing.JLabel;
import jfbchat.resources.Imgs;

/**
 *
 * Author Digitex (Giuseppe Federico support@digisoftware.org)
 */
public class IsWritingLabel extends JLabel{
  private boolean isWriting;

  public IsWritingLabel() {
  super("");
  this.isWriting = false;
  this.setIcon(new javax.swing.ImageIcon(getClass().getResource(Imgs.IS_WRITING_ICON)));

  }

  /**
  * Set the value of isWriting
  *
  */
  public void setIsWriting(boolean isWriting) {
  this.setVisible(isWriting);
  this.validate();
  this.isWriting = isWriting;

  }

  public boolean getIsWriting() {
  return this.isWriting;

  }
}
